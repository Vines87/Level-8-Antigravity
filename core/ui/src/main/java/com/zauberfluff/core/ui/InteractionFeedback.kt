package com.zauberfluff.core.ui

import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView

/**
 * A custom modifier that applies a bouncy "pulse" effect when pressed.
 * Ideal for kids interfaces as it gives immediate, playful visual feedback.
 */
fun Modifier.pulseClickable(
    interactionSource: MutableInteractionSource? = null,
    onClick: () -> Unit
): Modifier = composed {
    val actualInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val isPressed by actualInteractionSource.collectIsPressedAsState()
    
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.9f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "PulseEffect"
    )

    // Trigger haptic feedback on press down
    val view = LocalView.current
    if (isPressed) {
        view.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
    }

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = actualInteractionSource,
            indication = null, // Disable default ripple for the pulse effect
            onClick = {
                // Secondary haptic on release/click
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY)
                onClick()
            }
        )
}

/**
 * Utility to manually trigger haptic feedback without a click modifier.
 */
object HapticFeedbackHelper {
    fun triggerSuccessFeedback(view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
    }
    
    fun triggerErrorFeedback(view: View) {
        view.performHapticFeedback(HapticFeedbackConstants.REJECT)
    }
}
