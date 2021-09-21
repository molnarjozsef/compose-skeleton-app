package com.jozsefmolnar.newskeletonapp.navigation

import kotlinx.coroutines.flow.Flow

/**
 * Manages the route stack and history.
 */
interface RouteManager {

    /**
     * Goes back to the previous route with an optional result. Think "back button".
     *
     * @param result The route to pass back to the previous route
     */
    fun goBack(result: NavigationResult? = null)

    /**
     * Goes back to a specific route with an optional result.
     *
     * @param route The route where you want to go back to
     * @param result The result to pass back to the destination route
     *
     */
    fun goBackTo(route: Route, result: NavigationResult? = null)

    /**
     * Goes to the requested route.
     *
     * @param route The route where you want to move to
     * @param navigationOptions The navigation options that has to be applied to the screen change
     */
    fun setRoute(route: Route, navigationOptions: NavigationOptions? = null)

    /**
     * Gets the default route.
     *
     * @return the starting route
     */
    fun getDefaultRoute(): Route

    /**
     * The route manager does not perform any navigation. It updates the history and dispatches
     * the events to a consumer. The consumer can do the actual traversal between routes.
     *
     * @return a flow of navigation events.
     */
    fun dispatch(): Flow<NavigationEvent>
}
