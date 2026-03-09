package com.zauberfluff.core.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zauberfluff.core.domain.model.Symbol

/**
 * A composable that renders a cute, child-friendly icon for a given [Symbol].
 * Icons are drawn via Canvas for resolution independence.
 *
 * @param symbol   The game symbol to render.
 * @param modifier Optional modifier (use [Modifier.size] to control icon size).
 * @param dimmed   When true, renders a desaturated, greyed-out version of the icon
 *                 (used for mission slots the player hasn't filled yet).
 */
@Composable
fun SymbolIcon(
    symbol: Symbol,
    modifier: Modifier = Modifier.size(48.dp),
    dimmed: Boolean = false
) {
    val alpha = if (dimmed) 0.25f else 1f
    Canvas(modifier = modifier) {
        when (symbol) {
            Symbol.DRAGON   -> drawDragon(alpha)
            Symbol.UNICORN  -> drawUnicorn(alpha)
            Symbol.FAIRY    -> drawFairy(alpha)
            Symbol.GOBLIN   -> drawGoblin(alpha)
            Symbol.PHOENIX  -> drawPhoenix(alpha)
            Symbol.MERMAID  -> drawMermaid(alpha)
            Symbol.YETI     -> drawYeti(alpha)
            Symbol.KRAKEN   -> drawKraken(alpha)
            Symbol.GRIFFIN  -> drawGriffin(alpha)
            Symbol.PEGASUS  -> drawPegasus(alpha)
            Symbol.JOKER    -> drawJoker(alpha)
        }
    }
}

// ─── Joker ───────────────────────────────────────────────────────────────────
private fun DrawScope.drawJoker(alpha: Float) {
    val w = size.width
    val h = size.height
    // Simple colorful star/joker motif
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFFD54F).copy(alpha), Color(0xFFFF8F00).copy(alpha)),
            center = Offset(w * 0.5f, h * 0.5f),
            radius = w * 0.4f
        ),
        radius = w * 0.4f,
        center = Offset(w * 0.5f, h * 0.5f)
    )
    // Smile
    drawArc(
        color = Color(0xFFD84315).copy(alpha),
        startAngle = 20f, sweepAngle = 140f, useCenter = false,
        topLeft = Offset(w * 0.3f, h * 0.3f),
        size = Size(w * 0.4f, h * 0.4f),
        style = Stroke(width = w * 0.05f, cap = StrokeCap.Round)
    )
    // Eyes
    drawCircle(color = Color(0xFF1565C0).copy(alpha), radius = w * 0.05f, center = Offset(w * 0.35f, h * 0.4f))
    drawCircle(color = Color(0xFF1565C0).copy(alpha), radius = w * 0.05f, center = Offset(w * 0.65f, h * 0.4f))
}

// ─── Dragon ──────────────────────────────────────────────────────────────────
private fun DrawScope.drawDragon(alpha: Float) {
    val w = size.width
    val h = size.height
    // Body – deep green egg shape
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF66BB6A).copy(alpha), Color(0xFF2E7D32).copy(alpha)),
            center = Offset(w * 0.5f, h * 0.55f),
            radius = w * 0.4f
        ),
        topLeft = Offset(w * 0.15f, h * 0.30f),
        size = Size(w * 0.70f, h * 0.60f)
    )
    // Head – slightly lighter circle
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF81C784).copy(alpha), Color(0xFF388E3C).copy(alpha)),
            center = Offset(w * 0.62f, h * 0.28f),
            radius = w * 0.22f
        ),
        radius = w * 0.20f,
        center = Offset(w * 0.62f, h * 0.28f)
    )
    // Eye
    drawCircle(color = Color.White.copy(alpha), radius = w * 0.05f, center = Offset(w * 0.69f, h * 0.24f))
    drawCircle(color = Color(0xFF1B5E20).copy(alpha), radius = w * 0.03f, center = Offset(w * 0.70f, h * 0.24f))
    // Wing – triangle
    val wing = Path().apply {
        moveTo(w * 0.20f, h * 0.38f)
        lineTo(w * 0.02f, h * 0.10f)
        lineTo(w * 0.36f, h * 0.32f)
        close()
    }
    drawPath(wing, color = Color(0xFF43A047).copy(alpha))
    // Flame
    val flame = Path().apply {
        moveTo(w * 0.78f, h * 0.30f)
        quadraticBezierTo(w * 0.90f, h * 0.18f, w * 0.85f, h * 0.10f)
        quadraticBezierTo(w * 0.78f, h * 0.22f, w * 0.70f, h * 0.18f)
        quadraticBezierTo(w * 0.80f, h * 0.26f, w * 0.78f, h * 0.30f)
    }
    drawPath(flame, brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFF176).copy(alpha), Color(0xFFFF5722).copy(alpha)),
        startY = h * 0.10f,
        endY   = h * 0.30f
    ))
}

