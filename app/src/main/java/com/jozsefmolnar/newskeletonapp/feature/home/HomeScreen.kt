@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.feature.destinations.DetailsScreenDestination
import com.jozsefmolnar.newskeletonapp.feature.home.components.HomeList
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.model.domain.Weather
import com.jozsefmolnar.newskeletonapp.util.OnResume
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    val articles by viewModel.articles.collectAsStateWithLifecycle()
    val weather by viewModel.weather.collectAsStateWithLifecycle()

    OnResume {
        coroutineScope.launch { viewModel.refresh() }
    }

    HomeScreenContent(
        articles = articles?.toPersistentList(),
        weather = weather,
        onNewsItemClicked = { navigator.navigate(DetailsScreenDestination.invoke(it.id!!)) },
        refresh = viewModel::refresh
    )
}

@Composable
fun HomeScreenContent(
    articles: ImmutableList<Article>?,
    weather: Weather?,
    onNewsItemClicked: (Article) -> Unit,
    refresh: suspend () -> Unit,
) {
    Column {
        TopAppBar(
            title = { Text("News") },
            windowInsets = WindowInsets(0.dp),
        )

        HomeList(
            articles = articles,
            weather = weather,
            onNewsItemClicked = onNewsItemClicked,
            refresh = refresh,
        )
    }
}
