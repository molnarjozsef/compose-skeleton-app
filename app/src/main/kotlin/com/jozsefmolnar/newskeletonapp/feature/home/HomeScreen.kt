@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val fooItems by viewModel.items.collectAsStateWithLifecycle()

    HomeScreenContent(
        fooItems = fooItems?.toPersistentList() ?: persistentListOf(),
        onFooItemClicked = { viewModel.showDetails(it.id!!) }
    )
}

@Composable
fun HomeScreenContent(
    fooItems: ImmutableList<Foo>,
    onFooItemClicked: (Foo) -> Unit,
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Home") }) }
    ) { contentPadding ->
        Column(Modifier.padding(contentPadding)) {

            LazyColumn {
                items(fooItems) { fooItem ->
                    Text(
                        text = fooItem.name,
                        modifier = Modifier.clickable { onFooItemClicked(fooItem) },
                    )
                }
            }
        }
    }
}