// ─── Unicorn ─────────────────────────────────────────────────────────────────
private fun DrawScope.drawUnicorn(alpha: Float) {
    val w = size.width
    val h = size.height
    // Body
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFF8BBD0).copy(alpha), Color(0xFFF48FB1).copy(alpha)),
            center = Offset(w * 0.50f, h * 0.62f),
            radius = w * 0.40f
        ),
        topLeft = Offset(w * 0.10f, h * 0.40f),
        size = Size(w * 0.80f, h * 0.52f)
    )
    // Head
    drawCircle(
        color = Color(0xFFFCE4EC).copy(alpha),
        radius = w * 0.22f,
        center = Offset(w * 0.68f, h * 0.30f)
    )
    // Horn
    val horn = Path().apply {
        moveTo(w * 0.68f, h * 0.08f)
        lineTo(w * 0.74f, h * 0.20f)
        lineTo(w * 0.62f, h * 0.20f)
        close()
    }
    drawPath(horn, brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFD700).copy(alpha), Color(0xFFFFA726).copy(alpha)),
        startY = h * 0.08f,
        endY   = h * 0.20f
    ))
    // Eye
    drawCircle(color = Color(0xFFAD1457).copy(alpha), radius = w * 0.04f, center = Offset(w * 0.73f, h * 0.28f))
    // Mane – rainbow arcs
    val maneColors = listOf(
        Color(0xFFE91E63), Color(0xFF9C27B0), Color(0xFF2196F3), Color(0xFF4CAF50), Color(0xFFFFEB3B)
    )
    maneColors.forEachIndexed { i, c ->
        drawArc(
            color = c.copy(alpha),
            startAngle = 200f,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(w * (0.30f - i * 0.04f), h * (0.05f + i * 0.04f)),
            size = Size(w * 0.28f, h * 0.28f),
            style = Stroke(width = w * 0.05f, cap = StrokeCap.Round)
        )
    }
}

