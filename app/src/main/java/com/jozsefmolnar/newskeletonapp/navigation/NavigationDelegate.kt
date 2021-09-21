package com.jozsefmolnar.newskeletonapp.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.jozsefmolnar.newskeletonapp.navigation.Constants.ARG_RESULT

class NavigationDelegate(private val navController: NavController) {

    fun navigate(navigationEvent: NavigationEvent) {
        when (navigationEvent) {
            is NavigationEvent.BackNavigation -> {
                val route = navigationEvent.route

                if (route == null) {
                    navController.popBackStack()
                } else {
                    navController.popBackStack(route.toPath(), inclusive = false)
                }

                setResult(navigationEvent)
            }
            is NavigationEvent.ScreenNavigation -> {
                val route = navigationEvent.route

                navController.navigate(route.toPath()) {
                    navigationEvent.navigationOptions?.let {
                        popUpTo(it.popUpTo.path) {
                            inclusive = it.popUpToInclusive
                        }
                    }
                }

                setRoute(navigationEvent)
            }
        }
    }

    private fun setResult(navigationEvent: NavigationEvent.BackNavigation) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.set(ARG_RESULT, navigationEvent.result)
    }

    private fun setRoute(navigationEvent: NavigationEvent.ScreenNavigation) {
        val route = navigationEvent.route
        val entry = navController.currentBackStackEntry
        val bundle = Bundle()
        bundle.putAll(route.toBundle())
        entry?.arguments?.putAll(bundle)
    }
}
