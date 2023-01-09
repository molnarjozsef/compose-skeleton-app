@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.jozsefmolnar.newskeletonapp.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.model.domain.Weather
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
fun HomeList(
    articles: ImmutableList<Article>?,
    weather: Weather?,
    city: String?,
    onNewsItemClicked: (Article) -> Unit,
    refresh: suspend () -> Unit,
    modifier: Modifier = Modifier,
) {
    val refreshScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            refreshScope.launch {
                isRefreshing = true
                refresh()
                isRefreshing = false
            }
        },
    )

    Box(
        modifier = modifier
            .pullRefresh(pullRefreshState)
            .clipToBounds()
    ) {
        if (articles != null) {
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = Sizes.Size200,
                    vertical = Sizes.Size200,
                ),
                verticalArrangement = Arrangement.spacedBy(Sizes.Size300)
            ) {
                weather?.let {
                    item {
                        WeatherCard(
                            weather = weather,
                            city = city,
                        )
                    }
                }

                items(
                    items = articles
                ) { article ->
                    ArticleListItem(
                        article = article,
                        onNewsItemClicked = onNewsItemClicked,
                    )
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter),
        )
    }
}
