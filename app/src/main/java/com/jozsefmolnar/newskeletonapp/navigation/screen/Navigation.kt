package com.jozsefmolnar.newskeletonapp.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.jozsefmolnar.newskeletonapp.navigation.NavHost
import com.jozsefmolnar.newskeletonapp.navigation.NavigationDelegate
import com.jozsefmolnar.newskeletonapp.navigation.RouteManager
import com.jozsefmolnar.newskeletonapp.navigation.composable
import com.jozsefmolnar.newskeletonapp.route.DetailsRoute
import com.jozsefmolnar.newskeletonapp.route.HomeRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@Composable
fun Navigation(routeManager: RouteManager) {
    val navController = androidx.navigation.compose.rememberNavController()
    NavHost(navController = navController, startDestination = HomeRoute) {
        composable(HomeRoute) { MainScreen() }
        composable(DetailsRoute) { DetailsScreen() }
    }

    LaunchedEffect(Unit) {
        val delegate = NavigationDelegate(navController)

        routeManager.dispatch()
            .onEach { delegate.navigate(it) }
            .launchIn(this)
    }
}
