package com.zauberfluff.core.domain.engine

import kotlin.random.Random

class DeterministicSeedEngine(seed: Long) {
    val random: Random = Random(seed)
}
