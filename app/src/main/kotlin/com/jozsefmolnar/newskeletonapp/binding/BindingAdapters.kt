package com.jozsefmolnar.newskeletonapp.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("android:visibility")
fun View.bindVisibility(visible: Boolean?) {
    visible?.let {
        visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun ImageView.bindImageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
    }
}
