package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val StarIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Star",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {
        // Fat Star Body
        // Approximating a rounded fat 5-pointed star
        path(
            fill = SolidColor(Color(0xFFFFEA00)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 4f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 8f) // Top point
            curveTo(52f, 8f, 54f, 30f, 58f, 34f)
            curveTo(76f, 34f, 88f, 38f, 88f, 44f) // Right point
            curveTo(88f, 48f, 70f, 58f, 68f, 64f)
            curveTo(74f, 82f, 72f, 88f, 66f, 88f) // Bottom right point
            curveTo(62f, 88f, 50f, 76f, 48f, 76f)
            curveTo(46f, 76f, 34f, 88f, 30f, 88f) // Bottom left point
            curveTo(24f, 88f, 22f, 82f, 28f, 64f)
            curveTo(26f, 58f, 8f, 48f, 8f, 44f) // Left point
            curveTo(8f, 38f, 20f, 34f, 38f, 34f)
            curveTo(42f, 30f, 44f, 8f, 48f, 8f)
            close()
        }

        // Left Eye (Black)
        path(fill = SolidColor(Color.Black)) {
            moveTo(36f, 40f)
            curveTo(42f, 40f, 44f, 44f, 44f, 50f)
            curveTo(44f, 56f, 42f, 60f, 36f, 60f)
            curveTo(30f, 60f, 28f, 56f, 28f, 50f)
            curveTo(28f, 44f, 30f, 40f, 36f, 40f)
            close()
        }
        // Left Reflection
        path(fill = SolidColor(Color.White)) {
            moveTo(36f, 44f)
            curveTo(38f, 44f, 40f, 46f, 40f, 48f)
            curveTo(40f, 50f, 38f, 52f, 36f, 52f)
            curveTo(34f, 52f, 32f, 50f, 32f, 48f)
            curveTo(32f, 46f, 34f, 44f, 36f, 44f)
            close()
        }

        // Right Eye (Black)
        path(fill = SolidColor(Color.Black)) {
            moveTo(60f, 40f)
            curveTo(66f, 40f, 68f, 44f, 68f, 50f)
            curveTo(68f, 56f, 66f, 60f, 60f, 60f)
            curveTo(54f, 60f, 52f, 56f, 52f, 50f)
            curveTo(52f, 44f, 54f, 40f, 60f, 40f)
            close()
        }
        // Right Reflection
        path(fill = SolidColor(Color.White)) {
            moveTo(60f, 44f)
            curveTo(62f, 44f, 64f, 46f, 64f, 48f)
            curveTo(64f, 50f, 62f, 52f, 60f, 52f)
            curveTo(58f, 52f, 56f, 50f, 56f, 48f)
            curveTo(56f, 46f, 58f, 44f, 60f, 44f)
            close()
        }

        // Laughing open mouth
        path(
            fill = SolidColor(Color(0xFFD32F2F)),
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 4f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(40f, 64f)
            lineTo(56f, 64f)
            curveTo(56f, 74f, 40f, 74f, 40f, 64f)
            close()
        }
        // Tongue
        path(fill = SolidColor(Color(0xFFFF5252))) {
            moveTo(44f, 68f)
            curveTo(48f, 64f, 52f, 68f, 52f, 70f)
            curveTo(48f, 72f, 44f, 70f, 44f, 68f)
            close()
        }
    }.build()
}
