package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jozsefmolnar.newskeletonapp.navigation.components.HomeRoute
import com.jozsefmolnar.newskeletonapp.navigation.components.detailsScreen
import com.jozsefmolnar.newskeletonapp.navigation.components.homeScreen
import com.jozsefmolnar.newskeletonapp.navigation.components.navigateToDetails

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = HomeRoute,
    ) {
        homeScreen(
            navigateToDetails = { id -> navController.navigateToDetails(id) }
        )
        detailsScreen(
            navigateUp = navController::navigateUp
        )
    }
}