// ─── Fairy ───────────────────────────────────────────────────────────────────
private fun DrawScope.drawFairy(alpha: Float) {
    val w = size.width
    val h = size.height
    // Wings left
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFB3E5FC).copy(alpha * 0.7f), Color(0xFF81D4FA).copy(alpha * 0.4f)),
            center = Offset(w * 0.28f, h * 0.42f),
            radius = w * 0.24f
        ),
        topLeft = Offset(w * 0.02f, h * 0.22f),
        size = Size(w * 0.34f, h * 0.40f)
    )
    // Wings right
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFB3E5FC).copy(alpha * 0.7f), Color(0xFF81D4FA).copy(alpha * 0.4f)),
            center = Offset(w * 0.72f, h * 0.42f),
            radius = w * 0.24f
        ),
        topLeft = Offset(w * 0.64f, h * 0.22f),
        size = Size(w * 0.34f, h * 0.40f)
    )
    // Body – dress
    val dress = Path().apply {
        moveTo(w * 0.40f, h * 0.38f)
        quadraticBezierTo(w * 0.30f, h * 0.80f, w * 0.20f, h * 0.90f)
        lineTo(w * 0.80f, h * 0.90f)
        quadraticBezierTo(w * 0.70f, h * 0.80f, w * 0.60f, h * 0.38f)
        close()
    }
    drawPath(dress, brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFF48FB1).copy(alpha), Color(0xFFCE93D8).copy(alpha)),
        startY = h * 0.38f,
        endY   = h * 0.90f
    ))
    // Head
    drawCircle(color = Color(0xFFFFF9C4).copy(alpha), radius = w * 0.18f, center = Offset(w * 0.50f, h * 0.24f))
    // Eye dots
    drawCircle(color = Color(0xFF4A148C).copy(alpha), radius = w * 0.03f, center = Offset(w * 0.45f, h * 0.23f))
    drawCircle(color = Color(0xFF4A148C).copy(alpha), radius = w * 0.03f, center = Offset(w * 0.55f, h * 0.23f))
    // Sparkles
    listOf(Offset(w * 0.82f, h * 0.14f), Offset(w * 0.15f, h * 0.60f), Offset(w * 0.85f, h * 0.68f)).forEach { o ->
        drawCircle(color = Color(0xFFFFEB3B).copy(alpha), radius = w * 0.03f, center = o)
    }
}

// ─── Goblin ──────────────────────────────────────────────────────────────────
private fun DrawScope.drawGoblin(alpha: Float) {
    val w = size.width
    val h = size.height
    // Body
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFF9CCC65).copy(alpha), Color(0xFF558B2F).copy(alpha)),
            center = Offset(w * 0.50f, h * 0.65f),
            radius = w * 0.35f
        ),
        topLeft = Offset(w * 0.18f, h * 0.42f),
        size = Size(w * 0.64f, h * 0.52f)
    )
    // Head – larger, rounder
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFAED581).copy(alpha), Color(0xFF689F38).copy(alpha)),
            center = Offset(w * 0.50f, h * 0.30f),
            radius = w * 0.28f
        ),
        radius = w * 0.28f,
        center = Offset(w * 0.50f, h * 0.30f)
    )
    // Big ears
    drawCircle(color = Color(0xFF7CB342).copy(alpha), radius = w * 0.10f, center = Offset(w * 0.22f, h * 0.30f))
    drawCircle(color = Color(0xFF7CB342).copy(alpha), radius = w * 0.10f, center = Offset(w * 0.78f, h * 0.30f))
    // Eyes – large and round
    drawCircle(color = Color(0xFFFFEB3B).copy(alpha), radius = w * 0.09f, center = Offset(w * 0.40f, h * 0.28f))
    drawCircle(color = Color(0xFFFFEB3B).copy(alpha), radius = w * 0.09f, center = Offset(w * 0.60f, h * 0.28f))
    drawCircle(color = Color(0xFF1B5E20).copy(alpha), radius = w * 0.05f, center = Offset(w * 0.41f, h * 0.28f))
    drawCircle(color = Color(0xFF1B5E20).copy(alpha), radius = w * 0.05f, center = Offset(w * 0.61f, h * 0.28f))
    // Smile – arc
    drawArc(
        color = Color(0xFF33691E).copy(alpha),
        startAngle = 10f, sweepAngle = 160f, useCenter = false,
        topLeft = Offset(w * 0.35f, h * 0.36f),
        size = Size(w * 0.30f, h * 0.14f),
        style = Stroke(width = w * 0.04f, cap = StrokeCap.Round)
    )
    // Hat
    val hat = Path().apply {
        moveTo(w * 0.28f, h * 0.16f)
        lineTo(w * 0.50f, h * 0.00f)
        lineTo(w * 0.72f, h * 0.16f)
        close()
    }
    drawPath(hat, color = Color(0xFF4E342E).copy(alpha))
    drawRect(
        color = Color(0xFF6D4C41).copy(alpha),
        topLeft = Offset(w * 0.26f, h * 0.14f),
        size = Size(w * 0.48f, h * 0.06f)
    )
}

