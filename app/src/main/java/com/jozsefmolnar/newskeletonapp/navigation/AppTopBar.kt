@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import com.jozsefmolnar.newskeletonapp.navigation.components.DetailsRoute
import com.jozsefmolnar.newskeletonapp.navigation.components.HomeRoute
import com.jozsefmolnar.newskeletonapp.navigation.components.SettingsRoute

@Composable
fun AppTopBar(
    navigateUp: () -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = currentDestination?.route?.let { getTitle(it) } ?: "") },
        navigationIcon = {
            if (currentDestination?.route?.startsWith(DetailsRoute) == true) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        }
    )
}

private fun getTitle(route: String) = when {
    route == HomeRoute -> "News"
    route == SettingsRoute -> "Settings"
    route.startsWith(DetailsRoute) -> "Article details"
    else -> null
}
