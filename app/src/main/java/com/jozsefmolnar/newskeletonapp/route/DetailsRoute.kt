package com.jozsefmolnar.newskeletonapp.route

import com.jozsefmolnar.newskeletonapp.navigation.Route
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsRoute(
    val articleId: Int
) : Route(this) {

    companion object : Factory<DetailsRoute>() {

        override val path = "ArticleDetails"
    }
}
