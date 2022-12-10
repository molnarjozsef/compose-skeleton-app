@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jozsefmolnar.newskeletonapp.feature.details.DetailsScreen
import com.jozsefmolnar.newskeletonapp.feature.details.DetailsViewModel
import com.jozsefmolnar.newskeletonapp.feature.home.HomeScreen
import com.jozsefmolnar.newskeletonapp.feature.settings.SettingsScreen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun AppNavHost(
    simpleNavigator: SimpleNavigator,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

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

    Scaffold(
        modifier = modifier,
        bottomBar = { AppNavigationBar(navController) }
    ) { contentPadding ->
        NavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController,
            startDestination = Route.MainRoute.route,
        ) {
            composable(Route.MainRoute.route) { HomeScreen() }

            composable(Route.SettingsRoute.route) { SettingsScreen() }

            composable(
                Route.DetailsRoute.route + "/{articleId}",
                arguments = listOf(
                    navArgument("articleId") {
                        type = NavType.IntType
                        defaultValue = 0
                    }
                )
            ) { entry ->
                val viewModel = hiltViewModel<DetailsViewModel>()
                entry.arguments?.getInt("articleId")?.let { viewModel.setArticleId(it) }

                DetailsScreen(
                    viewModel = viewModel,
                )
            }
        }
    }
}
