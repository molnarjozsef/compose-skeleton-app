package com.jozsefmolnar.newskeletonapp.navigation

data class NavigationOptions(
    val popUpTo: Route.Factory<*>,
    val popUpToInclusive: Boolean
)
