@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.model.domain.Foo

@Composable
fun DetailsScreen(
    navigateUp: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val foo by viewModel.foo.collectAsStateWithLifecycle()

    DetailsScreenContent(
        foo = foo,
        navigateUp = navigateUp,
    )
}

@Composable
fun DetailsScreenContent(
    foo: Foo?,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(Modifier.padding(contentPadding)) {
            Text(text = foo?.name ?: "Empty")
        }
    }
}
