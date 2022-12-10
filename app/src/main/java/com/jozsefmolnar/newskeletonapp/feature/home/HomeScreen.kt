@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.feature.home.components.ArticleList
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val articles by viewModel.items.collectAsStateWithLifecycle()

    HomeScreenContent(
        articles = articles?.toPersistentList(),
        onNewsItemClicked = { viewModel.showDetails(it.id!!) }
    )
}

@Composable
fun HomeScreenContent(
    articles: ImmutableList<Article>?,
    onNewsItemClicked: (Article) -> Unit,
) {
    Column {
        TopAppBar(title = { Text("News") })
        ArticleList(
            articles = articles,
            onNewsItemClicked = onNewsItemClicked,
        )
    }
}
