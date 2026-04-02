package com.example.tictactoe

import com.example.tictactoe11.GameMode
import com.example.tictactoe11.AIDifficulty
import com.example.tictactoe11.ThemeStyle

sealed class UserAction {
    object PlayAgainButtonClicked : UserAction()
    data class BoardTapped(val cellNo: Int) : UserAction()
    data class StartGame(val gameMode: GameMode, val aiDifficulty: AIDifficulty) : UserAction()
    object ExitCurrentGame : UserAction()
    data class UpdateThemeStyle(val themeStyle: ThemeStyle) : UserAction()
}