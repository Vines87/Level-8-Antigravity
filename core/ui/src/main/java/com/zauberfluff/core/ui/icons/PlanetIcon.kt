package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PlanetIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Planet",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {
        // Thick Saturn-like Rings (Back Half)
        path(
            fill = SolidColor(Color(0xFFFFD54F)), // Yellowish ring
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(8f, 32f)
            curveTo(40f, 16f, 80f, 40f, 88f, 64f)
            curveTo(64f, 40f, 24f, 16f, 8f, 32f)
            close()
        }

        // Chubby Colorful Planet Body
        path(
            fill = SolidColor(Color(0xFFB388FF)), // Light Purple
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 16f)
            curveTo(80f, 16f, 84f, 48f, 80f, 64f)
            curveTo(72f, 88f, 40f, 88f, 24f, 76f)
            curveTo(12f, 56f, 16f, 16f, 48f, 16f)
            close()
        }

        // Some craters/spots
        path(fill = SolidColor(Color(0x66FFFFFF))) {
            moveTo(32f, 24f); curveTo(38f, 24f, 40f, 28f, 40f, 32f); curveTo(40f, 36f, 38f, 40f, 32f, 40f); curveTo(26f, 40f, 24f, 36f, 24f, 32f); curveTo(24f, 28f, 26f, 24f, 32f, 24f); close()
            moveTo(60f, 70f); curveTo(66f, 70f, 68f, 72f, 68f, 76f); curveTo(68f, 80f, 66f, 82f, 60f, 82f); curveTo(54f, 82f, 52f, 80f, 52f, 76f); curveTo(52f, 72f, 54f, 70f, 60f, 70f); close()
        }

        // Huge Eye 1 (Left)
        path(fill = SolidColor(Color.Black)) { 
            moveTo(34f, 44f)
            curveTo(42f, 44f, 46f, 52f, 46f, 60f)
            curveTo(46f, 68f, 42f, 76f, 34f, 76f)
            curveTo(26f, 76f, 22f, 68f, 22f, 60f)
            curveTo(22f, 52f, 26f, 44f, 34f, 44f)
            close()
        }
        // Eye 1 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(36f, 50f); curveTo(38f, 50f, 40f, 52f, 40f, 54f); curveTo(40f, 56f, 38f, 58f, 36f, 58f); curveTo(34f, 58f, 32f, 56f, 32f, 54f); curveTo(32f, 52f, 34f, 50f, 36f, 50f); close()
            moveTo(30f, 64f); curveTo(31f, 64f, 32f, 65f, 32f, 66f); curveTo(32f, 67f, 31f, 68f, 30f, 68f); curveTo(29f, 68f, 28f, 67f, 28f, 66f); curveTo(28f, 65f, 29f, 64f, 30f, 64f); close()
        }

        // Huge Eye 2 (Right)
        path(fill = SolidColor(Color.Black)) {
            moveTo(64f, 44f)
            curveTo(72f, 44f, 76f, 52f, 76f, 60f)
            curveTo(76f, 68f, 72f, 76f, 64f, 76f)
            curveTo(56f, 76f, 52f, 68f, 52f, 60f)
            curveTo(52f, 52f, 56f, 44f, 64f, 44f)
            close()
        }
        // Eye 2 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(66f, 50f); curveTo(68f, 50f, 70f, 52f, 70f, 54f); curveTo(70f, 56f, 68f, 58f, 66f, 58f); curveTo(64f, 58f, 62f, 56f, 62f, 54f); curveTo(62f, 52f, 64f, 50f, 66f, 50f); close()
            moveTo(60f, 64f); curveTo(61f, 64f, 62f, 65f, 62f, 66f); curveTo(62f, 67f, 61f, 68f, 60f, 68f); curveTo(59f, 68f, 58f, 67f, 58f, 66f); curveTo(58f, 65f, 59f, 64f, 60f, 64f); close()
        }

        // Cute smiling mouth
        path(stroke = SolidColor(Color.Black), strokeLineWidth = 4f, strokeLineCap = StrokeCap.Round) {
            moveTo(44f, 76f)
            curveTo(46f, 80f, 52f, 80f, 54f, 76f)
        }

        // Thick Saturn-like Rings (Front Half)
        path(
            fill = SolidColor(Color(0xFFFFD54F)), // Yellowish ring
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(88f, 64f)
            curveTo(80f, 80f, 24f, 64f, 8f, 32f)
            curveTo(24f, 48f, 72f, 72f, 88f, 64f)
            close()
        }
    }.build()
}
