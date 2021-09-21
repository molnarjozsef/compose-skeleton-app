package com.jozsefmolnar.newskeletonapp.util

import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.FragmentActivity

fun Context.getActivity(): FragmentActivity? {
    var currentContext = this

    while (currentContext is ContextWrapper) {
        if (currentContext is FragmentActivity) {
            return currentContext
        }

        currentContext = currentContext.baseContext
    }

    return null
}

fun Context.requireActivity(): FragmentActivity = requireNotNull(getActivity())
