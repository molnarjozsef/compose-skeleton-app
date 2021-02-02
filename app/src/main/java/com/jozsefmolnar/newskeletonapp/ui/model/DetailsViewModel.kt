package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    val message = ObservableField<String>()

    init {
        message.set("Details Fragment")
    }
}