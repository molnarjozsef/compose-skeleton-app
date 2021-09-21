package com.jozsefmolnar.newskeletonapp.route

import android.os.Bundle
import com.jozsefmolnar.newskeletonapp.navigation.Route
import kotlinx.android.parcel.Parcelize

@Parcelize
class HomeRoute : Route(this) {

    companion object : Factory<HomeRoute>() {

        override val path = "Home"

        override fun create(bundle: Bundle?) = HomeRoute()
    }
}
