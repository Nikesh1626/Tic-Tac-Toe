package com.example.tictactoe

import android.app.Activity
import android.view.HapticFeedbackConstants
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.UserAction
import com.example.tictactoe.ui.theme.LocalTicTacToeColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel
) {
    val state = viewModel.state
    val context = LocalContext.current
    val colors = LocalTicTacToeColors.current
    val actionButtonTextColor = if (state.selectedTheme == ThemeStyle.VOLTAGE_BRUTALIST) Color.Black else Color.White
    var showExitAppDialog by rememberSaveable { mutableStateOf(false) }
    var showExitCurrentGameDialog by rememberSaveable { mutableStateOf(false) }
    var showThemeSheet by rememberSaveable { mutableStateOf(false) }
    var showGameOverSheet by rememberSaveable { mutableStateOf(false) }

    val isDraw = !state.hasWon && state.boardItems.values.all { it != BoardCellValue.NONE }
    val isActiveGameplay = !state.showGameModeSelection && !state.hasWon && !isDraw
    val shouldShowGameOver = !state.showGameModeSelection && (state.hasWon || isDraw)

    LaunchedEffect(shouldShowGameOver) {
        showGameOverSheet = shouldShowGameOver
    }

    BackHandler(enabled = state.showGameModeSelection && !showExitAppDialog) {
        showExitAppDialog = true
    }

    BackHandler(enabled = isActiveGameplay && !showExitCurrentGameDialog) {
        showExitCurrentGameDialog = true
    }

    BackHandler(enabled = shouldShowGameOver && !showGameOverSheet) {
        showGameOverSheet = true
    }

    if (state.showGameModeSelection) {
        GameModeSelectionScreen(
            viewModel = viewModel,
            onChangeThemeClick = { showThemeSheet = true }
        )
    } else {
        TicTacToeGameScreen(viewModel = viewModel)
    }

    if (showThemeSheet) {
        ThemeSelectionSheet(
            currentTheme = state.selectedTheme,
            onDismiss = { showThemeSheet = false },
            onThemeSelected = {
                viewModel.onAction(UserAction.UpdateThemeStyle(it))
                showThemeSheet = false
            }
        )
    }

    if (showExitAppDialog) {
        ModalBottomSheet(
            onDismissRequest = { showExitAppDialog = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            dragHandle = null,
            containerColor = colors.grayBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Exit App",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textColor
                )
                Text(
                    text = "Do you want to exit the app?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.textColor
                )
                Button(
                    onClick = { (context as? Activity)?.finish() },
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(58.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.blueCustom,
                        contentColor = actionButtonTextColor
                    )
                ) {
                    Text("Exit", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                }
                Button(
                    onClick = { showExitAppDialog = false },
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(58.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.destructiveButtonColor,
                        contentColor = actionButtonTextColor
                    )
                ) {
                    Text("Cancel", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }

    if (showExitCurrentGameDialog) {
        ModalBottomSheet(
            onDismissRequest = { showExitCurrentGameDialog = false },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            dragHandle = null,
            containerColor = colors.grayBackground
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Exit Current Game",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textColor
                )
                Text(
                    text = "Are you sure you want to exit this game?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.textColor
                )
                Button(
                    onClick = {
                        showExitCurrentGameDialog = false
                        viewModel.onAction(UserAction.ExitCurrentGame)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(58.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.blueCustom,
                        contentColor = actionButtonTextColor
                    )
                ) {
                    Text("Exit", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                }
                Button(
                    onClick = { showExitCurrentGameDialog = false },
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(58.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.destructiveButtonColor,
                        contentColor = actionButtonTextColor
                    )
                ) {
                    Text("Cancel", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }

    if (shouldShowGameOver && showGameOverSheet) {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ModalBottomSheet(
            onDismissRequest = { },
            sheetState = sheetState,
            dragHandle = null,
            properties = ModalBottomSheetProperties(
                shouldDismissOnBackPress = false
            ),
            containerColor = colors.grayBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Game Over",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = colors.textColor
                )
                Text(
                    text = if (isDraw) "It's a draw" else state.hintText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.textColor
                )
                Button(
                    onClick = {
                        showGameOverSheet = false
                        viewModel.onAction(UserAction.PlayAgainButtonClicked)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(58.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.blueCustom,
                        contentColor = actionButtonTextColor
                    )
                ) {
                    Text("Play Again", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                }
                Button(
                    onClick = {
                        showGameOverSheet = false
                        viewModel.onAction(UserAction.ExitCurrentGame)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.86f)
                        .height(58.dp),
                    shape = RoundedCornerShape(22.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.destructiveButtonColor,
                        contentColor = actionButtonTextColor
                    )
                ) {
                    Text("Exit", fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ThemeSelectionSheet(
    currentTheme: ThemeStyle,
    onDismiss: () -> Unit,
    onThemeSelected: (ThemeStyle) -> Unit
) {
    val colors = LocalTicTacToeColors.current

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        dragHandle = null,
        containerColor = colors.sheetSurface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Change Theme",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textColor
            )
            Text(
                text = "Choose the look you want for the game.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colors.textSecondaryColor
            )

            ThemeStyle.entries.forEach { themeStyle ->
                val selected = themeStyle == currentTheme
                Card(
                    onClick = {
                        onThemeSelected(themeStyle)
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selected) colors.blueCustom.copy(alpha = 0.14f) else colors.cardSurface
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = themeStyle.displayName,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = colors.textColor
                            )
                            if (selected) {
                                Text(
                                    text = "Selected",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colors.blueCustom
                                )
                            }
                        }

                        Text(
                            text = when (themeStyle) {
                                ThemeStyle.CURRENT_DEFAULT -> "Keeps the current visual style of the game."
                                ThemeStyle.NEON_NEBULA -> "A neon, glowing look with vivid contrast."
                                ThemeStyle.SOFT_TACTILE -> "Soft surfaces with calm tactile shadows."
                                ThemeStyle.VOLTAGE_BRUTALIST -> "High-contrast, bold, and aggressive."
                            },
                            fontSize = 14.sp,
                            color = colors.textSecondaryColor
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            val previewColors = when (themeStyle) {
                                ThemeStyle.CURRENT_DEFAULT -> listOf(colors.grayBackground, colors.blueCustom, colors.aqua)
                                ThemeStyle.NEON_NEBULA -> listOf(Color(0xFF0F1C3A), Color(0xFF00D9FF), Color(0xFFFF006E))
                                ThemeStyle.SOFT_TACTILE -> listOf(Color(0xFFF9F9FB), Color(0xFF306465), Color(0xFFA39A64))
                                ThemeStyle.VOLTAGE_BRUTALIST -> listOf(Color.Black, Color.Yellow, Color.Magenta)
                            }
                            previewColors.forEach { previewColor ->
                                Box(
                                    modifier = Modifier
                                        .size(18.dp)
                                        .background(previewColor, RoundedCornerShape(6.dp))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GameModeSelectionScreen(
    viewModel: GameViewModel,
    onChangeThemeClick: () -> Unit
) {
    val colors = LocalTicTacToeColors.current
    val actionButtonTextColor = if (viewModel.state.selectedTheme == ThemeStyle.VOLTAGE_BRUTALIST) Color.Black else Color.White
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(colors.grayBackground)
    ) {
        val compactLayout = maxWidth < 360.dp || maxHeight < 700.dp
        val screenPadding = if (compactLayout) 16.dp else 24.dp
        val titleSize = if (compactLayout) 42.sp else 50.sp
        val headingSize = if (compactLayout) 24.sp else 28.sp
        val buttonHeightPadding = if (compactLayout) 10.dp else 15.dp
        val buttonFont = if (compactLayout) 18.sp else 20.sp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = screenPadding, vertical = if (compactLayout) 12.dp else 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Tic Tac Toe",
                fontSize = titleSize,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = colors.blueCustom
            )

            Text(
                text = "Select Game Mode",
                fontSize = headingSize,
                fontWeight = FontWeight.Bold,
                color = colors.textColor
            )

            Button(
                onClick = {
                    viewModel.onAction(UserAction.StartGame(GameMode.TWO_PLAYER, AIDifficulty.MEDIUM))
                },
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                modifier = Modifier
                    .fillMaxWidth(if (compactLayout) 0.9f else 0.85f)
                    .padding(vertical = buttonHeightPadding),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.blueCustom,
                    contentColor = actionButtonTextColor
                )
            ) {
                Text(
                    "Two Player",
                    fontSize = buttonFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(if (compactLayout) 14.dp else 15.dp)
                )
            }

            Text(
                text = "vs AI - Select Difficulty",
                fontSize = if (compactLayout) 16.sp else 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.textColor
            )

            Button(
                onClick = {
                    viewModel.onAction(UserAction.StartGame(GameMode.SINGLE_PLAYER, AIDifficulty.EASY))
                },
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                modifier = Modifier
                    .fillMaxWidth(if (compactLayout) 0.9f else 0.85f)
                    .padding(vertical = if (compactLayout) 6.dp else 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.easyButtonColor,
                    contentColor = actionButtonTextColor
                )
            ) {
                Text(
                    "Easy",
                    fontSize = if (compactLayout) 17.sp else 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(if (compactLayout) 11.dp else 12.dp)
                )
            }

            Button(
                onClick = {
                    viewModel.onAction(UserAction.StartGame(GameMode.SINGLE_PLAYER, AIDifficulty.MEDIUM))
                },
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                modifier = Modifier
                    .fillMaxWidth(if (compactLayout) 0.9f else 0.85f)
                    .padding(vertical = if (compactLayout) 6.dp else 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.mediumButtonColor,
                    contentColor = actionButtonTextColor
                )
            ) {
                Text(
                    "Medium",
                    fontSize = if (compactLayout) 17.sp else 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(if (compactLayout) 11.dp else 12.dp)
                )
            }

            Button(
                onClick = {
                    viewModel.onAction(UserAction.StartGame(GameMode.SINGLE_PLAYER, AIDifficulty.HARD))
                },
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(5.dp),
                modifier = Modifier
                    .fillMaxWidth(if (compactLayout) 0.9f else 0.85f)
                    .padding(vertical = if (compactLayout) 6.dp else 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.hardButtonColor,
                    contentColor = actionButtonTextColor
                )
            ) {
                Text(
                    "Hard",
                    fontSize = if (compactLayout) 17.sp else 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(if (compactLayout) 11.dp else 12.dp)
                )
            }
        }

        Text(
            text = "Change theme",
            fontSize = if (compactLayout) 16.sp else 17.sp,
            fontWeight = FontWeight.SemiBold,
            color = colors.blueCustom,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = if (compactLayout) 16.dp else 24.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = onChangeThemeClick
                )
        )
    }
}

@Composable
private fun TicTacToeGameScreen(
    viewModel: GameViewModel
) {
    val state = viewModel.state
    val colors = LocalTicTacToeColors.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(colors.grayBackground)
    ) {
        val compactLayout = maxWidth < 360.dp || maxHeight < 700.dp
        val screenPadding = if (compactLayout) 16.dp else 24.dp
        val titleSize = if (compactLayout) 36.sp else 42.sp
        val hintSize = if (compactLayout) 19.sp else 22.sp
        val scoreCardPaddingY = if (compactLayout) 8.dp else 10.dp
        val scoreCardPaddingX = if (compactLayout) 12.dp else 14.dp
        val boardSize = minOf(maxWidth - (screenPadding * 2), maxHeight * if (compactLayout) 0.39f else 0.44f)
        val boardInnerPadding = if (compactLayout) 16.dp else 20.dp
        val cellPadding = if (compactLayout) 12.dp else 16.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = screenPadding, vertical = if (compactLayout) 12.dp else 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Tic Tac Toe",
                fontSize = titleSize,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = colors.blueCustom
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = colors.blueCustom.copy(alpha = 0.12f)),
                shape = RoundedCornerShape(14.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = scoreCardPaddingX, vertical = scoreCardPaddingY),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (state.gameMode == GameMode.TWO_PLAYER) {
                        ScoreBlock("Player O", state.playerCircleCount, colors.textColor)
                        DividerDot(colors.textColor)
                        ScoreBlock("Draw", state.drawCount, colors.textColor)
                        DividerDot(colors.textColor)
                        ScoreBlock("Player X", state.playerCrossCount, colors.textColor)
                    } else {
                        ScoreBlock("You", state.playerCircleCount, colors.textColor)
                        DividerDot(colors.textColor)
                        ScoreBlock("Draw", state.drawCount, colors.textColor)
                        DividerDot(colors.textColor)
                        ScoreBlock("AI", state.playerCrossCount, colors.textColor)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = if (compactLayout) 8.dp else 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(boardSize)
                        .shadow(
                            elevation = 10.dp,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .background(colors.grayBackground)
                        .padding(boardInnerPadding)
                ) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(3)
                    ) {
                        state.boardItems.forEach { (cellNo, boardCellValue) ->
                            item {
                                val view = LocalView.current
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .aspectRatio(1f)
                                        .padding(cellPadding)
                                        .clickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = null,
                                            enabled = !state.isAIThinking && !state.hasWon
                                        ) {
                                            view.performHapticFeedback(
                                                HapticFeedbackConstants.VIRTUAL_KEY,
                                                HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING
                                            )
                                            viewModel.onAction(UserAction.BoardTapped(cellNo))
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    AnimatedVisibility(
                                        visible = state.boardItems[cellNo] != BoardCellValue.NONE,
                                        enter = scaleIn(animationSpec = tween(durationMillis = 220))
                                    ) {
                                        if (boardCellValue == BoardCellValue.CIRCLE) {
                                            Circle()
                                        } else if (boardCellValue == BoardCellValue.CROSS) {
                                            Cross()
                                        }
                                    }
                                }
                            }
                        }
                    }
                    BoardLines()

                    if (state.hasWon) {
                        WinningLine(winningLine = state.victoryType)
                    }
                }
            }

            Text(
                text = state.hintText,
                fontSize = hintSize,
                fontStyle = FontStyle.Italic,
                color = colors.textColor
            )
        }
    }
}

@Composable
private fun ScoreBlock(label: String, value: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, color = color, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = value.toString(), color = color, fontWeight = FontWeight.Bold, fontSize = 22.sp)
    }
}

@Composable
private fun DividerDot(color: Color) {
    Text(text = "•", color = color.copy(alpha = 0.7f), fontSize = 20.sp, modifier = Modifier.width(8.dp))
}
