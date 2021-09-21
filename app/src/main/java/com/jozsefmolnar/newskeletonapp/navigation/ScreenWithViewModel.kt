package com.jozsefmolnar.newskeletonapp.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.asFlow
import androidx.lifecycle.getAll
import androidx.navigation.NavBackStackEntry
import com.jozsefmolnar.newskeletonapp.navigation.Constants.ARG_RESULT
import com.jozsefmolnar.newskeletonapp.navigation.Constants.ARG_ROUTE
import com.jozsefmolnar.newskeletonapp.navigation.NavigationResult
import com.jozsefmolnar.newskeletonapp.navigation.Route
import com.jozsefmolnar.newskeletonapp.ui.model.BaseScreenViewModel
import com.jozsefmolnar.newskeletonapp.util.requireActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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

        if (viewModel != null) {
            viewModel.setCurrentRoute(route ?: routeFactory.create(navBackStackEntry.arguments))

            handleNavigationResult(navBackStackEntry, viewModel)
        }
    }
}

private fun CoroutineScope.handleNavigationResult(
    navBackStackEntry: NavBackStackEntry,
    viewModel: BaseScreenViewModel
) {
    val navigationResultLiveData = navBackStackEntry.savedStateHandle
        .getLiveData<NavigationResult>(ARG_RESULT)

    navigationResultLiveData.asFlow()
        .filterNotNull()
        .onEach {
            viewModel.setNavigationResult(it)
            navigationResultLiveData.value = null
        }
        .launchIn(this)
}
