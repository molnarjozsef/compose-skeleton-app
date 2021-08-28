package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.jozsefmolnar.newskeletonapp.BR

abstract class BaseFragment<VM : ViewModel>(
    @LayoutRes private val contentViewId: Int
) : Fragment() {

    abstract val viewModel: VM

    private var binding: ViewDataBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            contentViewId,
            container,
            false
        )

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            lifecycleOwner = this@BaseFragment

            setVariable(BR.vm, viewModel)

            executePendingBindings()
        }
    }

    override fun onDestroyView() {
        binding?.unbind()
        binding = null

        super.onDestroyView()
    }

    @Suppress("UNCHECKED_CAST")
    protected fun <U : ViewDataBinding> getBinding(): U = binding as U
}
