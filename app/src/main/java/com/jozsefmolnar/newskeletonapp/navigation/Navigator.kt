package com.jozsefmolnar.newskeletonapp.navigation

/**
 * Framework independent navigator. Provides navigation methods between screens.
 */
interface Navigator {
    /**
     * Navigates to the starting screen.
     * @param navigationOptions NavigationOptions for popping the stack
     */
    fun navigateToHome(navigationOptions: NavigationOptions? = null)

    /**
     * Navigates forward to the requested screen.
     *
     * @param route The route where you want to navigate to
     * @param navigationOptions NavigationOptions for popping the stack
     */
    fun navigateTo(route: Route, navigationOptions: NavigationOptions? = null)

    /**
     * Navigates back to the previous screen.
     *
     * @param result The result to pass back to the previous screen
     */
    fun navigateBack(result: NavigationResult? = null)

    /**
     * Navigates back to the requested screen.
     *
     * @param route The route where you want to navigate back to
     * @param result The result to pass back to the destination screen
     */
    fun navigateBackTo(route: Route, result: NavigationResult? = null)

    /**
     * Navigates back to the starting screen.
     *
     * @param result The result to pass back to the starting screen
     */
    fun navigateBackToHome(result: NavigationResult? = null)
}
