package com.zauberfluff.core.domain.repository

import com.zauberfluff.core.domain.model.SoundPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    val soundPreferences: Flow<SoundPreferences>
    suspend fun updateSoundEnabled(enabled: Boolean)
    suspend fun updateMusicEnabled(enabled: Boolean)
}
