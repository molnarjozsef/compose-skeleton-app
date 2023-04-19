package com.jozsefmolnar.newskeletonapp.navigation.components

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jozsefmolnar.newskeletonapp.feature.home.HomeScreen

const val HomeRoute = "home_route"

fun NavGraphBuilder.homeScreen(
    navigateToDetails: (String) -> Unit,
) {
    composable(route = HomeRoute) {
        HomeScreen(
            navigateToDetails = navigateToDetails,
        )
    }
}
