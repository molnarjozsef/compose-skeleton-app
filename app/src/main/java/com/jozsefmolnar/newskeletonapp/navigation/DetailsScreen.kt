package com.jozsefmolnar.newskeletonapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailsScreen(item: StateFlow<Article?>) {
    val article = item.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row {
            GlideImage(imageModel = article.value?.urlToImage ?: "", modifier = Modifier.width(160.dp))
            Column {
                Text(text = article.value?.title ?: "TITLE")
                Text(text = article.value?.description ?: "DESCRIPTION")
            }
        }
    }
}

@Preview
@Composable
fun DetailsPreview() {
    DetailsScreen(item = MutableStateFlow(ArticleGenerator.generateArticle()))
}
