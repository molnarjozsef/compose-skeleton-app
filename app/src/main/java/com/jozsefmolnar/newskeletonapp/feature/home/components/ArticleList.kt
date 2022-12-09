@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator

@Composable
fun ArticleList(
    articles: List<Article>?,
    onNewsItemClicked: (Article) -> Unit,
) {
    if (articles != null) {
        LazyColumn(
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
fun ArticleListItem(
    article: Article,
    onNewsItemClicked: (Article) -> Unit,
) {
    Card(
        onClick = { onNewsItemClicked(article) },
        shape = RoundedCornerShape(Sizes.Size200),
    ) {
        Column(modifier = Modifier.padding(Sizes.Size200)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineSmall,
            )

            Spacer(modifier = Modifier.height(Sizes.Size100))

            Text(
                text = article.content?.substringBefore("[") ?: "",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
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
