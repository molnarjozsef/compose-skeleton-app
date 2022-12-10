package com.jozsefmolnar.newskeletonapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = appColorScheme(),
        content = content
    )
}
