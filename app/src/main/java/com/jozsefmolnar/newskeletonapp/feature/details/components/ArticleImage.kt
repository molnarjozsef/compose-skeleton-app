package com.jozsefmolnar.newskeletonapp.feature.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.jozsefmolnar.newskeletonapp.ui.theme.Constants

@Composable
fun ArticleImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        modifier = modifier.aspectRatio(Constants.AspectRatio),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}
