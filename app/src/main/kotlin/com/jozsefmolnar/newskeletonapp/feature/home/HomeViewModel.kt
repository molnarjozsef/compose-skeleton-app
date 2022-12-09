package com.jozsefmolnar.newskeletonapp.feature.home

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.action.FooAction
import com.jozsefmolnar.newskeletonapp.navigation.Route
import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import com.jozsefmolnar.newskeletonapp.store.FooStore
import com.jozsefmolnar.newskeletonapp.feature.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    fooStore: FooStore,
    private val fooAction: FooAction,
    private val simpleNavigator: SimpleNavigator,
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

    fun showDetails(id: Int) = simpleNavigator.navigateTo(Route.DetailsRoute.withArgs(id))
}
