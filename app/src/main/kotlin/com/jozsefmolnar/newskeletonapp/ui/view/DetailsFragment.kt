package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel
import com.jozsefmolnar.newskeletonapp.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel>(R.layout.details_fragment) {

    override val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt(Constants.FOO_ID_KEY)?.let {
            viewModel.setFooId(it)
        }
    }
}
