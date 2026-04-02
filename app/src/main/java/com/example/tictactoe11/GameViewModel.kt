package com.example.tictactoe11

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.UserAction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val themePreferences = ThemePreferences(application)

    var state by mutableStateOf(
        GameState(
            boardItems = createEmptyBoard()
        )
    )

    init {
        viewModelScope.launch {
            themePreferences.themeStyleFlow.collect { themeStyle ->
                state = state.copy(selectedTheme = themeStyle)
            }
        }
    }

    fun onAction(action: UserAction) {
        when (action) {
            is UserAction.BoardTapped -> {
                addValueToBoard(action.cellNo)
            }

            UserAction.PlayAgainButtonClicked -> {
                playAgain()
            }

            is UserAction.StartGame -> {
                startGame(action.gameMode, action.aiDifficulty)
            }

            UserAction.ExitCurrentGame -> {
                exitCurrentGame()
            }

            is UserAction.UpdateThemeStyle -> {
                state = state.copy(selectedTheme = action.themeStyle)
                viewModelScope.launch {
                    themePreferences.setThemeStyle(action.themeStyle)
                }
            }
        }
    }

    private fun startGame(gameMode: GameMode, aiDifficulty: AIDifficulty) {
        state = state.copy(
            gameMode = gameMode,
            aiDifficulty = aiDifficulty,
            playerCircleCount = 0,
            playerCrossCount = 0,
            drawCount = 0,
            showGameModeSelection = false,
            boardItems = createEmptyBoard(),
            currentTurn = BoardCellValue.CIRCLE,
            hintText = if (gameMode == GameMode.SINGLE_PLAYER) "Your turn (O)" else "Player 'O' turn",
            hasWon = false,
            victoryType = VictoryType.NONE
        )
    }

    private fun addValueToBoard(cellNo: Int) {
        if (state.showGameModeSelection || state.hasWon || state.boardItems[cellNo] != BoardCellValue.NONE || state.isAIThinking) {
            return
        }

        val updatedBoardItems = state.boardItems.toMutableMap()
        updatedBoardItems[cellNo] = state.currentTurn

        state = state.copy(
            boardItems = updatedBoardItems,
            currentTurn = if (state.currentTurn == BoardCellValue.CIRCLE) {
                BoardCellValue.CROSS
            } else {
                BoardCellValue.CIRCLE
            },
            hintText = if (state.currentTurn == BoardCellValue.CIRCLE) {
                "Player 'X' turn"
            } else {
                if (state.gameMode == GameMode.SINGLE_PLAYER) "AI is thinking..." else "Player 'O' turn"
            }
        )

        checkForVictory()

        if (!state.hasWon) {
            checkForDraw()
        }

        // If AI should play next
        if (!state.hasWon && state.gameMode == GameMode.SINGLE_PLAYER && state.currentTurn == BoardCellValue.CROSS) {
            makeAIMove()
        }
    }

    private fun makeAIMove() {
        viewModelScope.launch {
            state = state.copy(isAIThinking = true, hintText = "AI is thinking...")
            
            // Simulate thinking time (800ms to 1.5s based on difficulty)
            val delay = when (state.aiDifficulty) {
                AIDifficulty.EASY -> 600L
                AIDifficulty.MEDIUM -> 800L
                AIDifficulty.HARD -> 1000L
            }
            delay(delay)

            if (state.isAIThinking) { // Double-check game hasn't ended
                val availableMoves = state.boardItems.filter { it.value == BoardCellValue.NONE }.keys
                
                if (availableMoves.isNotEmpty()) {
                    val aiMove = AIPlayer.getBestMove(state.boardItems, state.aiDifficulty)
                    
                    val updatedBoardItems = state.boardItems.toMutableMap()
                    updatedBoardItems[aiMove] = BoardCellValue.CROSS

                    state = state.copy(
                        boardItems = updatedBoardItems,
                        currentTurn = BoardCellValue.CIRCLE,
                        hintText = "Your turn (O)",
                        isAIThinking = false
                    )

                    checkForVictory()
                    if (!state.hasWon) {
                        checkForDraw()
                    }
                } else {
                    state = state.copy(isAIThinking = false)
                }
            }
        }
    }

    private fun checkForVictory() {
        val winningLines = listOf(
            listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9),
            listOf(1, 4, 7), listOf(2, 5, 8), listOf(3, 6, 9),
            listOf(1, 5, 9), listOf(3, 5, 7)
        )

        winningLines.forEach { line ->
            val firstCell = state.boardItems[line[0]]
            val secondCell = state.boardItems[line[1]]
            val thirdCell = state.boardItems[line[2]]

            if (firstCell != BoardCellValue.NONE && firstCell == secondCell && firstCell == thirdCell) {
                val updatedCircleCount =
                    if (firstCell == BoardCellValue.CIRCLE) state.playerCircleCount + 1 else state.playerCircleCount
                val updatedCrossCount =
                    if (firstCell == BoardCellValue.CROSS) state.playerCrossCount + 1 else state.playerCrossCount

                state = state.copy(
                    hasWon = true,
                    victoryType = when (line) {
                        listOf(1, 2, 3) -> VictoryType.HORIZONTAL1
                        listOf(4, 5, 6) -> VictoryType.HORIZONTAL2
                        listOf(7, 8, 9) -> VictoryType.HORIZONTAL3
                        listOf(1, 4, 7) -> VictoryType.VERTICAL1
                        listOf(2, 5, 8) -> VictoryType.VERTICAL2
                        listOf(3, 6, 9) -> VictoryType.VERTICAL3
                        listOf(1, 5, 9) -> VictoryType.DIAGONAL1
                        else -> VictoryType.DIAGONAL2
                    },
                    hintText = if (firstCell == BoardCellValue.CIRCLE) {
                        if (state.gameMode == GameMode.SINGLE_PLAYER) "You won! 🎉" else "Player 'O' has won!"
                    } else {
                        if (state.gameMode == GameMode.SINGLE_PLAYER) "AI won! Better luck next time 🤖" else "Player 'X' has won!"
                    },
                    playerCircleCount = updatedCircleCount,
                    playerCrossCount = updatedCrossCount,
                    isAIThinking = false
                )
                return
            }
        }
    }

    private fun checkForDraw() {
        if (state.boardItems.values.all { it != BoardCellValue.NONE }) {
            state = state.copy(
                drawCount = state.drawCount + 1,
                hintText = "It's a draw",
                isAIThinking = false
            )
        }
    }

    private fun playAgain() {
        state = state.copy(
            hintText = if (state.gameMode == GameMode.TWO_PLAYER) "Player 'O' turn" else "Your turn (O)",
            currentTurn = BoardCellValue.CIRCLE,
            boardItems = createEmptyBoard(),
            victoryType = VictoryType.NONE,
            hasWon = false,
            isAIThinking = false
        )
    }

    private fun exitCurrentGame() {
        state = state.copy(
            showGameModeSelection = true,
            hintText = "Player 'O' turn",
            currentTurn = BoardCellValue.CIRCLE,
            boardItems = createEmptyBoard(),
            victoryType = VictoryType.NONE,
            hasWon = false,
            isAIThinking = false
        )
    }

    private fun createEmptyBoard(): MutableMap<Int, BoardCellValue> {
        return (1..9).associateWith { BoardCellValue.NONE }.toMutableMap()
    }
}