// ─── Phoenix ─────────────────────────────────────────────────────────────────
private fun DrawScope.drawPhoenix(alpha: Float) {
    val w = size.width
    val h = size.height
    // Tail feathers – gradient sweep
    listOf(
        Triple(0.15f, 0.75f, Color(0xFFFF5722)),
        Triple(0.30f, 0.80f, Color(0xFFFF9800)),
        Triple(0.50f, 0.82f, Color(0xFFFFEB3B)),
        Triple(0.70f, 0.80f, Color(0xFFFF9800)),
        Triple(0.85f, 0.75f, Color(0xFFFF5722))
    ).forEach { (xFrac, yFrac, c) ->
        drawLine(
            color = c.copy(alpha),
            start = Offset(w * 0.50f, h * 0.50f),
            end   = Offset(w * xFrac, h * yFrac),
            strokeWidth = w * 0.06f,
            cap = StrokeCap.Round
        )
    }
    // Wings
    val wingL = Path().apply {
        moveTo(w * 0.50f, h * 0.40f)
        quadraticBezierTo(w * 0.05f, h * 0.20f, w * 0.10f, h * 0.50f)
        quadraticBezierTo(w * 0.28f, h * 0.44f, w * 0.50f, h * 0.50f)
        close()
    }
    val wingR = Path().apply {
        moveTo(w * 0.50f, h * 0.40f)
        quadraticBezierTo(w * 0.95f, h * 0.20f, w * 0.90f, h * 0.50f)
        quadraticBezierTo(w * 0.72f, h * 0.44f, w * 0.50f, h * 0.50f)
        close()
    }
    val wingBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFCC02).copy(alpha), Color(0xFFFF6D00).copy(alpha)),
        startY = 0f, endY = h
    )
    drawPath(wingL, wingBrush)
    drawPath(wingR, wingBrush)
    // Body
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFFF8F00).copy(alpha), Color(0xFFE65100).copy(alpha)),
            center = Offset(w * 0.50f, h * 0.42f),
            radius = w * 0.22f
        ),
        radius = w * 0.20f,
        center = Offset(w * 0.50f, h * 0.42f)
    )
    // Head
    drawCircle(color = Color(0xFFFFCC02).copy(alpha), radius = w * 0.14f, center = Offset(w * 0.50f, h * 0.22f))
    // Eye
    drawCircle(color = Color(0xFF4A148C).copy(alpha), radius = w * 0.04f, center = Offset(w * 0.55f, h * 0.20f))
}

