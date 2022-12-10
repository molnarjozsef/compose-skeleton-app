@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.feature.details.components.ArticleBody
import com.jozsefmolnar.newskeletonapp.feature.details.components.ArticleHeader
import com.jozsefmolnar.newskeletonapp.feature.details.components.ArticleImage
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
) {
    val article by viewModel.article.collectAsStateWithLifecycle()

    DetailsScreenContent(
        article = article,
        navigateUp = viewModel::navigateUp,
    )
}

@Composable
private fun DetailsScreenContent(
    article: Article?,
    navigateUp: () -> Unit,
) {
    Column {
        TopAppBar(
            title = { Text("Article details") },
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
            windowInsets = WindowInsets(0.dp),
        )
        if (article != null) {
            ArticleDetails(article = article)
        }
    }
}

@Composable
private fun ArticleDetails(
    article: Article,
) {
    Column(Modifier.padding(vertical = Sizes.Size200)) {
        ArticleHeader(
            article = article,
            modifier = Modifier.padding(horizontal = Sizes.Size200),
        )

        ArticleImage(
            imageUrl = article.urlToImage,
            modifier = Modifier.fillMaxWidth(),
        )

        ArticleBody(
            article = article,
            modifier = Modifier.padding(horizontal = Sizes.Size200),
        )
    }
}

@Preview
@Composable
fun DetailsPreview() {
    DetailsScreenContent(
        article = ArticleGenerator.generateArticle(),
        navigateUp = { },
    )
}
