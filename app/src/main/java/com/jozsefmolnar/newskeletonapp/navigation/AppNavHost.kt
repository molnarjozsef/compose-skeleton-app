@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jozsefmolnar.newskeletonapp.feature.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = { AppNavigationBar(navController) }
    ) { contentPadding ->
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.root,
            modifier = Modifier.padding(contentPadding),
        )
    }
}
