package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.databinding.DetailsFragmentBinding
import com.jozsefmolnar.newskeletonapp.ui.model.DetailsViewModel

class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil
        .inflate<DetailsFragmentBinding>(
            inflater,
            R.layout.details_fragment,
            container,
            false
        )
        .apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}