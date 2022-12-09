package com.jozsefmolnar.newskeletonapp.navigation

sealed class Route(val route: String) {
    object MainRoute : Route("main_route")
    object DetailsRoute : Route("details_route")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
