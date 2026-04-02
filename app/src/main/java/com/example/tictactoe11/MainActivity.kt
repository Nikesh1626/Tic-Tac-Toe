package com.example.tictactoe11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe11.ui.theme.TicTacToe11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: GameViewModel = viewModel()
            val themeStyle = viewModel.state.selectedTheme
            TicTacToe11Theme(themeStyle = themeStyle) {
                GameScreen(viewModel = viewModel)
            }
        }
    }
}

