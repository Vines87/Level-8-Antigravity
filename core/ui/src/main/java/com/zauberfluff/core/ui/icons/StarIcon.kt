package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Super-cute Star icon ⭐
 * A golden five-pointed star with rosy cheeks and a happy face.
 */
val StarIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Star",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {

        // ── Star body (golden) ────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFFFD600)),
            stroke = SolidColor(Color(0xFFE6AC00)),
            strokeLineWidth = 2.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            // 5-point star, center (48,50), "fat" rounded-feeling points
            moveTo(48f, 6f)
            lineTo(58f, 36f)
            lineTo(90f, 36f)
            lineTo(65f, 56f)
            lineTo(75f, 88f)
            lineTo(48f, 70f)
            lineTo(21f, 88f)
            lineTo(31f, 56f)
            lineTo(6f, 36f)
            lineTo(38f, 36f)
            close()
        }

        // ── Inner lighter highlight (give it volume) ──────────────────────────
        path(fill = SolidColor(Color(0xFFFFF176)), strokeLineWidth = 0f) {
            moveTo(48f, 14f)
            lineTo(56f, 38f)
            lineTo(84f, 38f)
            lineTo(62f, 54f)
            lineTo(70f, 80f)
            lineTo(48f, 66f)
            lineTo(26f, 80f)
            lineTo(34f, 54f)
            lineTo(12f, 38f)
            lineTo(40f, 38f)
            close()
        }

        // ── Left rosy cheek ───────────────────────────────────────────────────
        path(fill = SolidColor(Color(0x88FF7043)), strokeLineWidth = 0f) {
            moveTo(22f, 56f); curveTo(22f, 52f, 25f, 49f, 29f, 49f)
            curveTo(33f, 49f, 36f, 52f, 36f, 56f)
            curveTo(36f, 60f, 33f, 63f, 29f, 63f)
            curveTo(25f, 63f, 22f, 60f, 22f, 56f); close()
        }

        // ── Right rosy cheek ──────────────────────────────────────────────────
        path(fill = SolidColor(Color(0x88FF7043)), strokeLineWidth = 0f) {
            moveTo(60f, 56f); curveTo(60f, 52f, 63f, 49f, 67f, 49f)
            curveTo(71f, 49f, 74f, 52f, 74f, 56f)
            curveTo(74f, 60f, 71f, 63f, 67f, 63f)
            curveTo(63f, 63f, 60f, 60f, 60f, 56f); close()
        }

        // ── Left eye ─────────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF3E2723)), strokeLineWidth = 0f) {
            moveTo(36f, 46f); curveTo(36f, 43f, 37.8f, 41f, 40f, 41f)
            curveTo(42.2f, 41f, 44f, 43f, 44f, 46f)
            curveTo(44f, 49f, 42.2f, 51f, 40f, 51f)
            curveTo(37.8f, 51f, 36f, 49f, 36f, 46f); close()
        }

        // ── Right eye ────────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF3E2723)), strokeLineWidth = 0f) {
            moveTo(52f, 46f); curveTo(52f, 43f, 53.8f, 41f, 56f, 41f)
            curveTo(58.2f, 41f, 60f, 43f, 60f, 46f)
            curveTo(60f, 49f, 58.2f, 51f, 56f, 51f)
            curveTo(53.8f, 51f, 52f, 49f, 52f, 46f); close()
        }

        // ── Eye sparkles ──────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(37.5f, 44f); curveTo(37.5f, 43f, 38.5f, 43f, 38.5f, 44f)
            curveTo(38.5f, 45f, 37.5f, 45f, 37.5f, 44f); close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(53.5f, 44f); curveTo(53.5f, 43f, 54.5f, 43f, 54.5f, 44f)
            curveTo(54.5f, 45f, 53.5f, 45f, 53.5f, 44f); close()
        }

        // ── Big happy smile ───────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFF5D4037)),
            strokeLineWidth = 3f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(36f, 56f)
            curveTo(40f, 63f, 56f, 63f, 60f, 56f)
        }

        // ── Tiny sparkle stars around ─────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFEB3B)), strokeLineWidth = 0f) {
            // Small star top-right corner  ★
            moveTo(78f, 12f); lineTo(79.5f, 16.5f); lineTo(84f, 18f)
            lineTo(79.5f, 19.5f); lineTo(78f, 24f); lineTo(76.5f, 19.5f)
            lineTo(72f, 18f); lineTo(76.5f, 16.5f); close()
        }
        path(fill = SolidColor(Color(0xFFFFEB3B)), strokeLineWidth = 0f) {
            // Small star bottom-left ★
            moveTo(14f, 76f); lineTo(15.2f, 79.3f); lineTo(18.5f, 80.5f)
            lineTo(15.2f, 81.7f); lineTo(14f, 85f); lineTo(12.8f, 81.7f)
            lineTo(9.5f, 80.5f); lineTo(12.8f, 79.3f); close()
        }

    }.build()
}
