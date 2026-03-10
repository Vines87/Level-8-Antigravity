package com.zauberfluff.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.zauberfluff.core.domain.model.Symbol
import com.zauberfluff.core.ui.icons.ZauberfluffIcons

/**
 * A composable that renders a cute, child-friendly vector icon for a given [Symbol].
 *
 * @param symbol   The game symbol to render.
 * @param modifier Optional modifier (use [Modifier.size] to control icon size).
 * @param dimmed   When true, renders a faded out version of the icon
 *                 (used for mission slots the player hasn't filled yet).
 */
@Composable
fun SymbolIcon(
    symbol: Symbol,
    modifier: Modifier = Modifier.size(48.dp),
    dimmed: Boolean = false
) {
    val alphaValue = if (dimmed) 0.25f else 1f
    val icon = when (symbol) {
        Symbol.ASTRONAUT -> ZauberfluffIcons.Astronaut
        Symbol.PLANET    -> ZauberfluffIcons.Planet
        Symbol.ROCKET    -> ZauberfluffIcons.Rocket
        Symbol.STAR      -> ZauberfluffIcons.Star
        Symbol.ALIEN     -> ZauberfluffIcons.Alien
        Symbol.JOKER     -> ZauberfluffIcons.Joker
    }

    Image(
        imageVector = icon,
        contentDescription = symbol.name,
        modifier = modifier.alpha(alphaValue)
    )
}
