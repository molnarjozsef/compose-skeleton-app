package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.getAll
import androidx.navigation.NavBackStackEntry
import com.jozsefmolnar.newskeletonapp.navigation.Constants.ARG_ROUTE
import com.jozsefmolnar.newskeletonapp.ui.model.BaseScreenViewModel

@Composable
fun ScreenWithViewModel(
    navBackStackEntry: NavBackStackEntry,
    routeFactory: Route.Factory<*>,
    content: @Composable () -> Unit
) {
    content()

    LaunchedEffect(Unit) {
        val route = navBackStackEntry.arguments?.get(ARG_ROUTE) as Route?

        val viewModel = navBackStackEntry.viewModelStore.getAll()
            .filterIsInstance<BaseScreenViewModel>()
            .firstOrNull()

        viewModel?.setCurrentRoute(route ?: routeFactory.create(navBackStackEntry.arguments))
    }
}
