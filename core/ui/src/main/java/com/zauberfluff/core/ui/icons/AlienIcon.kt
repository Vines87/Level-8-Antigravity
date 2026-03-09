package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Super-cute Alien icon 👽
 * A bright green blob with three big sparkly eyes and a friendly waving hand.
 */
val AlienIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Alien",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {

        // ── Body / blob ───────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFF69F0AE)),
            stroke = SolidColor(Color(0xFF00C853)),
            strokeLineWidth = 2.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            // Chubby rounded blob
            moveTo(20f, 50f)
            curveTo(18f, 32f, 30f, 10f, 48f, 10f)
            curveTo(66f, 10f, 78f, 32f, 76f, 50f)
            curveTo(76f, 68f, 63f, 82f, 48f, 82f)
            curveTo(33f, 82f, 20f, 68f, 20f, 50f)
            close()
        }

        // ── Belly lighter patch ───────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFB9F6CA)), strokeLineWidth = 0f) {
            moveTo(32f, 58f)
            curveTo(32f, 50f, 39f, 44f, 48f, 44f)
            curveTo(57f, 44f, 64f, 50f, 64f, 58f)
            curveTo(64f, 66f, 57f, 72f, 48f, 72f)
            curveTo(39f, 72f, 32f, 66f, 32f, 58f)
            close()
        }

        // ── Left eye (white) ──────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(22f, 38f)
            curveTo(22f, 30f, 26.5f, 24f, 32f, 24f)
            curveTo(37.5f, 24f, 42f, 30f, 42f, 38f)
            curveTo(42f, 46f, 37.5f, 52f, 32f, 52f)
            curveTo(26.5f, 52f, 22f, 46f, 22f, 38f)
            close()
        }

        // ── Centre eye (white, slightly larger) ───────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(37f, 34f)
            curveTo(37f, 25f, 42f, 18f, 48f, 18f)
            curveTo(54f, 18f, 59f, 25f, 59f, 34f)
            curveTo(59f, 43f, 54f, 50f, 48f, 50f)
            curveTo(42f, 50f, 37f, 43f, 37f, 34f)
            close()
        }

        // ── Right eye (white) ─────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(54f, 38f)
            curveTo(54f, 30f, 58.5f, 24f, 64f, 24f)
            curveTo(69.5f, 24f, 74f, 30f, 74f, 38f)
            curveTo(74f, 46f, 69.5f, 52f, 64f, 52f)
            curveTo(58.5f, 52f, 54f, 46f, 54f, 38f)
            close()
        }

        // ── Left iris ─────────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF1565C0)), strokeLineWidth = 0f) {
            moveTo(25f, 38f); curveTo(25f, 33f, 28f, 29f, 32f, 29f)
            curveTo(36f, 29f, 39f, 33f, 39f, 38f)
            curveTo(39f, 43f, 36f, 47f, 32f, 47f)
            curveTo(28f, 47f, 25f, 43f, 25f, 38f); close()
        }

        // ── Centre iris (teal) ────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF00ACC1)), strokeLineWidth = 0f) {
            moveTo(40f, 34f); curveTo(40f, 28f, 43.6f, 23f, 48f, 23f)
            curveTo(52.4f, 23f, 56f, 28f, 56f, 34f)
            curveTo(56f, 40f, 52.4f, 45f, 48f, 45f)
            curveTo(43.6f, 45f, 40f, 40f, 40f, 34f); close()
        }

        // ── Right iris ────────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF1565C0)), strokeLineWidth = 0f) {
            moveTo(57f, 38f); curveTo(57f, 33f, 60f, 29f, 64f, 29f)
            curveTo(68f, 29f, 71f, 33f, 71f, 38f)
            curveTo(71f, 43f, 68f, 47f, 64f, 47f)
            curveTo(60f, 47f, 57f, 43f, 57f, 38f); close()
        }

        // ── Pupils ────────────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF0D0D1A)), strokeLineWidth = 0f) {
            moveTo(29f, 37f); curveTo(29f, 35f, 30.2f, 34f, 32f, 34f)
            curveTo(33.8f, 34f, 35f, 35f, 35f, 37f)
            curveTo(35f, 39f, 33.8f, 40f, 32f, 40f)
            curveTo(30.2f, 40f, 29f, 39f, 29f, 37f); close()
        }
        path(fill = SolidColor(Color(0xFF0D0D1A)), strokeLineWidth = 0f) {
            moveTo(44f, 33f); curveTo(44f, 31f, 45.7f, 30f, 48f, 30f)
            curveTo(50.3f, 30f, 52f, 31f, 52f, 33f)
            curveTo(52f, 35f, 50.3f, 36f, 48f, 36f)
            curveTo(45.7f, 36f, 44f, 35f, 44f, 33f); close()
        }
        path(fill = SolidColor(Color(0xFF0D0D1A)), strokeLineWidth = 0f) {
            moveTo(61f, 37f); curveTo(61f, 35f, 62.2f, 34f, 64f, 34f)
            curveTo(65.8f, 34f, 67f, 35f, 67f, 37f)
            curveTo(67f, 39f, 65.8f, 40f, 64f, 40f)
            curveTo(62.2f, 40f, 61f, 39f, 61f, 37f); close()
        }

        // ── Eye sparkles ──────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(30f, 33f); curveTo(30f, 32f, 31f, 32f, 31f, 33f)
            curveTo(31f, 34f, 30f, 34f, 30f, 33f); close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(45f, 29f); curveTo(45f, 28f, 46f, 28f, 46f, 29f)
            curveTo(46f, 30f, 45f, 30f, 45f, 29f); close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(62f, 33f); curveTo(62f, 32f, 63f, 32f, 63f, 33f)
            curveTo(63f, 34f, 62f, 34f, 62f, 33f); close()
        }

        // ── Small antenna left ────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFF00C853)),
            strokeLineWidth = 3f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(34f, 12f); lineTo(28f, 2f)
        }
        path(fill = SolidColor(Color(0xFFFFD600)), strokeLineWidth = 0f) {
            moveTo(24f, 2f); curveTo(24f, -0.3f, 28f, -2f, 28f, 2f)
            curveTo(28f, 5f, 24f, 5f, 24f, 2f); close()
        }

        // ── Small antenna right ───────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFF00C853)),
            strokeLineWidth = 3f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(62f, 12f); lineTo(68f, 2f)
        }
        path(fill = SolidColor(Color(0xFFFFD600)), strokeLineWidth = 0f) {
            moveTo(64f, 0f); curveTo(68f, -1f, 72f, 2f, 68f, 4f)
            curveTo(64f, 5f, 63f, 2f, 64f, 0f); close()
        }

        // ── Friendly smile ────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFF00C853)),
            strokeLineWidth = 3f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(36f, 64f); curveTo(40f, 70f, 56f, 70f, 60f, 64f)
        }

        // ── Waving arm / hand ─────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFF00C853)),
            strokeLineWidth = 5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(72f, 58f)
            curveTo(80f, 54f, 88f, 48f, 84f, 40f)
        }

        // ── Hand blob ─────────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF69F0AE)), strokeLineWidth = 0f) {
            moveTo(80f, 36f); curveTo(86f, 34f, 92f, 38f, 88f, 43f)
            curveTo(84f, 48f, 78f, 44f, 80f, 36f); close()
        }

    }.build()
}
