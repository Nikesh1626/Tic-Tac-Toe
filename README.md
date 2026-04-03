# TIC TAC TOE

![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-Design%20System-757575?logo=materialdesign&logoColor=white)
![Android](https://img.shields.io/badge/Android-API%2024%2B-3DDC84?logo=android&logoColor=white)

TIC TAC TOE is a modern Android Tic-Tac-Toe application built with Jetpack Compose, Material 3, and an MVVM architecture. It supports local two-player matches, single-player play against an AI opponent, and persistent theme selection for a polished mobile experience.

## Table of Contents

- Features
- Tech Stack
- Project Structure
- How It Works
- Screenshots
- Architecture
- AI Behavior
- Theme System
- Getting Started
- Build and Run
- Testing
- Release Notes
- Contributing
- License

## Features

- Local two-player mode.
- Single-player mode with AI difficulty selection.
- Three AI difficulty levels: Easy, Medium, and Hard.
- Persistent theme selection across app launches.
- Animated victory line rendering and board interactions.
- Score tracking for O wins, X wins, and draws.
- Exit confirmation and game-over flows using Material 3 bottom sheets.
- Responsive Compose UI with haptic feedback support.

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Android ViewModel
- Lifecycle ViewModel Compose
- DataStore Preferences

## Project Structure

```text
.
├── build.gradle.kts
├── settings.gradle.kts
├── gradle/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/tictactoe/
│       │   ├── MainActivity.kt
│       │   ├── GameScreen.kt
│       │   ├── GameViewModel.kt
│       │   ├── GameState.kt
│       │   ├── AI.kt
│       │   ├── ThemePreferences.kt
│       │   ├── ThemeStyle.kt
│       │   ├── UserAction.kt
│       │   └── ui/theme/
│       └── res/
└── README.md
```

## How It Works

1. The app launches into a mode-selection screen.
2. The player chooses either local two-player mode or single-player mode against the AI.
3. In single-player mode, the AI difficulty is selected before the match begins.
4. Players tap cells on the 3x3 board to place O and X values.
5. The ViewModel validates moves, checks for wins or draws, and updates the board state.
6. In single-player mode, the AI takes its turn automatically after the human move.
7. When the game ends, a bottom sheet appears with the result and actions to play again or exit.
8. Theme changes are saved locally and restored on the next launch.

## AI Behavior

The AI is implemented entirely in Kotlin and adapts to the selected difficulty:

- Easy: mostly random, with occasional strategic or defensive moves.
- Medium: strategic most of the time, but intentionally imperfect.
- Hard: uses a minimax-based strategy for optimal play.

In single-player mode, the human player uses O and the AI uses X.

## Theme System

The application includes four visual styles:

- Current
- Neon Nebula
- Soft Tactile
- Voltage Brutalist

The selected theme is stored locally using DataStore Preferences and restored automatically on the next app launch.

## Screenshots

### 1. Home Screen

![1. Home Screen](app/docs/Screenshots/1.%20Home%20Screen.jpeg)

### 2. Gameplay Screen Theme 1

![2. Gameplay Screen Theme 1](app/docs/Screenshots/2.%20Gameplay%20Screen%20Theme%201.jpeg)

### 3. Theme Selection Sheet

![3. Theme Selection Sheet](app/docs/Screenshots/3.%20Theme%20Selection%20Sheet.jpeg)

### 4. Gameplay Screen Theme 2

![4. Gameplay Screen Theme 2](app/docs/Screenshots/4.%20Gameplay%20Screen%20Theme%202.jpeg)

### 5. Gameplay Screen with AI

![5. Gameplay Screen with AI](app/docs/Screenshots/5.%20Gameplay%20Screen%20with%20AI.jpeg)

## Architecture

The project follows a lightweight MVVM structure:

- `MainActivity` hosts the Compose entry point.
- `GameViewModel` owns the game state and user actions.
- `GameState` models the board, scores, mode, and theme.
- `AIPlayer` contains the move-selection logic for each difficulty level.
- `ThemePreferences` persists the selected theme with DataStore.
- `GameScreen` renders the UI and interaction layers.

## Getting Started

### Prerequisites

- Android Studio with support for Android Gradle Plugin 8.11.2.
- JDK 11.
- Android SDK 36.
- Minimum Android API level 24.

### Open the Project

Open the repository in Android Studio, let Gradle sync complete, and select a device or emulator to run the app.

## Build and Run

### Windows

```powershell
.\gradlew.bat :app:assembleDebug
```

### macOS / Linux

```bash
./gradlew :app:assembleDebug
```

The debug APK is generated at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

To install and run the app from Android Studio, use the standard Run configuration for the `app` module.

## Testing

The repository includes the standard Android unit and instrumentation test scaffolding. No custom automated test suite is currently defined in the app code.

## Release Notes

- Package name: `com.example.tictactoe`
- Application label: `TIC TAC TOE`
- Release build type is configured with ProGuard disabled.
- The app runs fully offline and does not require external services.

## Contributing

1. Fork the repository.
2. Create a feature branch.
3. Make your changes with clear commit messages.
4. Open a pull request describing the change and validation performed.

## License

Add a license file before publishing this project publicly.
