package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: Route.Factory<*>,
    route: Route.Factory<*>? = null,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startDestination.path,
        route = route?.path,
        modifier = modifier,
        builder = builder
    )
}
