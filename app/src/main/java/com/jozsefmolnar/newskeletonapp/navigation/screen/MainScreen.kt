package com.jozsefmolnar.newskeletonapp.navigation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jozsefmolnar.newskeletonapp.NewsHomeContent
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import com.jozsefmolnar.newskeletonapp.util.collectAsStateInLifecycle

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val articles by viewModel.items.collectAsStateInLifecycle()

    MainScreenContent(articles)
    { viewModel.showDetails(it) }
}

@Composable
fun MainScreenContent(
    articles: List<Article>?,
    showDetails: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        NewsHomeContent(
            articles = articles ?: emptyList(),
             showDetails
        )
    }
}
