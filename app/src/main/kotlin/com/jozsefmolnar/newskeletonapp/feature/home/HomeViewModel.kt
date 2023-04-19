package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.action.FooAction
import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import com.jozsefmolnar.newskeletonapp.store.FooStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    fooStore: FooStore,
    private val fooAction: FooAction,
) : BaseViewModel() {

    val items = fooStore.getFooList()
        .asStateFlow()

    init {
        fetchLatestFoo()
    }

    fun fetchLatestFoo() {
        viewModelScope.launch {
            trackProgress {
                fooAction.refreshFooList()
            }
        }
    }
}
