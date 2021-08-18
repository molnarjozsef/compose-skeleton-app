package com.jozsefmolnar.newskeletonapp.binding

import android.view.View
import androidx.databinding.BindingAdapter

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
