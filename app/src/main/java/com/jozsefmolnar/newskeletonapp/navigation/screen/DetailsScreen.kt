package com.jozsefmolnar.newskeletonapp.navigation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel
import com.jozsefmolnar.newskeletonapp.ui.theme.AppColors
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator
import com.jozsefmolnar.newskeletonapp.util.collectAsStateInLifecycle

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {
    val article by viewModel.article.collectAsStateInLifecycle()

    DetailsScreenContent(article = article)
}

@Composable
fun DetailsScreenContent(article: Article?) {
    article ?: return
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        Column(verticalArrangement = Arrangement.Top) {
            DetailsHeader(article)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = article.description ?: "",
                color = Color.White,
                style = TextStyle(fontSize = 14.sp)
            )
            DetailsOpenBrowserButton(article.url)
        }
    }
}

@Composable
fun DetailsHeader(article: Article) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        if (article.urlToImage?.isNotBlank() == true) {
            Image(
                painter = rememberImagePainter(article.urlToImage),
                modifier = Modifier.fillMaxSize(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            AppColors.Background
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = article.title,
                color = Color.White,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

@Composable
fun DetailsOpenBrowserButton(url: String) {
    val uriHandler = LocalUriHandler.current
    Box(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = { uriHandler.openUri(url) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Open full article")
        }
    }
}

@Preview
@Composable
fun DetailsPreview() {
    DetailsScreenContent(ArticleGenerator.generateArticle())
}
