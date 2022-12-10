package com.jozsefmolnar.newskeletonapp.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.jozsefmolnar.newskeletonapp.db.PreferencesKeys
import com.jozsefmolnar.newskeletonapp.model.domain.NewsCountry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    suspend fun selectCountry(newsCountry: NewsCountry) {
        dataStore.edit { preferences ->
            val currentSelectedCountries = preferences[PreferencesKeys.SelectedCountries] ?: emptySet()
            val updatedSelectedCountries = if (currentSelectedCountries.contains(newsCountry.countryCode)) {
                currentSelectedCountries.minus(newsCountry.countryCode)
            } else {
                currentSelectedCountries.plus(newsCountry.countryCode)
            }
            preferences[PreferencesKeys.SelectedCountries] = updatedSelectedCountries
        }
    }

    fun getSelectedCountries(): Flow<List<NewsCountry>> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.SelectedCountries]
            ?.mapNotNull { countryCode -> NewsCountry.fromCountryCode(countryCode) }
            ?: emptyList()
    }
}
