package com.zauberfluff.core.ui

import android.view.HapticFeedbackConstants
import android.view.View
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.composed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

/**
 * A custom modifier that applies a bouncy "pulse" effect when pressed.
 * Ideal for kids interfaces as it gives immediate, playful visual feedback.
 */
fun Modifier.pulseClickable(
    enabled: Boolean = true,
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
            enabled = enabled,
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

// ---------------------------------------------------------------------------
// Karten-Flug: Spring-Eingleit-Animation für neu gezogene Karten
// ---------------------------------------------------------------------------

/**
 * Wraps [content] in an AnimatedVisibility that slides in from below with a
 * satisfying spring curve whenever [visible] turns true.
 */
@Composable
fun CardDrawAnimation(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMediumLow
            )
        )
    ) {
        content()
    }
}

// ---------------------------------------------------------------------------
// Missions-Hüpfen: Scale-Bounce-Modifier für ausgewählte Karten
// ---------------------------------------------------------------------------

/**
 * Applies a quick "bounce" scale animation to the element whenever [trigger]
 * switches to true. The card scales up then back to normal in ~500 ms.
 */
fun Modifier.cardBounce(trigger: Boolean): Modifier = composed {
    val scale by animateFloatAsState(
        targetValue = if (trigger) 1.35f else 1f,
        animationSpec = if (trigger) {
            keyframes {
                durationMillis = 500
                1.0f  at 0    using FastOutSlowInEasing
                1.35f at 150  using FastOutSlowInEasing
                0.85f at 300  using FastOutSlowInEasing
                1.0f  at 500
            }
        } else {
            tween(durationMillis = 200)
        },
        label = "CardBounce"
    )
    this.scale(scale)
}

// ---------------------------------------------------------------------------
// Glitzer-Partikel: Buntе Konfetti-Kreise für das Missions-Feuerwerk
// ---------------------------------------------------------------------------

private data class GlitterParticle(
    val x: Animatable<Float, *>,
    val y: Animatable<Float, *>,
    val alpha: Animatable<Float, *>,
    val color: Color,
    val radius: Float
)

private val glitterColors = listOf(
    Color(0xFFFFD700), // Gold
    Color(0xFFFF4081), // Pink
    Color(0xFF40C4FF), // Hellblau
    Color(0xFF69F0AE), // Mint
    Color(0xFFFF6E40), // Orange
    Color(0xFFEA80FC), // Lila
    Color(0xFFFFFF00), // Gelb
    Color(0xFF00E5FF), // Cyan
)

/**
 * Displays a burst of 30 colorful glitter circles when [trigger] turns true.
 * Each particle flies outward and fades out within ~800 ms.
 */
@Composable
fun GlitterParticleSystem(
    trigger: Boolean,
    modifier: Modifier = Modifier
) {
    val particles = remember { mutableStateListOf<GlitterParticle>() }

    LaunchedEffect(trigger) {
        if (!trigger) return@LaunchedEffect

        particles.clear()
        repeat(30) {
            val angle = Random.nextDouble(0.0, 360.0)
            val distance = Random.nextFloat() * 600f + 200f
            val startX = 0f
            val startY = 0f
            val endX = (cos(Math.toRadians(angle)) * distance).toFloat()
            val endY = (sin(Math.toRadians(angle)) * distance).toFloat()

            val xAnim = Animatable(startX)
            val yAnim = Animatable(startY)
            val alphaAnim = Animatable(1f)

            particles.add(
                GlitterParticle(
                    x = xAnim,
                    y = yAnim,
                    alpha = alphaAnim,
                    color = glitterColors.random(),
                    radius = Random.nextFloat() * 12f + 6f
                )
            )

            launch {
                launch { xAnim.animateTo(endX, tween(800)) }
                launch { yAnim.animateTo(endY, tween(800)) }
                delay(300)
                alphaAnim.animateTo(0f, tween(500))
            }
        }
    }

    Canvas(modifier = modifier) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        particles.forEach { p ->
            drawCircle(
                color = p.color.copy(alpha = p.alpha.value),
                radius = p.radius,
                center = Offset(centerX + p.x.value, centerY + p.y.value)
            )
        }
    }
}

// ---------------------------------------------------------------------------
// Sieg-Feier: Vollbild-Overlay mit Rakete + SENSATIONELL!
// ---------------------------------------------------------------------------

/**
 * Full-screen victory overlay: a semi-transparent dark background, a rocket
 * that flies from bottom to top, and the text "SENSATIONELL!" scaling in.
 *
 * Call with [isVisible] == true once [GameState.isGameOver] becomes true.
 */
@Composable
fun VictoryOverlay(
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = androidx.compose.animation.fadeIn(tween(400))
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Semi-opaque dark background
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.75f)
                    .then(Modifier.graphicsLayer {})
                    .run {
                        this
                    }
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawRect(Color(0xFF0D0D2B))
                }
            }

            // Glitter in background (always running)
            GlitterParticleSystem(
                trigger = isVisible,
                modifier = Modifier.fillMaxSize()
            )

            // Rocket flying upward
            val rocketOffsetY = remember { Animatable(600f) }
            LaunchedEffect(isVisible) {
                if (isVisible) {
                    rocketOffsetY.animateTo(
                        targetValue = -800f,
                        animationSpec = tween(durationMillis = 2000, easing = FastOutSlowInEasing)
                    )
                }
            }
            Text(
                text = "🚀",
                fontSize = 100.sp,
                modifier = Modifier.offset(y = rocketOffsetY.value.dp)
            )

            // SENSATIONELL! text
            val textScale = remember { Animatable(0f) }
            LaunchedEffect(isVisible) {
                if (isVisible) {
                    delay(300)
                    textScale.animateTo(
                        targetValue = 1f,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
                }
            }
            Text(
                text = "SENSATIONELL! 🎉",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFFFFD700),
                textAlign = TextAlign.Center,
                modifier = Modifier.scale(textScale.value)
            )
        }
    }
}
