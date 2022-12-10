package com.jozsefmolnar.newskeletonapp.util

import com.jozsefmolnar.newskeletonapp.model.domain.Article
import kotlin.random.Random

object ArticleGenerator {

    fun generateArticle() = Article(
        id = Random.nextInt(),
        title = "Sample article",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        url = "https://google.com",
        urlToImage = "https://picsum.photos/400/300",
        publishedAt = "17 sep 2021, 11:32",
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Donec justo nulla, egestas et ultrices sit amet, mollis sit amet dui. " +
            "Mauris at justo eu lectus iaculis sollicitudin quis at justo. " +
            "Pellentesque sit amet ipsum accumsan, consectetur purus nec, elementum urna. " +
            "Nunc non magna vestibulum, scelerisque lacus et, vulputate enim.",
        author = "Author",
        source = "Source"
    )
}
