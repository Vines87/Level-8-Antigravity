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
 * Super-cute Rocket icon 🚀
 * A chubby, toy-like rocket in red & white with a porthole window.
 */
val RocketIcon: ImageVector by lazy {
    ImageVector.Builder(
        name = "Rocket",
        defaultWidth = 96.dp,
        defaultHeight = 96.dp,
        viewportWidth = 96f,
        viewportHeight = 96f
    ).apply {

        // ── Flame glow underneath ─────────────────────────────────────────────
        path(fill = SolidColor(Color(0x55FFCC00)), strokeLineWidth = 0f) {
            moveTo(36f, 88f)
            curveTo(36f, 80f, 42f, 74f, 48f, 74f)
            curveTo(54f, 74f, 60f, 80f, 60f, 88f)
            curveTo(60f, 92f, 54f, 94f, 48f, 94f)
            curveTo(42f, 94f, 36f, 92f, 36f, 88f)
            close()
        }

        // ── Flame (inner orange/yellow) ───────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFF6B35)), strokeLineWidth = 0f) {
            moveTo(42f, 82f)
            curveTo(43f, 77f, 46f, 74f, 48f, 74f)
            curveTo(50f, 74f, 53f, 77f, 54f, 82f)
            curveTo(54f, 87f, 51f, 90f, 48f, 90f)
            curveTo(45f, 90f, 42f, 87f, 42f, 82f)
            close()
        }
        path(fill = SolidColor(Color(0xFFFFD600)), strokeLineWidth = 0f) {
            moveTo(44f, 84f)
            curveTo(45f, 80f, 46.5f, 77f, 48f, 77f)
            curveTo(49.5f, 77f, 51f, 80f, 52f, 84f)
            curveTo(52f, 88f, 50f, 90f, 48f, 90f)
            curveTo(46f, 90f, 44f, 88f, 44f, 84f)
            close()
        }

        // ── Left fin ──────────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFE53935)),
            stroke = SolidColor(Color(0xFFB71C1C)),
            strokeLineWidth = 1.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(32f, 72f)
            lineTo(20f, 82f)
            lineTo(32f, 82f)
            close()
        }

        // ── Right fin ─────────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFE53935)),
            stroke = SolidColor(Color(0xFFB71C1C)),
            strokeLineWidth = 1.5f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(64f, 72f)
            lineTo(76f, 82f)
            lineTo(64f, 82f)
            close()
        }

        // ── Main body (fat oval) ──────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFF5F5F5)),
            stroke = SolidColor(Color(0xFFCCCCCC)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(32f, 70f)
            curveTo(32f, 82f, 38f, 88f, 48f, 88f)
            curveTo(58f, 88f, 64f, 82f, 64f, 70f)
            lineTo(64f, 40f)
            curveTo(64f, 32f, 58f, 26f, 48f, 22f)
            curveTo(38f, 26f, 32f, 32f, 32f, 40f)
            close()
        }

        // ── Red mid-stripe ────────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFE53935)), strokeLineWidth = 0f) {
            moveTo(32f, 55f)
            lineTo(64f, 55f)
            lineTo(64f, 65f)
            lineTo(32f, 65f)
            close()
        }

        // ── Nose cone ─────────────────────────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFE53935)),
            stroke = SolidColor(Color(0xFFB71C1C)),
            strokeLineWidth = 2f,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Round
        ) {
            moveTo(48f, 4f)
            curveTo(34f, 14f, 32f, 28f, 32f, 40f)
            lineTo(64f, 40f)
            curveTo(64f, 28f, 62f, 14f, 48f, 4f)
            close()
        }

        // ── Porthole window (blue circle) ─────────────────────────────────────
        path(
            fill = SolidColor(Color(0xFFB3E5FC)),
            stroke = SolidColor(Color(0xFF7AABDA)),
            strokeLineWidth = 2.5f
        ) {
            moveTo(38f, 44f)
            curveTo(38f, 37f, 42.5f, 32f, 48f, 32f)
            curveTo(53.5f, 32f, 58f, 37f, 58f, 44f)
            curveTo(58f, 51f, 53.5f, 56f, 48f, 56f)
            curveTo(42.5f, 56f, 38f, 51f, 38f, 44f)
            close()
        }

        // ── Porthole highlight ────────────────────────────────────────────────
        path(fill = SolidColor(Color(0xAAFFFFFF)), strokeLineWidth = 0f) {
            moveTo(41f, 37f)
            curveTo(43f, 34f, 47f, 33f, 50f, 35f)
            curveTo(47f, 35f, 43f, 37f, 41f, 40f)
            close()
        }

        // ── Little star on body ───────────────────────────────────────────────
        path(fill = SolidColor(Color(0xFFFFD600)), strokeLineWidth = 0f) {
            // 5-point star at (48, 72) size 4
            moveTo(48f, 68f)
            lineTo(49.2f, 71.3f)
            lineTo(52.8f, 71.3f)
            lineTo(50f, 73.3f)
            lineTo(51.2f, 76.6f)
            lineTo(48f, 74.6f)
            lineTo(44.8f, 76.6f)
            lineTo(46f, 73.3f)
            lineTo(43.2f, 71.3f)
            lineTo(46.8f, 71.3f)
            close()
        }

    }.build()
}
