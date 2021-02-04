package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jozsefmolnar.newskeletonapp.model.domain.Article

class MainItemViewModel(
    item: Article,
) : ViewModel() {

    val title = ObservableField(item.title)

    val imageUrl = ObservableField(item.urlToImage)

    val publishedAt = ObservableField(item.publishedAt)
}
