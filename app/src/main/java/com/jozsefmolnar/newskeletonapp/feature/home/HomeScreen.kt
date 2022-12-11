@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.compose.material3.ExperimentalMaterial3Api
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
    navigateToDetails: (Int) -> Unit,
) {
    val articles by viewModel.items.collectAsStateWithLifecycle()

    HomeScreenContent(
        articles = articles?.toPersistentList(),
        onNewsItemClicked = { navigateToDetails(it.id!!) }
    )
}

@Composable
fun HomeScreenContent(
    articles: ImmutableList<Article>?,
    onNewsItemClicked: (Article) -> Unit,
) {
    ArticleList(
        articles = articles,
        onNewsItemClicked = onNewsItemClicked,
    )
}
