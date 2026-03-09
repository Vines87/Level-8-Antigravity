package com.zauberfluff.core.ui.icons

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Central access point for all Zauberfluff super-cute vector icons.
 *
 * Usage:
 * ```kotlin
 * Icon(imageVector = ZauberfluffIcons.Astronaut, contentDescription = "Astronaut")
 * ```
 */
object ZauberfluffIcons {
    val Astronaut: ImageVector get() = AstronautIcon
    val Planet: ImageVector    get() = PlanetIcon
    val Rocket: ImageVector    get() = RocketIcon
    val Star: ImageVector      get() = StarIcon
    val Alien: ImageVector     get() = AlienIcon
    val Joker: ImageVector     get() = JokerIcon

    /** All icons in display order, e.g. for card backs / slot reels. */
    val all: List<ImageVector> = listOf(
        AstronautIcon,
        PlanetIcon,
        RocketIcon,
        StarIcon,
        AlienIcon,
        JokerIcon
    )
}
