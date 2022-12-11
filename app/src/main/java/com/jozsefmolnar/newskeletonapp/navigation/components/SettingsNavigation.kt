package com.jozsefmolnar.newskeletonapp.navigation.components

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.jozsefmolnar.newskeletonapp.feature.settings.SettingsScreen

const val SettingsRoute = "settings_route"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(SettingsRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable(route = SettingsRoute) {
        SettingsScreen()
    }
}
