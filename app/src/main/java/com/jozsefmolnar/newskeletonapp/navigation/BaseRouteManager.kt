package com.jozsefmolnar.newskeletonapp.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class BaseRouteManager : RouteManager {

    private val navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)

    override fun goBack(result: NavigationResult?) {
        navigationEvent.tryEmit(NavigationEvent.BackNavigation(result = result))
    }

    override fun goBackTo(route: Route, result: NavigationResult?) {
        navigationEvent.tryEmit(NavigationEvent.BackNavigation(route, result))
    }

    override fun setRoute(route: Route, navigationOptions: NavigationOptions?) {
        navigationEvent.tryEmit(NavigationEvent.ScreenNavigation(route, navigationOptions))
    }

    override fun dispatch(): Flow<NavigationEvent> = navigationEvent
}
