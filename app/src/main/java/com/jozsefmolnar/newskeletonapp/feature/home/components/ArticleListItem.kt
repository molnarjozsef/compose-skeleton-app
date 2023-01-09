@file:OptIn(ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.ui.theme.UiConstants
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator

@Composable
fun ArticleListItem(
    article: Article,
    onNewsItemClicked: (Article) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = { onNewsItemClicked(article) },
        shape = MaterialTheme.shapes.medium,
    ) {
        Image(
            painter = rememberAsyncImagePainter(article.urlToImage),
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .aspectRatio(UiConstants.AspectRatio),
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
