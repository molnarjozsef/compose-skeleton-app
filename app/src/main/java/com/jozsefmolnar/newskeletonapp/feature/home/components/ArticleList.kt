@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)

package com.jozsefmolnar.newskeletonapp.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.Constants
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
fun ArticleList(
    articles: ImmutableList<Article>?,
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

    Box(modifier.pullRefresh(pullRefreshState)) {
        if (articles != null) {
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = Sizes.Size200,
                    vertical = Sizes.Size200,
                ),
                verticalArrangement = Arrangement.spacedBy(Sizes.Size300)
            ) {
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

@Composable
private fun ArticleListItem(
    article: Article,
    onNewsItemClicked: (Article) -> Unit,
) {
    Card(
        onClick = { onNewsItemClicked(article) },
        shape = MaterialTheme.shapes.medium,
    ) {
        Image(
            painter = rememberAsyncImagePainter(article.urlToImage),
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .aspectRatio(Constants.AspectRatio),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(Sizes.Size200))

        article.source?.let {
            Text(
                modifier = Modifier.padding(horizontal = Sizes.Size200),
                text = article.source,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(Sizes.Size100))
        }

        Text(
            modifier = Modifier.padding(horizontal = Sizes.Size200),
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(Sizes.Size200))
    }
}

@Preview
@Composable
fun ArticleListItemPreview() {
    ArticleListItem(
        article = ArticleGenerator.generateArticle(),
        onNewsItemClicked = { },
    )
}
