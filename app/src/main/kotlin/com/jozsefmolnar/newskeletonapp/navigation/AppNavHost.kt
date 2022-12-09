package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jozsefmolnar.newskeletonapp.feature.details.DetailsScreen
import com.jozsefmolnar.newskeletonapp.feature.details.DetailsViewModel
import com.jozsefmolnar.newskeletonapp.feature.home.HomeScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun AppNavHost(
    simpleNavigator: SimpleNavigator,
) {
    val navController = androidx.navigation.compose.rememberNavController()
    NavHost(navController = navController, startDestination = Route.MainRoute.route) {
        composable(Route.MainRoute.route) { HomeScreen() }

        composable(
            Route.DetailsRoute.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<DetailsViewModel>()
            entry.arguments?.getInt("id")?.let { viewModel.setFooId(it) }

            DetailsScreen(
                viewModel = viewModel,
            )
        }
    }

    LaunchedEffect(Unit) {
        simpleNavigator.sharedFlow
            .onEach { navigateEvent ->
                when (navigateEvent) {
                    is SimpleNavigator.NavigateEvent.NavigateTo ->
                        navController.navigate(navigateEvent.route)
                    SimpleNavigator.NavigateEvent.NavigateUp ->
                        navController.navigateUp()
                }
            }
            .launchIn(this)
    }
}
