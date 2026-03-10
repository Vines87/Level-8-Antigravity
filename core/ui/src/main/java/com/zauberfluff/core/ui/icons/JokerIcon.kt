package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val JokerIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Joker",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {
        // We simulate a rainbow layout by having a base heart and layered smaller stripes, 
        // but for a pure vector without complex clips, we can draw a big chubby heart 
        // with vibrant base color and some rainbow sparkle lines, then add the huge eyes!

        // Base Thick Heart Outline & Pink Fill
        path(
            fill = SolidColor(Color(0xFFFF4081)), // Vibrant Pink
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f, // Thick outline
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 32f)
            // Left lobe
            curveTo(48f, 16f, 22f, 16f, 14f, 34f)
            curveTo(6f, 52f, 26f, 72f, 48f, 88f)
            // Right lobe
            curveTo(70f, 72f, 90f, 52f, 82f, 34f)
            curveTo(74f, 16f, 48f, 16f, 48f, 32f)
            close()
        }

        // Rainbow Stripes (Overlay on top of heart, roughly fitting the shape)
        // Yellow Stripe
        path(stroke = SolidColor(Color(0xFFFFEA00)), strokeLineWidth = 8f, strokeLineCap = StrokeCap.Round) {
            moveTo(20f, 40f)
            curveTo(40f, 30f, 60f, 50f, 76f, 40f)
        }
        // Cyan Stripe
        path(stroke = SolidColor(Color(0xFF00E5FF)), strokeLineWidth = 8f, strokeLineCap = StrokeCap.Round) {
            moveTo(24f, 56f)
            curveTo(44f, 46f, 64f, 66f, 72f, 56f)
        }
        
        // Huge Eye 1 (Left)
        path(fill = SolidColor(Color.Black)) { // Left Eye
            moveTo(32f, 42f)
            // Tilted oval
            curveTo(40f, 40f, 42f, 48f, 40f, 56f)
            curveTo(38f, 64f, 30f, 64f, 28f, 56f)
            curveTo(26f, 48f, 24f, 44f, 32f, 42f)
            close()
        }
        // Eye 1 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(32f, 46f); curveTo(34f, 46f, 36f, 48f, 36f, 50f); curveTo(36f, 52f, 34f, 54f, 32f, 54f); curveTo(30f, 54f, 28f, 52f, 28f, 50f); curveTo(28f, 48f, 30f, 46f, 32f, 46f); close()
            moveTo(36f, 56f); curveTo(37f, 55f, 38f, 56f, 38f, 57f); curveTo(38f, 58f, 37f, 59f, 36f, 59f); curveTo(35f, 59f, 34f, 58f, 34f, 57f); curveTo(34f, 56f, 35f, 55f, 36f, 56f); close()
        }

        // Huge Eye 2 (Right)
        path(fill = SolidColor(Color.Black)) {
            moveTo(64f, 42f)
            curveTo(56f, 40f, 54f, 48f, 56f, 56f)
            curveTo(58f, 64f, 66f, 64f, 68f, 56f)
            curveTo(70f, 48f, 72f, 44f, 64f, 42f)
            close()
        }
        // Eye 2 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(64f, 46f); curveTo(62f, 46f, 60f, 48f, 60f, 50f); curveTo(60f, 52f, 62f, 54f, 64f, 54f); curveTo(66f, 54f, 68f, 52f, 68f, 50f); curveTo(68f, 48f, 66f, 46f, 64f, 46f); close()
            moveTo(60f, 56f); curveTo(59f, 55f, 58f, 56f, 58f, 57f); curveTo(58f, 58f, 59f, 59f, 60f, 59f); curveTo(61f, 59f, 62f, 58f, 62f, 57f); curveTo(62f, 56f, 61f, 55f, 60f, 56f); close()
        }

        // Big Laughing Mouth
        path(fill = SolidColor(Color.Black), stroke = SolidColor(Color.Black), strokeLineWidth = 2f, strokeLineCap = StrokeCap.Round, strokeLineJoin = StrokeJoin.Round) {
            moveTo(40f, 64f)
            curveTo(40f, 72f, 56f, 72f, 56f, 64f)
            lineTo(40f, 64f)
            close()
        }
        // Tongue
        path(fill = SolidColor(Color(0xFFFF5252))) {
            moveTo(44f, 68f)
            curveTo(44f, 70f, 52f, 70f, 52f, 68f)
            close()
        }

        // Star Sparkles (Glitzer) around the heart
        path(fill = SolidColor(Color(0xFFFFC107)), stroke = SolidColor(Color.Black), strokeLineWidth = 2f) {
            // Sparkle 1 Top Left
            moveTo(16f, 10f); lineTo(18f, 16f); lineTo(24f, 18f); lineTo(18f, 20f); lineTo(16f, 26f); lineTo(14f, 20f); lineTo(8f, 18f); lineTo(14f, 16f); close()
            // Sparkle 2 Top Right
            moveTo(80f, 14f); lineTo(81f, 19f); lineTo(86f, 20f); lineTo(81f, 21f); lineTo(80f, 26f); lineTo(79f, 21f); lineTo(74f, 20f); lineTo(79f, 19f); close()
        }
    }.build()
}
