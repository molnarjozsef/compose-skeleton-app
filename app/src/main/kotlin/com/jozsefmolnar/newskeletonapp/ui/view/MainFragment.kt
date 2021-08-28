package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.jozsefmolnar.newskeletonapp.BR
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.databinding.MainFragmentBinding
import com.jozsefmolnar.newskeletonapp.model.domain.Foo
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import com.jozsefmolnar.newskeletonapp.util.Constants
import com.jozsefmolnar.newskeletonapp.util.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainViewModel>(R.layout.main_fragment),
    OnItemClickListener<Foo> {

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = getBinding<MainFragmentBinding>()

        val itemBind = OnItemBindClass<Foo>()
            .map(Foo::class.java) { itemBinding, _, _ ->
                itemBinding.set(BR.item, R.layout.main_list_item)
            }

        binding.itemBinding = ItemBinding.of(itemBind)
            .bindExtra(BR.itemClickListener, this)

        binding.swipeContainer.setOnRefreshListener { viewModel.fetchLatestFoo() }
    }

    override fun onItemClicked(item: Foo) {
        val bundle = bundleOf(Constants.FOO_ID_KEY to item.id)
        findNavController(this).navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }
}
