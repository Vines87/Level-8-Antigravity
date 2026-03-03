package com.zauberfluff.core.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.zauberfluff.core.domain.model.LicenseStatus
import com.zauberfluff.core.domain.model.SoundPreferences
import com.zauberfluff.core.domain.repository.LicenseRepository
import com.zauberfluff.core.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "zauberfluff_prefs")

class DataStorePreferencesRepository @Inject constructor(
    private val context: Context
) : PreferencesRepository, LicenseRepository {

    private object PreferencesKeys {
        val IS_SOUND_ENABLED = booleanPreferencesKey("is_sound_enabled")
        val IS_MUSIC_ENABLED = booleanPreferencesKey("is_music_enabled")
        val IS_PREMIUM = booleanPreferencesKey("is_premium")
    }

    override val soundPreferences: Flow<SoundPreferences> = context.dataStore.data
        .map { preferences ->
            SoundPreferences(
                isSoundEnabled = preferences[PreferencesKeys.IS_SOUND_ENABLED] ?: true,
                isMusicEnabled = preferences[PreferencesKeys.IS_MUSIC_ENABLED] ?: true
            )
        }

    override val licenseStatus: Flow<LicenseStatus> = context.dataStore.data
        .map { preferences ->
            val isPremium = preferences[PreferencesKeys.IS_PREMIUM] ?: false
            if (isPremium) LicenseStatus.PREMIUM else LicenseStatus.FREE
        }

    override suspend fun updateSoundEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_SOUND_ENABLED] = enabled
        }
    }

    override suspend fun updateMusicEnabled(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_MUSIC_ENABLED] = enabled
        }
    }

    override suspend fun updateLicenseStatus(status: LicenseStatus) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_PREMIUM] = (status == LicenseStatus.PREMIUM)
        }
    }
}
