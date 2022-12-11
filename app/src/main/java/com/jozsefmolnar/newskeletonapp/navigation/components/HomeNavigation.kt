package com.jozsefmolnar.newskeletonapp.navigation.components

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jozsefmolnar.newskeletonapp.feature.home.HomeScreen

const val HomeGraphRoute = "home_graph"
const val HomeRoute = "home_route"

fun NavController.navigateToHomeGraph(navOptions: NavOptions? = null) {
    this.navigate(HomeGraphRoute, navOptions)
}

fun NavGraphBuilder.homeGraph(
    navigateToDetails: (Int) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = HomeGraphRoute,
        startDestination = HomeRoute
    ) {
        composable(route = HomeRoute) {
            HomeScreen(
                navigateToDetails = navigateToDetails,
            )
        }
        nestedGraphs()
    }
}
