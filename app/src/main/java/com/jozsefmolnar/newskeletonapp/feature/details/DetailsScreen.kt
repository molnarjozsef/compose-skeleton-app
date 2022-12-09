@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.details

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.Constants
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.jozsefmolnar.newskeletonapp.util.ArticleGenerator

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel,
    navigateUp: () -> Unit,
) {
    val article by viewModel.article.collectAsStateWithLifecycle()

    DetailsScreenContent(
        article = article,
        navigateUp = navigateUp,
    )
}

@Composable
fun DetailsScreenContent(
    article: Article?,
    navigateUp: () -> Unit,
    context: Context = LocalContext.current,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        if (article != null) {
            Column(
                Modifier
                    .padding(contentPadding)
                    .padding(Sizes.Size300)
            ) {
                Card {
                    Image(
                        painter = rememberAsyncImagePainter(article.urlToImage),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(Constants.AspectRatio),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(Modifier.height(Sizes.Size300))

                Column {
                    Text(
                        text = article.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(Modifier.height(Sizes.Size200))

                    Text(
                        text = article.description ?: "DESCRIPTION",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(Modifier.height(Sizes.Size200))

                    Button(
                        onClick = { context.openUrl(article.url) },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Open in Browser",
                        )
                    }
                }
            }
        }
    }
}

private fun Context.openUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        startActivity(browserIntent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(this, "Browser not found", Toast.LENGTH_LONG).show()
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

