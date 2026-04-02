package com.example.tictactoe11

import android.view.HapticFeedbackConstants
import android.view.View

class HapticFeedbackManager {
    
    /**
     * Trigger haptic feedback with different patterns
     */
    fun triggerHapticFeedback(view: View?, feedbackType: HapticFeedbackType) {
        view?.let {
            val feedbackConstant = when (feedbackType) {
                HapticFeedbackType.LIGHT_CLICK -> HapticFeedbackConstants.VIRTUAL_KEY
                HapticFeedbackType.MEDIUM_CLICK -> HapticFeedbackConstants.KEYBOARD_TAP
                HapticFeedbackType.HEAVY_CLICK -> HapticFeedbackConstants.LONG_PRESS
                HapticFeedbackType.TICK -> HapticFeedbackConstants.CLOCK_TICK
                HapticFeedbackType.SUCCESS -> HapticFeedbackConstants.VIRTUAL_KEY_RELEASE
            }
            it.performHapticFeedback(feedbackConstant, HapticFeedbackConstants.FLAG_IGNORE_VIEW_SETTING)
        }
    }
}

enum class HapticFeedbackType {
    LIGHT_CLICK,      // Move/cell tap
    MEDIUM_CLICK,     // Settings button
    HEAVY_CLICK,      // Play Again button
    TICK,             // General taps
    SUCCESS           // Win/Draw
}
