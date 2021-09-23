package com.jozsefmolnar.newskeletonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.newskeletonapp.navigation.SimpleNavigator
import com.jozsefmolnar.newskeletonapp.navigation.Navigation
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import com.jozsefmolnar.newskeletonapp.ui.theme.NewSkeletonAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ComposeMainActivity : ComponentActivity() {

    @Inject
    lateinit var simpleNavigator: SimpleNavigator

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchLatestNews()
        setContent {
            Navigation(simpleNavigator)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewSkeletonAppTheme {
    }
}
