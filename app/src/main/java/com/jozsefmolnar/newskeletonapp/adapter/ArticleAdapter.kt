package com.jozsefmolnar.newskeletonapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jozsefmolnar.newskeletonapp.R
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.ui.model.MainItemViewModel
import javax.inject.Inject

class ArticleAdapter @Inject constructor() : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var dataSet = emptyList<Article>()

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.executePendingBindings()
        }
    }

    private fun getItemViewModel(item: Article) = MainItemViewModel(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.main_list_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        holder.binding.apply {
            setVariable(BR.vm, getItemViewModel(item))
            executePendingBindings()
        }

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("articleId" to item.id)
            it.findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
        }
    }

    override fun getItemCount() = dataSet.size
}
