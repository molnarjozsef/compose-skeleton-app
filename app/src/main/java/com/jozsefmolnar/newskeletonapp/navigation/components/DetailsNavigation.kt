package com.jozsefmolnar.newskeletonapp.navigation.components

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jozsefmolnar.newskeletonapp.decoder.StringDecoder
import com.jozsefmolnar.newskeletonapp.feature.details.DetailsScreen

internal const val ArticleIdArg = "articleId"
const val DetailsRoute = "details_route"

internal class DetailsArgs(val articleId: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
        this(stringDecoder.decodeString(checkNotNull(savedStateHandle[ArticleIdArg])))
}

fun NavController.navigateToDetails(articleId: Int) {
    this.navigate("$DetailsRoute/$articleId")
}

fun NavGraphBuilder.detailsScreen() {
    composable(
        route = "$DetailsRoute/{$ArticleIdArg}",
        arguments = listOf(
            navArgument(ArticleIdArg) { type = NavType.StringType }
        )
    ) {
        DetailsScreen()
    }
}
