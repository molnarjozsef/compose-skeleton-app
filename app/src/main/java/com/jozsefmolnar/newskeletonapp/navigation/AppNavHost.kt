@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.jozsefmolnar.newskeletonapp.navigation.components.*
import kotlinx.collections.immutable.toPersistentList

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appState: AppState = rememberAppState(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                navigateUp = appState::onBackClick,
                currentDestination = appState.currentDestination,
            )
        },
        bottomBar = {
            AppBottomBar(
                destinations = appState.topLevelDestinations.toPersistentList(),
                currentDestination = appState.currentDestination,
                onNavigateToDestination = appState::navigateToTopLevelDestination
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = appState.navController,
            modifier = Modifier.padding(contentPadding),
            startDestination = HomeGraphRoute,
        ) {
            homeGraph(
                navigateToDetails = { articleId ->
                    appState.navController.navigateToDetails(articleId)
                },
                nestedGraphs = {
                    detailsScreen()
                }
            )

            settingsScreen()
        }
    }
}
