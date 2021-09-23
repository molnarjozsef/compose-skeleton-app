package com.jozsefmolnar.newskeletonapp.navigation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jozsefmolnar.newskeletonapp.NewsHomeContent
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import com.jozsefmolnar.newskeletonapp.ui.theme.AppColors
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
    Box(modifier = Modifier.background(AppColors.Background)) {
        NewsHomeContent(
            articles = articles ?: emptyList(),
            showDetails
        )
    }
}
