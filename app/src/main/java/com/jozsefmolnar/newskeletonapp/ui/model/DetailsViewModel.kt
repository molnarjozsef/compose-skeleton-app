package com.jozsefmolnar.newskeletonapp.ui.model

import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.newskeletonapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MainRepository,
) : BaseViewModel() {

    private val fooId = MutableStateFlow<Int?>(null)

    val foo = fooId.filterNotNull()
        .flatMapLatest { repository.getCachedFoo(it) }
        .asStateFlow()

    fun setFooId(id: Int) {
        viewModelScope.launch {
            fooId.emit(id)
        }
    }
}
