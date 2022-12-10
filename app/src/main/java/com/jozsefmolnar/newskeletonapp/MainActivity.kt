package com.jozsefmolnar.newskeletonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jozsefmolnar.newskeletonapp.model.domain.NewsCountry
import com.jozsefmolnar.newskeletonapp.navigation.AppNavHost
import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import com.jozsefmolnar.newskeletonapp.repository.NewsRepository
import com.jozsefmolnar.newskeletonapp.repository.SettingsRepository
import com.jozsefmolnar.newskeletonapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var simpleNavigator: SimpleNavigator

    @Inject
    lateinit var settingsRepository: SettingsRepository

    @Inject
    lateinit var newsRepository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            if (settingsRepository.getSelectedCountries().first().isEmpty()) {
                settingsRepository.selectCountry(NewsCountry.Hungary)
            }

            newsRepository.fetchLatestNews()
        }

        setContent {
            AppTheme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colorScheme.background,
                    darkIcons = !isSystemInDarkTheme(),
                )
                systemUiController.setNavigationBarColor(color = Color.Transparent)

                Box(
                    Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    AppNavHost(simpleNavigator)
                }
            }
        }
    }
}
