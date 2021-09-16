package com.jozsefmolnar.newskeletonapp.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailsScreen : Screen("details_screen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
