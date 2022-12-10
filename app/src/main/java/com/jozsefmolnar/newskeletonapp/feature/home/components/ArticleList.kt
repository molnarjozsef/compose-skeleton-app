@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun ArticleList(
    articles: ImmutableList<Article>?,
    onNewsItemClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (articles != null) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(
                horizontal = Sizes.Size200,
                vertical = Sizes.Size200,
            ),
            verticalArrangement = Arrangement.spacedBy(Sizes.Size200)
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

        Text(
            modifier = Modifier.padding(Sizes.Size200),
            text = article.title,
            style = MaterialTheme.typography.titleLarge,
        )
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
