package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.jozsefmolnar.newskeletonapp.BR
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.databinding.MainFragmentBinding
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import com.jozsefmolnar.newskeletonapp.util.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

@AndroidEntryPoint
class MainFragment :
    BaseFragment<MainViewModel>(R.layout.main_fragment),
    OnItemClickListener<Article> {

    override val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = getBinding<MainFragmentBinding>()

        val itemBind = OnItemBindClass<Article>()
            .map(Article::class.java) { itemBinding, _, _ ->
                itemBinding.set(BR.item, R.layout.main_list_item)
            }

        binding.itemBinding = ItemBinding.of(itemBind)
            .bindExtra(BR.itemClickListener, this)

        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.main_list_divider)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.recyclerView.addItemDecoration(dividerItemDecoration)

        binding.swipeContainer.setOnRefreshListener { viewModel.fetchLatestNews() }
    }

    override fun onItemClicked(item: Article) {
        val bundle = bundleOf("articleId" to item.id)
        findNavController(this).navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
    }
}
