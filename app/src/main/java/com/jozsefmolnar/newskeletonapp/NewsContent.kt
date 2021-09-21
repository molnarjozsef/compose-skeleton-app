package com.jozsefmolnar.newskeletonapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jozsefmolnar.newskeletonapp.model.domain.Article

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
    Row(modifier = Modifier.clickable {
        article.id?.let { showDetails(it) }
    }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = article.title, style = TextStyle(fontSize = 20.sp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = article.content ?: "")
        }
    }
}