// ─── Mermaid ─────────────────────────────────────────────────────────────────
private fun DrawScope.drawMermaid(alpha: Float) {
    val w = size.width
    val h = size.height
    // Tail
    val tail = Path().apply {
        moveTo(w * 0.35f, h * 0.55f)
        quadraticBezierTo(w * 0.20f, h * 0.80f, w * 0.15f, h * 0.92f)
        lineTo(w * 0.40f, h * 0.85f)
        quadraticBezierTo(w * 0.50f, h * 0.72f, w * 0.60f, h * 0.85f)
        lineTo(w * 0.85f, h * 0.92f)
        quadraticBezierTo(w * 0.80f, h * 0.80f, w * 0.65f, h * 0.55f)
        close()
    }
    drawPath(tail, brush = Brush.verticalGradient(
        colors = listOf(Color(0xFF26C6DA).copy(alpha), Color(0xFF006064).copy(alpha)),
        startY = h * 0.55f, endY = h * 0.92f
    ))
    // Scales shimmer
    for (row in 0..2) {
        for (col in 0..3) {
            drawCircle(
                color = Color(0xFF80DEEA).copy(alpha * 0.5f),
                radius = w * 0.04f,
                center = Offset(w * (0.32f + col * 0.12f), h * (0.62f + row * 0.09f))
            )
        }
    }
    // Torso
    drawOval(
        color = Color(0xFFFFF9C4).copy(alpha),
        topLeft = Offset(w * 0.30f, h * 0.32f),
        size = Size(w * 0.40f, h * 0.28f)
    )
    // Head
    drawCircle(color = Color(0xFFFFF59D).copy(alpha), radius = w * 0.20f, center = Offset(w * 0.50f, h * 0.22f))
    // Hair
    val hair = Path().apply {
        moveTo(w * 0.30f, h * 0.14f)
        quadraticBezierTo(w * 0.10f, h * 0.30f, w * 0.20f, h * 0.50f)
        quadraticBezierTo(w * 0.25f, h * 0.35f, w * 0.34f, h * 0.38f)
        close()
    }
    drawPath(hair, color = Color(0xFF00ACC1).copy(alpha))
    val hair2 = Path().apply {
        moveTo(w * 0.70f, h * 0.14f)
        quadraticBezierTo(w * 0.90f, h * 0.30f, w * 0.80f, h * 0.50f)
        quadraticBezierTo(w * 0.75f, h * 0.35f, w * 0.66f, h * 0.38f)
        close()
    }
    drawPath(hair2, color = Color(0xFF00ACC1).copy(alpha))
    // Eyes
    drawCircle(color = Color(0xFF0277BD).copy(alpha), radius = w * 0.04f, center = Offset(w * 0.44f, h * 0.21f))
    drawCircle(color = Color(0xFF0277BD).copy(alpha), radius = w * 0.04f, center = Offset(w * 0.56f, h * 0.21f))
}

// ─── Yeti ────────────────────────────────────────────────────────────────────
private fun DrawScope.drawYeti(alpha: Float) {
    val w = size.width
    val h = size.height
    // Body – fluffy white oval
    drawOval(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFE3F2FD).copy(alpha), Color(0xFF90CAF9).copy(alpha)),
            center = Offset(w * 0.50f, h * 0.60f),
            radius = w * 0.40f
        ),
        topLeft = Offset(w * 0.12f, h * 0.36f),
        size = Size(w * 0.76f, h * 0.58f)
    )
    // Arms
    drawOval(color = Color(0xFFBBDEFB).copy(alpha), topLeft = Offset(w * 0.02f, h * 0.45f), size = Size(w * 0.18f, h * 0.30f))
    drawOval(color = Color(0xFFBBDEFB).copy(alpha), topLeft = Offset(w * 0.80f, h * 0.45f), size = Size(w * 0.18f, h * 0.30f))
    // Head
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color.White.copy(alpha), Color(0xFFBBDEFB).copy(alpha)),
            center = Offset(w * 0.50f, h * 0.28f),
            radius = w * 0.26f
        ),
        radius = w * 0.26f,
        center = Offset(w * 0.50f, h * 0.28f)
    )
    // Eyes
    drawCircle(color = Color(0xFF1565C0).copy(alpha), radius = w * 0.06f, center = Offset(w * 0.40f, h * 0.26f))
    drawCircle(color = Color(0xFF1565C0).copy(alpha), radius = w * 0.06f, center = Offset(w * 0.60f, h * 0.26f))
    drawCircle(color = Color.White.copy(alpha), radius = w * 0.025f, center = Offset(w * 0.42f, h * 0.24f))
    drawCircle(color = Color.White.copy(alpha), radius = w * 0.025f, center = Offset(w * 0.62f, h * 0.24f))
    // Nose – button
    drawCircle(color = Color(0xFF90CAF9).copy(alpha), radius = w * 0.04f, center = Offset(w * 0.50f, h * 0.32f))
    // Snowflakes
    listOf(Offset(w * 0.12f, h * 0.14f), Offset(w * 0.80f, h * 0.08f), Offset(w * 0.92f, h * 0.50f)).forEach { o ->
        drawCircle(color = Color(0xFFB3E5FC).copy(alpha), radius = w * 0.03f, center = o)
        for (i in 0..5) {
            val angle = Math.toRadians(i * 60.0)
            drawLine(
                color = Color(0xFFB3E5FC).copy(alpha),
                start = o,
                end = Offset(o.x + (w * 0.05f * Math.cos(angle)).toFloat(), o.y + (w * 0.05f * Math.sin(angle)).toFloat()),
                strokeWidth = w * 0.015f
            )
        }
    }
}

