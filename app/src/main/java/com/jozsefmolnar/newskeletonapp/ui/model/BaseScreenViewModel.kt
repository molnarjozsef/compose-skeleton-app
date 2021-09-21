package com.jozsefmolnar.newskeletonapp.ui.model

import com.jozsefmolnar.newskeletonapp.navigation.NavigationResult
import com.jozsefmolnar.newskeletonapp.navigation.Route
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

abstract class BaseScreenViewModel(
) : BaseViewModel() {

    private val currentRoute = MutableStateFlow<Route?>(null)

    private val navigationResult =
        MutableSharedFlow<NavigationResult?>(extraBufferCapacity = 1)

    fun setCurrentRoute(route: Route) {
        currentRoute.value = route
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Route> currentRoute(): StateFlow<T?> = currentRoute as StateFlow<T?>

    @Suppress("UNCHECKED_CAST")
    fun <T : Route> currentRouteFlow(): Flow<T> = currentRoute
        .filterNotNull()
        .map { it as T }

    fun setNavigationResult(result: NavigationResult?) {
        navigationResult.tryEmit(result)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : NavigationResult> navigationResultAs(): Flow<T> = navigationResult
        .filterNotNull()
        .map { result -> result as T }
}
