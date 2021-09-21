package com.jozsefmolnar.newskeletonapp.navigation.impl

import com.jozsefmolnar.newskeletonapp.navigation.BaseRouteManager
import com.jozsefmolnar.newskeletonapp.navigation.Route
import com.jozsefmolnar.newskeletonapp.route.HomeRoute
import javax.inject.Inject

class RouteManagerImpl @Inject internal constructor() : BaseRouteManager() {

    override fun getDefaultRoute(): Route = HomeRoute()
}