// ─── Kraken ──────────────────────────────────────────────────────────────────
private fun DrawScope.drawKraken(alpha: Float) {
    val w = size.width
    val h = size.height
    // Tentacles
    val tentacleAngles = listOf(-60f, -30f, 0f, 30f, 60f, -90f, 90f, 120f)
    tentacleAngles.forEachIndexed { i, angle ->
        val rad = Math.toRadians(angle.toDouble())
        val endX = (w * 0.50f + w * 0.44f * Math.cos(rad)).toFloat()
        val endY = (h * 0.60f + h * 0.44f * Math.sin(rad)).toFloat()
        val path = Path().apply {
            moveTo(w * 0.50f, h * 0.60f)
            quadraticBezierTo(
                (w * 0.50f + endX) / 2f + (if (i % 2 == 0) w * 0.15f else -w * 0.15f),
                (h * 0.60f + endY) / 2f,
                endX, endY
            )
        }
        drawPath(path, color = Color(0xFF7B1FA2).copy(alpha), style = Stroke(width = w * 0.07f, cap = StrokeCap.Round))
    }
    // Body
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(Color(0xFFBA68C8).copy(alpha), Color(0xFF6A1B9A).copy(alpha)),
            center = Offset(w * 0.50f, h * 0.45f),
            radius = w * 0.28f
        ),
        radius = w * 0.28f,
        center = Offset(w * 0.50f, h * 0.45f)
    )
    // Big eyes
    drawCircle(color = Color(0xFFFFEB3B).copy(alpha), radius = w * 0.10f, center = Offset(w * 0.38f, h * 0.42f))
    drawCircle(color = Color(0xFFFFEB3B).copy(alpha), radius = w * 0.10f, center = Offset(w * 0.62f, h * 0.42f))
    drawCircle(color = Color(0xFF1A237E).copy(alpha), radius = w * 0.06f, center = Offset(w * 0.38f, h * 0.43f))
    drawCircle(color = Color(0xFF1A237E).copy(alpha), radius = w * 0.06f, center = Offset(w * 0.62f, h * 0.43f))
}

// ─── Griffin ─────────────────────────────────────────────────────────────────
private fun DrawScope.drawGriffin(alpha: Float) {
    val w = size.width
    val h = size.height
    // Hindquarters – lion body
    drawOval(
        color = Color(0xFFFFA726).copy(alpha),
        topLeft = Offset(w * 0.10f, h * 0.50f),
        size = Size(w * 0.55f, h * 0.40f)
    )
    // Wings
    val wingPath = Path().apply {
        moveTo(w * 0.55f, h * 0.44f)
        quadraticBezierTo(w * 0.90f, h * 0.10f, w * 0.96f, h * 0.44f)
        quadraticBezierTo(w * 0.80f, h * 0.50f, w * 0.55f, h * 0.50f)
        close()
    }
    drawPath(wingPath, brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFFCC02).copy(alpha), Color(0xFFFF8F00).copy(alpha)),
        startY = h * 0.10f, endY = h * 0.50f
    ))
    // Eagle forebody
    drawOval(
        color = Color(0xFF795548).copy(alpha),
        topLeft = Offset(w * 0.30f, h * 0.35f),
        size = Size(w * 0.40f, h * 0.30f)
    )
    // Eagle head
    drawCircle(color = Color(0xFF5D4037).copy(alpha), radius = w * 0.18f, center = Offset(w * 0.68f, h * 0.28f))
    // Beak
    val beak = Path().apply {
        moveTo(w * 0.80f, h * 0.28f)
        lineTo(w * 0.94f, h * 0.24f)
        lineTo(w * 0.80f, h * 0.34f)
        close()
    }
    drawPath(beak, color = Color(0xFFFFCC02).copy(alpha))
    // Eye
    drawCircle(color = Color(0xFFFFEB3B).copy(alpha), radius = w * 0.05f, center = Offset(w * 0.72f, h * 0.25f))
    drawCircle(color = Color(0xFF1A237E).copy(alpha), radius = w * 0.03f, center = Offset(w * 0.73f, h * 0.25f))
    // Tail tuft
    drawLine(
        color = Color(0xFFFFCC02).copy(alpha),
        start = Offset(w * 0.10f, h * 0.70f),
        end   = Offset(w * 0.02f, h * 0.90f),
        strokeWidth = w * 0.06f, cap = StrokeCap.Round
    )
}

