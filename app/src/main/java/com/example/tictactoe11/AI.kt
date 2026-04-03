package com.example.tictactoe

import kotlin.random.Random

enum class GameMode {
    TWO_PLAYER,
    SINGLE_PLAYER
}

enum class AIDifficulty {
    EASY,
    MEDIUM,
    HARD
}

object AIPlayer {
    
    /**
     * Get the best move for the AI player based on difficulty
     */
    fun getBestMove(
        boardItems: Map<Int, BoardCellValue>,
        difficulty: AIDifficulty
    ): Int {
        return when (difficulty) {
            AIDifficulty.EASY -> getEasyMove(boardItems)
            AIDifficulty.MEDIUM -> getMediumMove(boardItems)
            AIDifficulty.HARD -> getHardMove(boardItems)
        }
    }

    /**
     * Easy difficulty - mostly random with occasional defense.
     */
    private fun getEasyMove(boardItems: Map<Int, BoardCellValue>): Int {
        // Rarely play a smart move.
        if (Random.nextFloat() < 0.2f) {
            return getMediumStrategicMove(boardItems)
        }

        // Sometimes block obvious one-move losses.
        if (Random.nextFloat() < 0.3f) {
            val blockMove = findWinningMove(boardItems, BoardCellValue.CIRCLE)
            if (blockMove != -1) return blockMove
        }

        return getRandomMove(boardItems)
    }

    /**
     * Random move - makes mistakes often
     */
    private fun getRandomMove(boardItems: Map<Int, BoardCellValue>): Int {
        val availableMoves = boardItems.filter { it.value == BoardCellValue.NONE }.keys.toList()
        return if (availableMoves.isNotEmpty()) {
            availableMoves.random()
        } else {
            -1
        }
    }

    /**
     * Medium difficulty - wins if possible, blocks if opponent is winning, otherwise random
     */
    private fun getMediumMove(boardItems: Map<Int, BoardCellValue>): Int {
        // Medium intentionally makes occasional mistakes to stay fair.
        return if (Random.nextFloat() < 0.75f) {
            getMediumStrategicMove(boardItems)
        } else {
            getRandomMove(boardItems)
        }
    }

    private fun getMediumStrategicMove(boardItems: Map<Int, BoardCellValue>): Int {
        // Try to win
        val winMove = findWinningMove(boardItems, BoardCellValue.CROSS)
        if (winMove != -1) return winMove

        // Try to block opponent
        val blockMove = findWinningMove(boardItems, BoardCellValue.CIRCLE)
        if (blockMove != -1) return blockMove

        // Take center if available
        if (boardItems[5] == BoardCellValue.NONE) return 5

        // Take a corner if available
        val corners = listOf(1, 3, 7, 9).filter { boardItems[it] == BoardCellValue.NONE }
        if (corners.isNotEmpty()) return corners.random()

        // Take any available move
        return getRandomMove(boardItems)
    }

    /**
     * Hard difficulty - uses minimax algorithm for optimal play
     */
    private fun getHardMove(boardItems: Map<Int, BoardCellValue>): Int {
        var bestScore = Int.MIN_VALUE
        var bestMove = -1

        (1..9).forEach { move ->
            if (boardItems[move] == BoardCellValue.NONE) {
                val newBoard = boardItems.toMutableMap()
                newBoard[move] = BoardCellValue.CROSS
                val score = minimax(newBoard, 0, false)
                
                if (score > bestScore) {
                    bestScore = score
                    bestMove = move
                }
            }
        }

        return bestMove
    }

    /**
     * Minimax algorithm - recursively evaluates positions
     */
    private fun minimax(boardItems: Map<Int, BoardCellValue>, depth: Int, isMaximizing: Boolean): Int {
        val winner = checkWinner(boardItems)
        
        // Terminal states
        when (winner) {
            BoardCellValue.CROSS -> return 10 - depth  // AI wins (prefer quicker wins)
            BoardCellValue.CIRCLE -> return depth - 10  // Player wins (prefer slower losses)
            BoardCellValue.NONE -> if (isBoardFull(boardItems)) return 0  // Draw
        }

        return if (isMaximizing) {
            // AI's turn (maximize score)
            var bestScore = Int.MIN_VALUE
            (1..9).forEach { move ->
                if (boardItems[move] == BoardCellValue.NONE) {
                    val newBoard = boardItems.toMutableMap()
                    newBoard[move] = BoardCellValue.CROSS
                    val score = minimax(newBoard, depth + 1, false)
                    bestScore = maxOf(bestScore, score)
                }
            }
            bestScore
        } else {
            // Player's turn (minimize score)
            var bestScore = Int.MAX_VALUE
            (1..9).forEach { move ->
                if (boardItems[move] == BoardCellValue.NONE) {
                    val newBoard = boardItems.toMutableMap()
                    newBoard[move] = BoardCellValue.CIRCLE
                    val score = minimax(newBoard, depth + 1, true)
                    bestScore = minOf(bestScore, score)
                }
            }
            bestScore
        }
    }

    /**
     * Find a move that results in three in a row for the given player
     */
    private fun findWinningMove(boardItems: Map<Int, BoardCellValue>, player: BoardCellValue): Int {
        val winningLines = listOf(
            listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9),
            listOf(1, 4, 7), listOf(2, 5, 8), listOf(3, 6, 9),
            listOf(1, 5, 9), listOf(3, 5, 7)
        )

        winningLines.forEach { line ->
            val values = line.map { boardItems[it] }
            if (values.count { it == player } == 2 && values.count { it == BoardCellValue.NONE } == 1) {
                return line.first { boardItems[it] == BoardCellValue.NONE }
            }
        }
        return -1
    }

    /**
     * Check if there's a winner on the board
     */
    private fun checkWinner(boardItems: Map<Int, BoardCellValue>): BoardCellValue {
        val winningLines = listOf(
            listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9),
            listOf(1, 4, 7), listOf(2, 5, 8), listOf(3, 6, 9),
            listOf(1, 5, 9), listOf(3, 5, 7)
        )

        winningLines.forEach { line ->
            val firstCell = boardItems[line[0]] ?: return@forEach
            val secondCell = boardItems[line[1]] ?: return@forEach
            val thirdCell = boardItems[line[2]] ?: return@forEach

            if (firstCell != BoardCellValue.NONE && firstCell == secondCell && firstCell == thirdCell) {
                return firstCell
            }
        }
        return BoardCellValue.NONE
    }

    /**
     * Check if the board is completely full
     */
    private fun isBoardFull(boardItems: Map<Int, BoardCellValue>): Boolean {
        return boardItems.values.all { it != BoardCellValue.NONE }
    }
}
