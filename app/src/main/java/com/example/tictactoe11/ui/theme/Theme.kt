package com.example.tictactoe11.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.example.tictactoe11.ThemeStyle


data class TicTacToeColors(
    val aqua: Color,
    val greenishYellow: Color,
    val grayBackground: Color,
    val blueCustom: Color,
    val boardLines: Color,
    val textColor: Color,
    val textSecondaryColor: Color,
    val sheetSurface: Color,
    val cardSurface: Color,
    val easyButtonColor: Color,
    val mediumButtonColor: Color,
    val hardButtonColor: Color,
    val destructiveButtonColor: Color,
    val winningLineColor: Color
)

val LocalTicTacToeColors = staticCompositionLocalOf {
    TicTacToeColors(
        aqua = CurrentO,
        greenishYellow = CurrentX,
        grayBackground = CurrentBackground,
        blueCustom = CurrentPrimary,
        boardLines = CurrentLine,
        textColor = CurrentText,
        textSecondaryColor = CurrentTextSecondary,
        sheetSurface = CurrentSurface,
        cardSurface = CurrentSurface,
        easyButtonColor = CurrentEasy,
        mediumButtonColor = CurrentMedium,
        hardButtonColor = CurrentHard,
        destructiveButtonColor = CurrentDestructive,
        winningLineColor = CurrentWinningLine
    )
}

@Composable
fun TicTacToe11Theme(
    themeStyle: ThemeStyle = ThemeStyle.CURRENT_DEFAULT,
    content: @Composable () -> Unit
) {
    val ticTacToeColors = when (themeStyle) {
        ThemeStyle.CURRENT_DEFAULT -> TicTacToeColors(
            aqua = CurrentO,
            greenishYellow = CurrentX,
            grayBackground = CurrentBackground,
            blueCustom = CurrentPrimary,
            boardLines = CurrentLine,
            textColor = CurrentText,
            textSecondaryColor = CurrentTextSecondary,
            sheetSurface = CurrentSurface,
            cardSurface = CurrentSurface,
            easyButtonColor = CurrentEasy,
            mediumButtonColor = CurrentMedium,
            hardButtonColor = CurrentHard,
            destructiveButtonColor = CurrentDestructive,
            winningLineColor = CurrentWinningLine
        )

        ThemeStyle.NEON_NEBULA -> TicTacToeColors(
            aqua = NeonO,
            greenishYellow = NeonX,
            grayBackground = NeonBackground,
            blueCustom = NeonPrimary,
            boardLines = NeonLine,
            textColor = NeonText,
            textSecondaryColor = NeonTextSecondary,
            sheetSurface = NeonSurface,
            cardSurface = NeonSurface,
            easyButtonColor = NeonEasy,
            mediumButtonColor = NeonMedium,
            hardButtonColor = NeonHard,
            destructiveButtonColor = NeonDestructive,
            winningLineColor = NeonWinningLine
        )

        ThemeStyle.SOFT_TACTILE -> TicTacToeColors(
            aqua = SoftO,
            greenishYellow = SoftX,
            grayBackground = SoftBackground,
            blueCustom = SoftPrimary,
            boardLines = SoftLine,
            textColor = SoftText,
            textSecondaryColor = SoftTextSecondary,
            sheetSurface = SoftSurface,
            cardSurface = SoftSurface,
            easyButtonColor = SoftEasy,
            mediumButtonColor = SoftMedium,
            hardButtonColor = SoftHard,
            destructiveButtonColor = SoftDestructive,
            winningLineColor = SoftWinningLine
        )

        ThemeStyle.VOLTAGE_BRUTALIST -> TicTacToeColors(
            aqua = BrutalO,
            greenishYellow = BrutalX,
            grayBackground = BrutalBackground,
            blueCustom = BrutalPrimary,
            boardLines = BrutalLine,
            textColor = BrutalText,
            textSecondaryColor = BrutalTextSecondary,
            sheetSurface = BrutalSurface,
            cardSurface = BrutalSurface,
            easyButtonColor = BrutalEasy,
            mediumButtonColor = BrutalMedium,
            hardButtonColor = BrutalHard,
            destructiveButtonColor = BrutalDestructive,
            winningLineColor = BrutalWinningLine
        )
    }

    val colorScheme = when (themeStyle) {
        ThemeStyle.CURRENT_DEFAULT,
        ThemeStyle.SOFT_TACTILE -> lightColorScheme(
            primary = ticTacToeColors.blueCustom,
            secondary = ticTacToeColors.aqua,
            tertiary = ticTacToeColors.greenishYellow,
            background = ticTacToeColors.grayBackground,
            surface = ticTacToeColors.sheetSurface,
            onPrimary = Color.White,
            onSecondary = Color.White,
            onTertiary = Color.White,
            onBackground = ticTacToeColors.textColor,
            onSurface = ticTacToeColors.textColor
        )

        ThemeStyle.NEON_NEBULA,
        ThemeStyle.VOLTAGE_BRUTALIST -> darkColorScheme(
            primary = ticTacToeColors.blueCustom,
            secondary = ticTacToeColors.aqua,
            tertiary = ticTacToeColors.greenishYellow,
            background = ticTacToeColors.grayBackground,
            surface = ticTacToeColors.sheetSurface,
            onPrimary = Color.Black,
            onSecondary = Color.Black,
            onTertiary = Color.Black,
            onBackground = ticTacToeColors.textColor,
            onSurface = ticTacToeColors.textColor
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
