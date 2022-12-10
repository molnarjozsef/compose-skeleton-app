package com.jozsefmolnar.newskeletonapp.db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

object PreferencesKeys {
    val SelectedCountries = stringSetPreferencesKey("selected_countries")
}
