package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel>(R.layout.details_fragment) {

    override val viewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("articleId")?.let {
            viewModel.setArticleId(it)
        }
    }
}
