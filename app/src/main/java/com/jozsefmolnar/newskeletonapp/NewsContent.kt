package com.jozsefmolnar.newskeletonapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator

@Composable
fun NewsHomeContent(
    articles: List<Article>,
    showDetails: (Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = articles,
            itemContent = {
                NewsListItem(
                    article = it,
                    showDetails = showDetails
                )
            })
    }
}

@Composable
fun NewsListItem(
    article: Article,
    showDetails: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.clickable {
                article.id?.let { showDetails(it) }
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.DarkGray,
                modifier = Modifier.size(width = 120.dp, height = 80.dp),
                elevation = 4.dp
            ) {
                if (article.urlToImage?.isNotEmpty() == true) {
                    Image(
                        painter = rememberImagePainter(article.urlToImage.substringBefore('?')),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = article.title,
                    style = TextStyle(fontSize = 16.sp),
                    color = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
fun NewsListItemPreview() {
    NewsListItem(
        ArticleGenerator.generateArticle()
    ) {}
}
