package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.databinding.MainFragmentBinding
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DataBindingUtil
        .inflate<MainFragmentBinding>(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )
        .apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

}