// ─── Pegasus ─────────────────────────────────────────────────────────────────
private fun DrawScope.drawPegasus(alpha: Float) {
    val w = size.width
    val h = size.height
    // Wings
    val wingL = Path().apply {
        moveTo(w * 0.44f, h * 0.38f)
        quadraticBezierTo(w * 0.05f, h * 0.15f, w * 0.08f, h * 0.55f)
        quadraticBezierTo(w * 0.26f, h * 0.48f, w * 0.44f, h * 0.50f)
        close()
    }
    val wingR = Path().apply {
        moveTo(w * 0.56f, h * 0.38f)
        quadraticBezierTo(w * 0.95f, h * 0.15f, w * 0.92f, h * 0.55f)
        quadraticBezierTo(w * 0.74f, h * 0.48f, w * 0.56f, h * 0.50f)
        close()
    }
    val wingBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFE3F2FD).copy(alpha), Color(0xFF90CAF9).copy(alpha)),
        startY = 0f, endY = h
    )
    drawPath(wingL, wingBrush)
    drawPath(wingR, wingBrush)
    // Body
    drawOval(
        color = Color.White.copy(alpha),
        topLeft = Offset(w * 0.26f, h * 0.44f),
        size = Size(w * 0.48f, h * 0.38f)
    )
    // Neck
    drawOval(
        color = Color(0xFFFAFAFA).copy(alpha),
        topLeft = Offset(w * 0.40f, h * 0.28f),
        size = Size(w * 0.22f, h * 0.24f)
    )
    // Head
    drawCircle(color = Color.White.copy(alpha), radius = w * 0.18f, center = Offset(w * 0.56f, h * 0.22f))
    // Mane – soft gradient
    val mane = Path().apply {
        moveTo(w * 0.44f, h * 0.14f)
        quadraticBezierTo(w * 0.30f, h * 0.24f, w * 0.38f, h * 0.40f)
        quadraticBezierTo(w * 0.42f, h * 0.30f, w * 0.48f, h * 0.25f)
        close()
    }
    drawPath(mane, brush = Brush.verticalGradient(
        colors = listOf(Color(0xFFCE93D8).copy(alpha), Color(0xFF7B1FA2).copy(alpha)),
        startY = h * 0.14f, endY = h * 0.40f
    ))
    // Eye
    drawCircle(color = Color(0xFF7B1FA2).copy(alpha), radius = w * 0.04f, center = Offset(w * 0.60f, h * 0.20f))
    // Legs
    for (xf in listOf(0.34f, 0.44f, 0.56f, 0.66f)) {
        drawLine(
            color = Color(0xFFE0E0E0).copy(alpha),
            start = Offset(w * xf, h * 0.80f),
            end   = Offset(w * xf, h * 0.96f),
            strokeWidth = w * 0.06f, cap = StrokeCap.Round
        )
    }
}
