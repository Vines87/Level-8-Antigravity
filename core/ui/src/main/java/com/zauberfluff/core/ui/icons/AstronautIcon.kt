package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AstronautIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Astronaut",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {
        // Chubby Body
        path(
            fill = SolidColor(Color.White),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(24f, 80f)
            curveTo(16f, 80f, 20f, 48f, 48f, 48f)
            curveTo(76f, 48f, 80f, 80f, 72f, 80f)
            curveTo(64f, 80f, 60f, 64f, 48f, 64f)
            curveTo(36f, 64f, 32f, 80f, 24f, 80f)
            close()
        }

        // Chubby Arms
        // Left Arm
        path(
            fill = SolidColor(Color.White),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(28f, 52f)
            curveTo(12f, 48f, 8f, 64f, 16f, 72f)
            curveTo(20f, 72f, 24f, 64f, 28f, 56f)
            close()
        }
        // Right Arm (Waving)
        path(
            fill = SolidColor(Color.White),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(68f, 52f)
            curveTo(84f, 32f, 96f, 48f, 80f, 64f)
            curveTo(76f, 64f, 72f, 60f, 68f, 56f)
            close()
        }

        // Huge Round Helmet
        path(
            fill = SolidColor(Color.White),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 12f)
            curveTo(72f, 12f, 76f, 36f, 76f, 48f)
            curveTo(76f, 60f, 72f, 60f, 48f, 60f)
            curveTo(24f, 60f, 20f, 60f, 20f, 48f)
            curveTo(20f, 36f, 24f, 12f, 48f, 12f)
            close()
        }

        // Helmet Visor (Light Blue)
        path(
            fill = SolidColor(Color(0xFF80D8FF)), // Light Blue Visor
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 4f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 20f)
            curveTo(68f, 20f, 68f, 36f, 68f, 44f)
            curveTo(68f, 52f, 68f, 52f, 48f, 52f)
            curveTo(28f, 52f, 28f, 52f, 28f, 44f)
            curveTo(28f, 36f, 28f, 20f, 48f, 20f)
            close()
        }

        // Visor Glare (Top Right)
        path(
            stroke = SolidColor(Color.White),
            strokeLineWidth = 4f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(62f, 24f)
            curveTo(64f, 28f, 64f, 32f, 62f, 36f)
        }

        // Huge Eye 1 (Inside Visor, Left)
        path(fill = SolidColor(Color.Black)) { 
            moveTo(36f, 32f)
            curveTo(42f, 32f, 44f, 38f, 44f, 44f)
            curveTo(44f, 50f, 42f, 50f, 36f, 50f)
            curveTo(30f, 50f, 28f, 50f, 28f, 44f)
            curveTo(28f, 38f, 30f, 32f, 36f, 32f)
            close()
        }
        // Eye 1 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(36f, 36f); curveTo(38f, 36f, 38f, 38f, 38f, 40f); curveTo(38f, 42f, 38f, 42f, 36f, 42f); curveTo(34f, 42f, 34f, 42f, 34f, 40f); curveTo(34f, 38f, 34f, 36f, 36f, 36f); close()
        }

        // Huge Eye 2 (Inside Visor, Right)
        path(fill = SolidColor(Color.Black)) {
            moveTo(60f, 32f)
            curveTo(66f, 32f, 68f, 38f, 68f, 44f)
            curveTo(68f, 50f, 66f, 50f, 60f, 50f)
            curveTo(54f, 50f, 52f, 50f, 52f, 44f)
            curveTo(52f, 38f, 54f, 32f, 60f, 32f)
            close()
        }
        // Eye 2 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(60f, 36f); curveTo(62f, 36f, 62f, 38f, 62f, 40f); curveTo(62f, 42f, 62f, 42f, 60f, 42f); curveTo(58f, 42f, 58f, 42f, 58f, 40f); curveTo(58f, 38f, 58f, 36f, 60f, 36f); close()
        }

        // Cute blush marks inside visor
        path(fill = SolidColor(Color(0x88FF80AB))) { // Pinkish blush
            moveTo(30f, 46f); curveTo(34f, 46f, 34f, 48f, 34f, 50f); curveTo(34f, 52f, 30f, 52f, 30f, 50f); curveTo(26f, 50f, 26f, 48f, 30f, 46f); close()
            moveTo(66f, 46f); curveTo(70f, 46f, 70f, 48f, 70f, 50f); curveTo(70f, 52f, 66f, 52f, 66f, 50f); curveTo(62f, 50f, 62f, 48f, 66f, 46f); close()
        }

        // Helmet Buttons / Decals (Colorful)
        path(fill = SolidColor(Color(0xFFFF5252)), stroke = SolidColor(Color.Black), strokeLineWidth = 2f) { // Red button
            moveTo(40f, 68f)
            curveTo(44f, 68f, 44f, 72f, 44f, 72f)
            curveTo(44f, 76f, 40f, 76f, 40f, 76f)
            curveTo(36f, 76f, 36f, 72f, 36f, 72f)
            close()
        }
        path(fill = SolidColor(Color(0xFF00E5FF)), stroke = SolidColor(Color.Black), strokeLineWidth = 2f) { // Blue button
            moveTo(56f, 68f)
            curveTo(60f, 68f, 60f, 72f, 60f, 72f)
            curveTo(60f, 76f, 56f, 76f, 56f, 76f)
            curveTo(52f, 76f, 52f, 72f, 52f, 72f)
            close()
        }

    }.build()
}
