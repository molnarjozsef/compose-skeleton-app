package com.jozsefmolnar.newskeletonapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun parseArticleDateTime(articleDateTime: String): Date? =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .parse(
                articleDateTime
                    .replace("T", " ")
                    .replace("Z", "")
            )

    fun Date.format(): String = SimpleDateFormat().format(this)
}
