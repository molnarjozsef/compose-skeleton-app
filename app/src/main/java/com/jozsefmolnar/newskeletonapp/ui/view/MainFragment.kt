package com.jozsefmolnar.newskeletonapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.adapter.ArticleAdapter
import com.jozsefmolnar.newskeletonapp.databinding.MainFragmentBinding
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.recyclerView
import kotlinx.android.synthetic.main.main_fragment.swipeContainer
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    @Inject
    lateinit var adapter: ArticleAdapter

    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ArticleAdapter()

        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.main_list_divider)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onResume() {
        super.onResume()

        viewModel.items.observe(this, { articles ->
            (recyclerView.adapter as? ArticleAdapter)?.dataSet = articles
            recyclerView.adapter?.notifyDataSetChanged()
            swipeContainer.isRefreshing = false
        })

        swipeContainer.setOnRefreshListener { viewModel.fetchLatestNews() }
    }
}
