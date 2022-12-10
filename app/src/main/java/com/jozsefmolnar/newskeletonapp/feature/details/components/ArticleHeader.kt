package com.jozsefmolnar.newskeletonapp.feature.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.util.DateTimeUtils
import com.jozsefmolnar.newskeletonapp.util.DateTimeUtils.format

@Composable
fun ArticleHeader(
    article: Article,
    modifier: Modifier = Modifier,
) {
    val publishedAt = remember { DateTimeUtils.parseArticleDateTime(article.publishedAt)?.format() }

    Column(modifier) {
        Row {
            article.source?.let {
                Text(
                    text = article.source,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.alignByBaseline(),
                )
            }

            publishedAt?.let {
                Spacer(Modifier.width(Sizes.Size100))

                Text(
                    text = publishedAt,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.alignByBaseline(),
                )
            }
        }

        Spacer(Modifier.height(Sizes.Size200))

        Text(
            text = article.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(Sizes.Size200))
    }
}
