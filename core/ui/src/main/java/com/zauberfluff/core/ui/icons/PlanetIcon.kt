package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Super-cute Planet icon 🪐
 * A soft pink/lilac planet with a sweet surprised face and a pastel Saturn ring.
 */
val PlanetIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Planet",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {

        // ── Ring (back half) ──────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFFB8A0DC)),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round
        ) {
            // Back arc of the ring
            moveTo(14f, 52f)
            curveTo(14f, 44f, 30f, 38f, 48f, 38f)
            curveTo(66f, 38f, 82f, 44f, 82f, 52f)
        }

        // ── Planet body ───────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFF8B4D9)),
            stroke = SolidColor(Color(0xFFD470A2)),
            strokeLineWidth = 2.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(18f, 48f)
            curveTo(18f, 27.5f, 31.4f, 11f, 48f, 11f)
            curveTo(64.6f, 11f, 78f, 27.5f, 78f, 48f)
            curveTo(78f, 68.5f, 64.6f, 85f, 48f, 85f)
            curveTo(31.4f, 85f, 18f, 68.5f, 18f, 48f)
            close()
        }

        // ── Soft pastel stripe on planet ──────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFFDD0E8)),
            strokeLineWidth = 0f
        ) {
            moveTo(22f, 42f)
            curveTo(24f, 36f, 34f, 32f, 48f, 32f)
            curveTo(62f, 32f, 72f, 36f, 74f, 42f)
            curveTo(72f, 48f, 62f, 52f, 48f, 52f)
            curveTo(34f, 52f, 24f, 48f, 22f, 42f)
            close()
        }

        // ── Big round eyes (white) ────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(30f, 45f)
            curveTo(30f, 39f, 34f, 34f, 39f, 34f)
            curveTo(44f, 34f, 48f, 39f, 48f, 45f)
            curveTo(48f, 51f, 44f, 56f, 39f, 56f)
            curveTo(34f, 56f, 30f, 51f, 30f, 45f)
            close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(48f, 45f)
            curveTo(48f, 39f, 52f, 34f, 57f, 34f)
            curveTo(62f, 34f, 66f, 39f, 66f, 45f)
            curveTo(66f, 51f, 62f, 56f, 57f, 56f)
            curveTo(52f, 56f, 48f, 51f, 48f, 45f)
            close()
        }

        // ── Irises (violet) ───────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF9B59B6)), strokeLineWidth = 0f) {
            moveTo(33f, 45f)
            curveTo(33f, 41f, 35.7f, 38f, 39f, 38f)
            curveTo(42.3f, 38f, 45f, 41f, 45f, 45f)
            curveTo(45f, 49f, 42.3f, 52f, 39f, 52f)
            curveTo(35.7f, 52f, 33f, 49f, 33f, 45f)
            close()
        }
        path(fill = SolidColor(Color(0xFF9B59B6)), strokeLineWidth = 0f) {
            moveTo(51f, 45f)
            curveTo(51f, 41f, 53.7f, 38f, 57f, 38f)
            curveTo(60.3f, 38f, 63f, 41f, 63f, 45f)
            curveTo(63f, 49f, 60.3f, 52f, 57f, 52f)
            curveTo(53.7f, 52f, 51f, 49f, 51f, 45f)
            close()
        }

        // ── Pupils ────────────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF1A0033)), strokeLineWidth = 0f) {
            moveTo(36f, 44f); curveTo(36f, 42f, 37.2f, 41f, 39f, 41f)
            curveTo(40.8f, 41f, 42f, 42f, 42f, 44f)
            curveTo(42f, 46f, 40.8f, 47f, 39f, 47f)
            curveTo(37.2f, 47f, 36f, 46f, 36f, 44f); close()
        }
        path(fill = SolidColor(Color(0xFF1A0033)), strokeLineWidth = 0f) {
            moveTo(54f, 44f); curveTo(54f, 42f, 55.2f, 41f, 57f, 41f)
            curveTo(58.8f, 41f, 60f, 42f, 60f, 44f)
            curveTo(60f, 46f, 58.8f, 47f, 57f, 47f)
            curveTo(55.2f, 47f, 54f, 46f, 54f, 44f); close()
        }

        // ── Eye sparkles ──────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(37f, 40f); curveTo(37f, 39f, 38f, 39f, 38f, 40f)
            curveTo(38f, 41f, 37f, 41f, 37f, 40f); close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(55f, 40f); curveTo(55f, 39f, 56f, 39f, 56f, 40f)
            curveTo(56f, 41f, 55f, 41f, 55f, 40f); close()
        }

        // ── Rosy cheeks ──────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0x55FF80BF)), strokeLineWidth = 0f) {
            moveTo(26f, 53f); curveTo(26f, 50f, 28f, 48f, 31f, 48f)
            curveTo(34f, 48f, 36f, 50f, 36f, 53f)
            curveTo(36f, 56f, 34f, 58f, 31f, 58f)
            curveTo(28f, 58f, 26f, 56f, 26f, 53f); close()
        }
        path(fill = SolidColor(Color(0x55FF80BF)), strokeLineWidth = 0f) {
            moveTo(60f, 53f); curveTo(60f, 50f, 62f, 48f, 65f, 48f)
            curveTo(68f, 48f, 70f, 50f, 70f, 53f)
            curveTo(70f, 56f, 68f, 58f, 65f, 58f)
            curveTo(62f, 58f, 60f, 56f, 60f, 53f); close()
        }

        // ── Tiny UwU smile ────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFF9B3070)),
            strokeLineWidth = 2.5f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(38f, 62f)
            curveTo(41f, 66f, 55f, 66f, 58f, 62f)
        }

        // ── Ring (front half) ─────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFFCDB4F5)),
            strokeLineWidth = 6f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(14f, 52f)
            curveTo(14f, 60f, 30f, 66f, 48f, 66f)
            curveTo(66f, 66f, 82f, 60f, 82f, 52f)
        }

    }.build()
}
