@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.jozsefmolnar.newskeletonapp.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
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
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val article by viewModel.article.collectAsStateWithLifecycle()

    DetailsScreenContent(
        article = article,
    )
}

@Composable
private fun DetailsScreenContent(
    article: Article?,
) {
    if (article != null) {
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = Sizes.Size200)
        ) {
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
}

@Preview
@Composable
fun DetailsPreview() {
    DetailsScreenContent(
        article = ArticleGenerator.generateArticle(),
    )
}
