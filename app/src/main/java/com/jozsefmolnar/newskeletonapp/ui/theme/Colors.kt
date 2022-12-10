package com.jozsefmolnar.newskeletonapp.ui.theme

import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorPalette = darkColorScheme(
    // Override colors here
)

private val LightColorPalette = lightColorScheme(
    // Override colors here
)

@Composable
fun appColorScheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    context: Context = LocalContext.current,
): ColorScheme {
    val dynamicColorAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val darkColorScheme = if (dynamicColorAvailable) {
        dynamicDarkColorScheme(context)
    } else {
        DarkColorPalette
    }

    val lightColorScheme = if (dynamicColorAvailable) {
        dynamicLightColorScheme(context)
    } else {
        LightColorPalette
    }

    return if (darkTheme) {
        darkColorScheme
    } else {
        lightColorScheme
    }
}
