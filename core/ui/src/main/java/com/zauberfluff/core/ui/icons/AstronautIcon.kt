package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Super-cute Astronaut icon 🚀👨‍🚀
 * A chubby round helmet with huge shiny eyes and a tiny smile.
 */
val AstronautIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Astronaut",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {

        // ── Body / Suit (pale silver-white, very round) ──────────────────────
        path(
            fill = SolidColor(Color(0xFFE8EDF5)),
            fillAlpha = 1f,
            stroke = SolidColor(Color(0xFF9AAABF)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            // Big chubby oval body
            moveTo(24f, 60f)
            curveTo(24f, 82f, 36f, 90f, 48f, 90f)
            curveTo(60f, 90f, 72f, 82f, 72f, 60f)
            curveTo(72f, 50f, 66f, 44f, 48f, 44f)
            curveTo(30f, 44f, 24f, 50f, 24f, 60f)
            close()
        }

        // ── Helmet (round, light blue tint visor) ────────────────────────────
        path(
            fill = SolidColor(Color(0xFFD0E8FF)),
            fillAlpha = 1f,
            stroke = SolidColor(Color(0xFF7AABDA)),
            strokeLineWidth = 2.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            // Outer helmet circle – big & round ≈ circle at (48,38) r=30
            moveTo(18f, 38f)
            curveTo(18f, 20.3f, 31.4f, 6f, 48f, 6f)
            curveTo(64.6f, 6f, 78f, 20.3f, 78f, 38f)
            curveTo(78f, 55.7f, 64.6f, 70f, 48f, 70f)
            curveTo(31.4f, 70f, 18f, 55.7f, 18f, 38f)
            close()
        }

        // ── Helmet rim / collar ───────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFBBCCE0)),
            strokeLineWidth = 0f
        ) {
            moveTo(30f, 64f)
            curveTo(30f, 68f, 38f, 72f, 48f, 72f)
            curveTo(58f, 72f, 66f, 68f, 66f, 64f)
            curveTo(66f, 60f, 58f, 57f, 48f, 57f)
            curveTo(38f, 57f, 30f, 60f, 30f, 64f)
            close()
        }

        // ── Left big eye (white) ──────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(29f, 34f)
            curveTo(29f, 26f, 34f, 20f, 40.5f, 20f)
            curveTo(47f, 20f, 52f, 26f, 52f, 34f)
            curveTo(52f, 42f, 47f, 48f, 40.5f, 48f)
            curveTo(34f, 48f, 29f, 42f, 29f, 34f)
            close()
        }

        // ── Right big eye (white) ─────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(44f, 34f)
            curveTo(44f, 26f, 49f, 20f, 55.5f, 20f)
            curveTo(62f, 20f, 67f, 26f, 67f, 34f)
            curveTo(67f, 42f, 62f, 48f, 55.5f, 48f)
            curveTo(49f, 48f, 44f, 42f, 44f, 34f)
            close()
        }

        // ── Left iris (deep blue) ─────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF3A7BD5)), strokeLineWidth = 0f) {
            moveTo(33f, 34f)
            curveTo(33f, 29f, 36.4f, 25f, 40.5f, 25f)
            curveTo(44.6f, 25f, 48f, 29f, 48f, 34f)
            curveTo(48f, 39f, 44.6f, 43f, 40.5f, 43f)
            curveTo(36.4f, 43f, 33f, 39f, 33f, 34f)
            close()
        }

        // ── Right iris (deep blue) ────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF3A7BD5)), strokeLineWidth = 0f) {
            moveTo(48f, 34f)
            curveTo(48f, 29f, 51.4f, 25f, 55.5f, 25f)
            curveTo(59.6f, 25f, 63f, 29f, 63f, 34f)
            curveTo(63f, 39f, 59.6f, 43f, 55.5f, 43f)
            curveTo(51.4f, 43f, 48f, 39f, 48f, 34f)
            close()
        }

        // ── Pupils (black) ────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF1A1A2E)), strokeLineWidth = 0f) {
            moveTo(37f, 33f); curveTo(37f, 31f, 38.5f, 30f, 40.5f, 30f)
            curveTo(42.5f, 30f, 44f, 31f, 44f, 33f)
            curveTo(44f, 35f, 42.5f, 36f, 40.5f, 36f)
            curveTo(38.5f, 36f, 37f, 35f, 37f, 33f)
            close()
        }
        path(fill = SolidColor(Color(0xFF1A1A2E)), strokeLineWidth = 0f) {
            moveTo(52f, 33f); curveTo(52f, 31f, 53.5f, 30f, 55.5f, 30f)
            curveTo(57.5f, 30f, 59f, 31f, 59f, 33f)
            curveTo(59f, 35f, 57.5f, 36f, 55.5f, 36f)
            curveTo(53.5f, 36f, 52f, 35f, 52f, 33f)
            close()
        }

        // ── Sparkle highlights in eyes ────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(38f, 29f); curveTo(38f, 28f, 39f, 28f, 39f, 29f)
            curveTo(39f, 30f, 38f, 30f, 38f, 29f); close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(53f, 29f); curveTo(53f, 28f, 54f, 28f, 54f, 29f)
            curveTo(54f, 30f, 53f, 30f, 53f, 29f); close()
        }

        // ── Tiny smile ────────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFF5C3D2E)),
            strokeLineWidth = 2.5f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(40f, 52f)
            curveTo(42f, 55f, 50f, 55f, 56f, 52f)
        }

        // ── Suit badge (orange circle) ────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFF8C42)), strokeLineWidth = 0f) {
            moveTo(43f, 76f); curveTo(43f, 73f, 45f, 71f, 48f, 71f)
            curveTo(51f, 71f, 53f, 73f, 53f, 76f)
            curveTo(53f, 79f, 51f, 81f, 48f, 81f)
            curveTo(45f, 81f, 43f, 79f, 43f, 76f); close()
        }

        // ── Little gloves / hands ─────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFE8EDF5)),
            stroke = SolidColor(Color(0xFF9AAABF)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            // Left hand
            moveTo(18f, 72f); curveTo(12f, 70f, 10f, 76f, 16f, 78f)
            curveTo(22f, 80f, 26f, 76f, 18f, 72f); close()
        }
        path(
            fill = SolidColor(Color(0xFFE8EDF5)),
            stroke = SolidColor(Color(0xFF9AAABF)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            // Right hand
            moveTo(78f, 72f); curveTo(84f, 70f, 86f, 76f, 80f, 78f)
            curveTo(74f, 80f, 70f, 76f, 78f, 72f); close()
        }

    }.build()
}
