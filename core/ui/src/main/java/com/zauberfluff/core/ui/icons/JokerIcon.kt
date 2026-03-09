package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

/**
 * Super-cute Joker icon 🦄🛸
 * A glittery Unicorn UFO with a rainbow beam and twinkling stars all around.
 */
val JokerIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Joker",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {

        // ── Rainbow beam ──────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0x55FF1493)), strokeLineWidth = 0f) {
            moveTo(32f, 62f); lineTo(20f, 92f); lineTo(48f, 80f)
            lineTo(76f, 92f); lineTo(64f, 62f); close()
        }
        path(fill = SolidColor(Color(0x55FF8C00)), strokeLineWidth = 0f) {
            moveTo(35f, 62f); lineTo(25f, 88f); lineTo(48f, 78f)
            lineTo(71f, 88f); lineTo(61f, 62f); close()
        }
        path(fill = SolidColor(Color(0x55FFD600)), strokeLineWidth = 0f) {
            moveTo(38f, 62f); lineTo(30f, 85f); lineTo(48f, 76f)
            lineTo(66f, 85f); lineTo(58f, 62f); close()
        }
        path(fill = SolidColor(Color(0x5500C853)), strokeLineWidth = 0f) {
            moveTo(40f, 62f); lineTo(34f, 82f); lineTo(48f, 74f)
            lineTo(62f, 82f); lineTo(56f, 62f); close()
        }
        path(fill = SolidColor(Color(0x551565C0)), strokeLineWidth = 0f) {
            moveTo(42f, 62f); lineTo(38f, 79f); lineTo(48f, 72f)
            lineTo(58f, 79f); lineTo(54f, 62f); close()
        }

        // ── UFO disc body ─────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFF3E5F5)),
            stroke = SolidColor(Color(0xFFCE93D8)),
            strokeLineWidth = 2.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(10f, 56f)
            curveTo(10f, 48f, 26f, 42f, 48f, 42f)
            curveTo(70f, 42f, 86f, 48f, 86f, 56f)
            curveTo(86f, 64f, 70f, 70f, 48f, 70f)
            curveTo(26f, 70f, 10f, 64f, 10f, 56f)
            close()
        }

        // ── UFO glass dome ────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFE1F5FE)),
            stroke = SolidColor(Color(0xFF81D4FA)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(28f, 50f)
            curveTo(28f, 34f, 36f, 14f, 48f, 14f)
            curveTo(60f, 14f, 68f, 34f, 68f, 50f)
            close()
        }

        // ── Unicorn horn ──────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFFFD600)),
            stroke = SolidColor(Color(0xFFFFAB00)),
            strokeLineWidth = 1.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 2f); lineTo(52f, 16f); lineTo(44f, 16f); close()
        }
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFFFFAB00)),
            strokeLineWidth = 1.5f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(45f, 9f); lineTo(51f, 11f)
        }
        path(
            fill = SolidColor(Color(0x00000000)),
            stroke = SolidColor(Color(0xFFFFAB00)),
            strokeLineWidth = 1.5f,
            strokeLineCap = StrokeCap.Round
        ) {
            moveTo(45.5f, 14f); lineTo(50.5f, 15.5f)
        }

        // ── Eyes inside dome ──────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFF1565C0)), strokeLineWidth = 0f) {
            moveTo(37f, 44f); curveTo(37f, 41f, 39f, 39f, 41f, 39f)
            curveTo(43f, 39f, 45f, 41f, 45f, 44f)
            curveTo(45f, 47f, 43f, 49f, 41f, 49f)
            curveTo(39f, 49f, 37f, 47f, 37f, 44f); close()
        }
        path(fill = SolidColor(Color(0xFF1565C0)), strokeLineWidth = 0f) {
            moveTo(51f, 44f); curveTo(51f, 41f, 53f, 39f, 55f, 39f)
            curveTo(57f, 39f, 59f, 41f, 59f, 44f)
            curveTo(59f, 47f, 57f, 49f, 55f, 49f)
            curveTo(53f, 49f, 51f, 47f, 51f, 44f); close()
        }
        path(fill = SolidColor(Color(0xFF0D0D1A)), strokeLineWidth = 0f) {
            moveTo(39f, 43f); curveTo(39f, 42f, 40f, 41.5f, 41f, 41.5f)
            curveTo(42f, 41.5f, 43f, 42f, 43f, 43f)
            curveTo(43f, 44f, 42f, 44.5f, 41f, 44.5f)
            curveTo(40f, 44.5f, 39f, 44f, 39f, 43f); close()
        }
        path(fill = SolidColor(Color(0xFF0D0D1A)), strokeLineWidth = 0f) {
            moveTo(53f, 43f); curveTo(53f, 42f, 54f, 41.5f, 55f, 41.5f)
            curveTo(56f, 41.5f, 57f, 42f, 57f, 43f)
            curveTo(57f, 44f, 56f, 44.5f, 55f, 44.5f)
            curveTo(54f, 44.5f, 53f, 44f, 53f, 43f); close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(40f, 40.5f); curveTo(40f, 40f, 40.6f, 40f, 40.6f, 40.5f)
            curveTo(40.6f, 41f, 40f, 41f, 40f, 40.5f); close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF)), strokeLineWidth = 0f) {
            moveTo(54f, 40.5f); curveTo(54f, 40f, 54.6f, 40f, 54.6f, 40.5f)
            curveTo(54.6f, 41f, 54f, 41f, 54f, 40.5f); close()
        }

        // ── UFO disc shine ────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xAAFFFFFF)), strokeLineWidth = 0f) {
            moveTo(14f, 52f)
            curveTo(20f, 48f, 34f, 46f, 48f, 46f)
            curveTo(62f, 46f, 74f, 48f, 82f, 52f)
            curveTo(74f, 50f, 62f, 48f, 48f, 48f)
            curveTo(34f, 48f, 20f, 50f, 14f, 52f)
            close()
        }

        // ── Sparkle stars ─────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFF69B4)), strokeLineWidth = 0f) {
            moveTo(8f, 36f); lineTo(9.5f, 40f); lineTo(14f, 38f)
            lineTo(9.5f, 36f); close()
        }
        path(fill = SolidColor(Color(0xFFFF69B4)), strokeLineWidth = 0f) {
            moveTo(8f, 36f); lineTo(9.5f, 32f); lineTo(14f, 38f)
            lineTo(9.5f, 40f); close()
        }
        path(fill = SolidColor(Color(0xFF69F0AE)), strokeLineWidth = 0f) {
            moveTo(82f, 30f); lineTo(83.5f, 34f); lineTo(88f, 32f)
            lineTo(83.5f, 30f); close()
        }
        path(fill = SolidColor(Color(0xFF69F0AE)), strokeLineWidth = 0f) {
            moveTo(82f, 30f); lineTo(83.5f, 26f); lineTo(88f, 32f)
            lineTo(83.5f, 34f); close()
        }
        path(fill = SolidColor(Color(0xFFFFD600)), strokeLineWidth = 0f) {
            moveTo(18f, 20f); lineTo(19.5f, 24f); lineTo(24f, 22f)
            lineTo(19.5f, 20f); close()
        }
        path(fill = SolidColor(Color(0xFFFFD600)), strokeLineWidth = 0f) {
            moveTo(18f, 20f); lineTo(19.5f, 16f); lineTo(24f, 22f)
            lineTo(19.5f, 24f); close()
        }
        path(fill = SolidColor(Color(0xFFE040FB)), strokeLineWidth = 0f) {
            moveTo(72f, 10f); lineTo(73.5f, 14f); lineTo(78f, 12f)
            lineTo(73.5f, 10f); close()
        }
        path(fill = SolidColor(Color(0xFFE040FB)), strokeLineWidth = 0f) {
            moveTo(72f, 10f); lineTo(73.5f, 6f); lineTo(78f, 12f)
            lineTo(73.5f, 14f); close()
        }

        // ── Heart on disc ─────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFF1744)), strokeLineWidth = 0f) {
            moveTo(48f, 65f)
            curveTo(44f, 61f, 38f, 59f, 38f, 56f)
            curveTo(38f, 53f, 41f, 52f, 44f, 54f)
            lineTo(48f, 58f); lineTo(52f, 54f)
            curveTo(55f, 52f, 58f, 53f, 58f, 56f)
            curveTo(58f, 59f, 52f, 61f, 48f, 65f)
            close()
        }

    }.build()
}
