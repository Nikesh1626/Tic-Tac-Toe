package com.example.tictactoe11.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

// Custom color scheme for TicTacToe game
data class TicTacToeColors(
    val aqua: Color,
    val greenishYellow: Color,
    val grayBackground: Color,
    val blueCustom: Color,
    val boardLines: Color,
    val textColor: Color,
    val isDark: Boolean
)

val LocalTicTacToeColors = staticCompositionLocalOf {
    TicTacToeColors(
        aqua = Aqua,
        greenishYellow = GreenishYellow,
        grayBackground = GrayBackground,
        blueCustom = BlueCustom,
        boardLines = Color.Gray,
        textColor = Color.Black,
        isDark = false
    )
}

@Composable
fun TicTacToe11Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Custom TicTacToe colors based on theme
    val ticTacToeColors = if (darkTheme) {
        TicTacToeColors(
            aqua = AquaDark,
            greenishYellow = GreenishYellowDark,
            grayBackground = GrayBackgroundDark,
            blueCustom = BlueCustomDark,
            boardLines = DarkBoardLinesDark,
            textColor = DarkTextColorDark,
            isDark = true
        )
    } else {
        TicTacToeColors(
            aqua = Aqua,
            greenishYellow = GreenishYellow,
            grayBackground = GrayBackground,
            blueCustom = BlueCustom,
            boardLines = Color.Gray,
            textColor = Color.Black,
            isDark = false
        )
    }

    androidx.compose.runtime.CompositionLocalProvider(
        LocalTicTacToeColors provides ticTacToeColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}