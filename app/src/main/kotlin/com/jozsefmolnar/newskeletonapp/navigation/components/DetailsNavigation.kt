package com.jozsefmolnar.newskeletonapp.navigation.components

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jozsefmolnar.newskeletonapp.decoder.StringDecoder
import com.jozsefmolnar.newskeletonapp.feature.details.DetailsScreen

internal const val IdArg = "id"
const val DetailsRoute = "details_route"

internal class DetailsArgs(val id: String) {
    constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
        this(stringDecoder.decodeString(checkNotNull(savedStateHandle[IdArg])))
}

fun NavController.navigateToDetails(id: String) {
    this.navigate("$DetailsRoute/$id")
}

fun NavGraphBuilder.detailsScreen(
    navigateUp: () -> Unit,
) {
    composable(
        route = "$DetailsRoute/{$IdArg}",
        arguments = listOf(
            navArgument(IdArg) { type = NavType.StringType }
        )
    ) {
        DetailsScreen(
            navigateUp = navigateUp
        )
    }
}
