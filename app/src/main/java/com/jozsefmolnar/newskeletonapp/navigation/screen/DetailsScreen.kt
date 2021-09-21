package com.jozsefmolnar.newskeletonapp.navigation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel
import com.jozsefmolnar.newskeletonapp.util.collectAsStateInLifecycle
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val article by viewModel.article.collectAsStateInLifecycle()

    DetailsScreenContent(article = article)
}

@Composable
fun DetailsScreenContent(article: Article?) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row {
            GlideImage(imageModel = article?.urlToImage ?: "", modifier = Modifier.width(160.dp))
            Column {
                Text(text = article?.title ?: "TITLE")
                Text(text = article?.description ?: "DESCRIPTION")
            }
        }
    }
}

@Preview
@Composable
fun DetailsPreview() {
//    DetailsScreen(item = MutableStateFlow(ArticleGenerator.generateArticle()))
}
