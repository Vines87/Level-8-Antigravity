package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val RocketIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Rocket",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {
        // Thruster Fire (Orange & Yellow)
        path(
            fill = SolidColor(Color(0xFFFF9800)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 4f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(36f, 76f)
            lineTo(32f, 92f)
            lineTo(48f, 84f)
            lineTo(64f, 92f)
            lineTo(60f, 76f)
            close()
        }
        path(fill = SolidColor(Color(0xFFFFEB3B))) {
            moveTo(40f, 76f)
            lineTo(42f, 86f)
            lineTo(48f, 80f)
            lineTo(54f, 86f)
            lineTo(56f, 76f)
            close()
        }

        // Rocket Fins (Red)
        // Left Fin
        path(
            fill = SolidColor(Color(0xFFFF5252)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(28f, 52f)
            curveTo(12f, 60f, 16f, 76f, 16f, 80f)
            lineTo(32f, 76f)
            close()
        }
        // Right Fin
        path(
            fill = SolidColor(Color(0xFFFF5252)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(68f, 52f)
            curveTo(84f, 60f, 80f, 76f, 80f, 80f)
            lineTo(64f, 76f)
            close()
        }

        // Chubby Rocket Main Body (Light Blue / Silverish)
        path(
            fill = SolidColor(Color(0xFFE0F7FA)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 8f)
            curveTo(76f, 24f, 80f, 64f, 64f, 76f)
            curveTo(32f, 76f, 16f, 64f, 28f, 16f)
            curveTo(32f, 12f, 40f, 8f, 48f, 8f)
            close()
        }

        // Bright Red Nose Cone
        path(
            fill = SolidColor(Color(0xFFFF5252)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 8f)
            curveTo(60f, 16f, 64f, 28f, 64f, 28f)
            lineTo(32f, 28f)
            curveTo(32f, 28f, 36f, 16f, 48f, 8f)
            close()
        }

        // Window (Porthole)
        path(
            fill = SolidColor(Color(0xFF00B0FF)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 32f)
            curveTo(56f, 32f, 60f, 36f, 60f, 44f)
            curveTo(60f, 52f, 56f, 56f, 48f, 56f)
            curveTo(40f, 56f, 36f, 52f, 36f, 44f)
            curveTo(36f, 36f, 40f, 32f, 48f, 32f)
            close()
        }
        // Glare on window
        path(stroke = SolidColor(Color.White), strokeLineWidth = 3f, strokeLineCap = StrokeCap.Round) {
            moveTo(42f, 38f)
            curveTo(44f, 36f, 48f, 36f, 50f, 38f)
        }

        // Cute Face ON the Rocket Body (below viewport)
        // Huge Eye 1 (Left)
        path(fill = SolidColor(Color.Black)) { 
            moveTo(40f, 60f)
            curveTo(44f, 60f, 46f, 64f, 46f, 68f)
            curveTo(46f, 72f, 44f, 72f, 40f, 72f)
            curveTo(36f, 72f, 34f, 68f, 34f, 68f)
            curveTo(34f, 64f, 36f, 60f, 40f, 60f)
            close()
        }
        // Eye 1 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(40f, 62f); curveTo(42f, 62f, 42f, 64f, 42f, 66f); curveTo(42f, 68f, 40f, 68f, 38f, 68f); curveTo(38f, 66f, 38f, 64f, 40f, 62f); close()
        }

        // Huge Eye 2 (Right)
        path(fill = SolidColor(Color.Black)) {
            moveTo(56f, 60f)
            curveTo(60f, 60f, 62f, 64f, 62f, 68f)
            curveTo(62f, 72f, 60f, 72f, 56f, 72f)
            curveTo(52f, 72f, 50f, 68f, 50f, 68f)
            curveTo(50f, 64f, 52f, 60f, 56f, 60f)
            close()
        }
        // Eye 2 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(56f, 62f); curveTo(58f, 62f, 58f, 64f, 58f, 66f); curveTo(58f, 68f, 56f, 68f, 54f, 68f); curveTo(54f, 66f, 54f, 64f, 56f, 62f); close()
        }

        // Cute smile
        path(stroke = SolidColor(Color.Black), strokeLineWidth = 3f, strokeLineCap = StrokeCap.Round) {
            moveTo(46f, 70f)
            curveTo(48f, 72f, 50f, 72f, 50f, 70f)
        }
    }.build()
}
