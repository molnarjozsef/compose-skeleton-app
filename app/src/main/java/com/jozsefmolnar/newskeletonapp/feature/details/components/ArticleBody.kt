package com.jozsefmolnar.newskeletonapp.feature.details.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes

@Composable
fun ArticleBody(
    article: Article,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
) {
    Column(modifier) {
        article.author?.let {
            Spacer(Modifier.height(Sizes.Size200))

            Text(
                text = article.author,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge,
            )
        }

        article.content?.let { content ->
            Spacer(Modifier.height(Sizes.Size200))

            Text(
                text = content.substringBefore(ContentDelimiter),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

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

private fun Context.openUrl(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        startActivity(browserIntent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(this, "Browser not found", Toast.LENGTH_LONG).show()
    }
}

private const val ContentDelimiter = "["
