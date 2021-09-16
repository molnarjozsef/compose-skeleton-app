package com.jozsefmolnar.newskeletonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.newskeletonapp.ui.model.MainViewModel
import com.jozsefmolnar.newskeletonapp.ui.theme.NewSkeletonAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeMainActivity() : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchLatestNews()
        setContent {
            Navigation()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewSkeletonAppTheme {
        Navigation()
    }
}
