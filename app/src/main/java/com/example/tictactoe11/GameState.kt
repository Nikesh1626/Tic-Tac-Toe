package com.example.tictactoe11

data class GameState(
    val playerCircleCount: Int = 0,
    val playerCrossCount: Int = 0,
    val drawCount: Int = 0,
    val hintText: String = "Player 'O' turn",
    val currentTurn: BoardCellValue = BoardCellValue.CIRCLE,
    val victoryType: VictoryType = VictoryType.NONE,
    val hasWon: Boolean = false,
    val boardItems: Map<Int, BoardCellValue> = emptyMap(),
    val gameMode: GameMode = GameMode.TWO_PLAYER,
    val aiDifficulty: AIDifficulty = AIDifficulty.MEDIUM,
    val isAIThinking: Boolean = false,
    val showGameModeSelection: Boolean = true,
    val selectedTheme: ThemeStyle = ThemeStyle.CURRENT_DEFAULT
)

enum class BoardCellValue {
    CIRCLE,
    CROSS,
    NONE
}

enum class VictoryType {
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    DIAGONAL1,
    DIAGONAL2,
    NONE

}