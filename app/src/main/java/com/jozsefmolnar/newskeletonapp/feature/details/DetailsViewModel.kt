package com.jozsefmolnar.newskeletonapp.feature.details

import androidx.lifecycle.SavedStateHandle
import com.jozsefmolnar.newskeletonapp.decoder.StringDecoder
import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.navigation.components.DetailsArgs
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    stringDecoder: StringDecoder,
    newsRepository: NewsRepository,
) : BaseViewModel() {

    private val detailsArgs: DetailsArgs = DetailsArgs(savedStateHandle, stringDecoder)

    val article = newsRepository.getCachedArticle(detailsArgs.articleId.toInt())
        .asStateFlow()
}
