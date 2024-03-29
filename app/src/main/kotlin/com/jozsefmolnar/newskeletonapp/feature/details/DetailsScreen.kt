@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.model.domain.Foo

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
) {
    val foo by viewModel.foo.collectAsStateWithLifecycle()

    DetailsScreenContent(
        foo = foo,
        navigateUp = viewModel::navigateUp,
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
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Box(Modifier.padding(contentPadding)) {
            Text(text = foo?.name ?: "")
        }
    }
}

