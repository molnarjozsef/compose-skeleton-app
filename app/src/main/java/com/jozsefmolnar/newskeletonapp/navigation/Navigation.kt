package com.jozsefmolnar.newskeletonapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jozsefmolnar.newskeletonapp.navigation.DetailsScreen
import com.jozsefmolnar.newskeletonapp.navigation.MainScreen
import com.jozsefmolnar.newskeletonapp.navigation.Screen
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun Navigation() {
    val navController = androidx.navigation.compose.rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(
                navController = navController,
                { viewModel.fetchLatestNews() },
                viewModel.items
            )
        }
        composable(route = Screen.DetailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { entry ->
            val viewModel = hiltViewModel<DetailsViewModel>()
            viewModel.setArticleId(entry.arguments?.getInt("id")!!)
            DetailsScreen(viewModel.article)
        }
    }
}
