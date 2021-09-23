package com.jozsefmolnar.newskeletonapp.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.jozsefmolnar.newskeletonapp.navigation.NavigationDelegate
import com.jozsefmolnar.newskeletonapp.navigation.RouteManager
import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@Composable
fun Navigation(
    routeManager: RouteManager,
    simpleNavigator: SimpleNavigator
) {
    val navController = androidx.navigation.compose.rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) { MainScreen() }
        composable(Screen.DetailsScreen.route + "/{articleId}",
            arguments = listOf(
                navArgument("articleId") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<DetailsViewModel>()
            entry.arguments?.getInt("articleId")?.let { viewModel.setArticleId(it) }
            DetailsScreen()
        }
    }

    LaunchedEffect(Unit) {
        val delegate = NavigationDelegate(navController)

        simpleNavigator.sharedFlow
            .onEach {
                navController.navigate(it)
            }
            .launchIn(this)

        // routeManager.dispatch()
        //     .onEach { delegate.navigate(it) }
        //     .launchIn(this)
    }
}
