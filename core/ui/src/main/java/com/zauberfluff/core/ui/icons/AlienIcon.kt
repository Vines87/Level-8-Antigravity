package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val AlienIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Alien",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {
        // Blob Body (Green Wobbly Klecks)
        path(
            fill = SolidColor(Color(0xFF2EFE64)), // Bright Green
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 6f, // Thick outline
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 16f)
            // Wobbly top
            curveTo(68f, 10f, 82f, 26f, 84f, 44f)
            // Wobbly right and bottom
            curveTo(86f, 62f, 74f, 84f, 54f, 86f)
            curveTo(34f, 88f, 14f, 76f, 12f, 56f)
            // Wobbly left
            curveTo(10f, 36f, 28f, 22f, 48f, 16f)
            close()
        }

        // Small Antennae 1
        path(
            stroke = SolidColor(Color.Black),
            strokeLineWidth = 5f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(40f, 18f)
            curveTo(36f, 8f, 30f, 6f, 26f, 8f)
        }
        // Antennae 1 Ball
        path(fill = SolidColor(Color(0xFFFE2E9A)), stroke = SolidColor(Color.Black), strokeLineWidth = 3f) {
            moveTo(26f, 8f)
            curveTo(28f, 4f, 22f, 2f, 20f, 6f)
            curveTo(18f, 10f, 24f, 12f, 26f, 8f)
            close()
        }

        // Small Antennae 2
        path(stroke = SolidColor(Color.Black), strokeLineWidth = 5f, strokeLineCap = StrokeCap.Round) {
            moveTo(56f, 18f)
            curveTo(60f, 8f, 66f, 6f, 70f, 8f)
        }
        // Antennae 2 Ball
        path(fill = SolidColor(Color(0xFFFE2E9A)), stroke = SolidColor(Color.Black), strokeLineWidth = 3f) {
            moveTo(70f, 8f)
            curveTo(72f, 4f, 66f, 2f, 64f, 6f)
            curveTo(62f, 10f, 68f, 12f, 70f, 8f)
            close()
        }

        // Huge Eye 1 (Left)
        path(fill = SolidColor(Color.Black)) { // Left Eye
            moveTo(30f, 38f)
            curveTo(38f, 38f, 42f, 46f, 42f, 54f)
            curveTo(42f, 62f, 38f, 70f, 30f, 70f)
            curveTo(22f, 70f, 18f, 62f, 18f, 54f)
            curveTo(18f, 46f, 22f, 38f, 30f, 38f)
            close()
        }
        // Eye 1 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(32f, 44f); curveTo(34f, 44f, 36f, 46f, 36f, 48f); curveTo(36f, 50f, 34f, 52f, 32f, 52f); curveTo(30f, 52f, 28f, 50f, 28f, 48f); curveTo(28f, 46f, 30f, 44f, 32f, 44f); close()
            moveTo(26f, 56f); curveTo(27f, 56f, 28f, 57f, 28f, 58f); curveTo(28f, 59f, 27f, 60f, 26f, 60f); curveTo(25f, 60f, 24f, 59f, 24f, 58f); curveTo(24f, 57f, 25f, 56f, 26f, 56f); close()
        }

        // Huge Eye 2 (Middle, slightly higher)
        path(fill = SolidColor(Color.Black)) {
            moveTo(48f, 30f)
            curveTo(56f, 30f, 60f, 38f, 60f, 46f)
            curveTo(60f, 54f, 56f, 62f, 48f, 62f)
            curveTo(40f, 62f, 36f, 54f, 36f, 46f)
            curveTo(36f, 38f, 40f, 30f, 48f, 30f)
            close()
        }
        // Eye 2 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(50f, 36f); curveTo(52f, 36f, 54f, 38f, 54f, 40f); curveTo(54f, 42f, 52f, 44f, 50f, 44f); curveTo(48f, 44f, 46f, 42f, 46f, 40f); curveTo(46f, 38f, 48f, 36f, 50f, 36f); close()
            moveTo(44f, 48f); curveTo(45f, 48f, 46f, 49f, 46f, 50f); curveTo(46f, 51f, 45f, 52f, 44f, 52f); curveTo(43f, 52f, 42f, 51f, 42f, 50f); curveTo(42f, 49f, 43f, 48f, 44f, 48f); close()
        }

        // Huge Eye 3 (Right)
        path(fill = SolidColor(Color.Black)) {
            moveTo(66f, 38f)
            curveTo(74f, 38f, 78f, 46f, 78f, 54f)
            curveTo(78f, 62f, 74f, 70f, 66f, 70f)
            curveTo(58f, 70f, 54f, 62f, 54f, 54f)
            curveTo(54f, 46f, 58f, 38f, 66f, 38f)
            close()
        }
        // Eye 3 Reflections
        path(fill = SolidColor(Color.White)) {
            moveTo(68f, 44f); curveTo(70f, 44f, 72f, 46f, 72f, 48f); curveTo(72f, 50f, 70f, 52f, 68f, 52f); curveTo(66f, 52f, 64f, 50f, 64f, 48f); curveTo(64f, 46f, 66f, 44f, 68f, 44f); close()
            moveTo(62f, 56f); curveTo(63f, 56f, 64f, 57f, 64f, 58f); curveTo(64f, 59f, 63f, 60f, 62f, 60f); curveTo(61f, 60f, 60f, 59f, 60f, 58f); curveTo(60f, 57f, 61f, 56f, 62f, 56f); close()
        }

        // Tiny cute smile
        path(stroke = SolidColor(Color.Black), strokeLineWidth = 4f, strokeLineCap = StrokeCap.Round) {
            moveTo(44f, 72f)
            curveTo(46f, 76f, 50f, 76f, 52f, 72f)
        }
        
        // Cute blush marks
        path(fill = SolidColor(Color(0x88FF80AB))) { // Pinkish blush
            moveTo(18f, 66f); curveTo(22f, 66f, 24f, 68f, 24f, 70f); curveTo(24f, 72f, 22f, 74f, 18f, 74f); curveTo(14f, 74f, 12f, 72f, 12f, 70f); curveTo(12f, 68f, 14f, 66f, 18f, 66f); close()
            moveTo(78f, 66f); curveTo(82f, 66f, 84f, 68f, 84f, 70f); curveTo(84f, 72f, 82f, 74f, 78f, 74f); curveTo(74f, 74f, 72f, 72f, 72f, 70f); curveTo(72f, 68f, 74f, 66f, 78f, 66f); close()
        }
    }.build()
}
