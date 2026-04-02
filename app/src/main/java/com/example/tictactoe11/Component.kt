package com.example.tictactoe11

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import com.example.tictactoe11.ui.theme.LocalTicTacToeColors

@Composable
@Preview(showBackground = true)
fun BoardLines() {
    val colors = LocalTicTacToeColors.current
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawLine(
            color = colors.boardLines,
            start = Offset(x = size.width * 1 / 3, y = 0f),
            end = Offset(x = size.width * 1 / 3, y = size.height),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = colors.boardLines,
            start = Offset(x = size.width * 2 / 3, y = 0f),
            end = Offset(x = size.width * 2 / 3, y = size.height),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = colors.boardLines,
            start = Offset(x = 0f, y = size.height * 1 / 3),
            end = Offset(x = size.width, y = size.height * 1 / 3),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = colors.boardLines,
            start = Offset(x = 0f, y = size.height * 2 / 3),
            end = Offset(x = size.width, y = size.height * 2 / 3),
            strokeWidth = 5f,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun Cross() {
    val colors = LocalTicTacToeColors.current
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        drawLine(
            color = colors.greenishYellow,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height),
            strokeWidth = 20f,
            cap = StrokeCap.Round
        )
        drawLine(
            color = colors.greenishYellow,
            start = Offset(x = size.width, y = 0f),
            end = Offset(x = 0f, y = size.height),
            strokeWidth = 20f,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun Circle() {
    val colors = LocalTicTacToeColors.current
    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        drawCircle(
            color = colors.aqua,
            style = Stroke(width = 20f)
        )
    }
}

@Composable
fun WinningLine(winningLine: VictoryType) {
    // We use a state to track the animation progress.
    var animationPlayed by remember { mutableStateOf(false) }

    // This animates the value from 0f to 1f over 1000ms.
    val animationProgress by animateFloatAsState(
        targetValue = if (animationPlayed) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    // This effect runs once when the composable is first displayed to start the animation.
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        when (winningLine) {
            VictoryType.HORIZONTAL1 -> {
                val endX = size.width * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = 0f, y = size.height * 1 / 6),
                    end = Offset(x = endX, y = size.height * 1 / 6),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.HORIZONTAL2 -> {
                val endX = size.width * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = 0f, y = size.height * 3 / 6),
                    end = Offset(x = endX, y = size.height * 3 / 6),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.HORIZONTAL3 -> {
                val endX = size.width * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = 0f, y = size.height * 5 / 6),
                    end = Offset(x = endX, y = size.height * 5 / 6),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.VERTICAL1 -> {
                val endY = size.height * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = size.width * 1 / 6, y = 0f),
                    end = Offset(x = size.width * 1 / 6, y = endY),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.VERTICAL2 -> {
                val endY = size.height * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = size.width * 3 / 6, y = 0f),
                    end = Offset(x = size.width * 3 / 6, y = endY),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.VERTICAL3 -> {
                val endY = size.height * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = size.width * 5 / 6, y = 0f),
                    end = Offset(x = size.width * 5 / 6, y = endY),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.DIAGONAL1 -> {
                val endX = size.width * animationProgress
                val endY = size.height * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = endX, y = endY),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.DIAGONAL2 -> {
                val endX = size.width * (1 - animationProgress)
                val endY = size.height * animationProgress
                drawLine(
                    color = Color.Red,
                    start = Offset(x = size.width, y = 0f),
                    end = Offset(x = endX, y = endY),
                    strokeWidth = 10f,
                    cap = StrokeCap.Round
                )
            }

            VictoryType.NONE -> {}
        }
    }
}