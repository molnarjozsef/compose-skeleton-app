@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)

package com.jozsefmolnar.newskeletonapp.feature.home

import android.content.Context
import android.location.Geocoder
import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.jozsefmolnar.newskeletonapp.feature.destinations.DetailsScreenDestination
import com.jozsefmolnar.newskeletonapp.feature.home.components.HomeList
import com.jozsefmolnar.newskeletonapp.model.domain.Article
import com.jozsefmolnar.newskeletonapp.model.domain.Weather
import com.jozsefmolnar.newskeletonapp.util.OnResume
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    context: Context = LocalContext.current,
) {
    val articles by viewModel.articles.collectAsStateWithLifecycle()
    val weather by viewModel.weather.collectAsStateWithLifecycle()
    var location: Location? by remember { mutableStateOf(null) }
    var city: String? by remember { mutableStateOf(null) }

    LocationUpdater(onLocationAvailable = { location = it })

    OnResume { coroutineScope.launch { viewModel.refreshNews() } }

    LaunchedEffect(location) {
        location?.let { location ->
            viewModel.refreshWeather(location)

            val address = Geocoder(context)
                .getFromLocation(
                    location.latitude,
                    location.longitude,
                    1,
                )
                ?.first()

            city = address?.locality
        }
    }

    HomeScreenContent(
        articles = articles?.toPersistentList(),
        weather = weather,
        city = city,
        onNewsItemClicked = { navigator.navigate(DetailsScreenDestination.invoke(it.id!!)) },
        refresh = {
            viewModel.refreshNews()
            location?.let { viewModel.refreshWeather(it) }

            // We need to add some delay, because if both calls are optimized out,
            // the pull-refresh indicator will get stuck due to Material 3 incompatibility
            @Suppress("MagicNumber")
            delay(300)
        }
    )
}

@Composable
fun HomeScreenContent(
    articles: ImmutableList<Article>?,
    weather: Weather?,
    city: String?,
    onNewsItemClicked: (Article) -> Unit,
    refresh: suspend () -> Unit,
) {
    Column {
        TopAppBar(
            title = { Text("News") },
            windowInsets = WindowInsets(0.dp),
        )

        HomeList(
            articles = articles,
            weather = weather,
            city = city,
            onNewsItemClicked = onNewsItemClicked,
            refresh = refresh,
        )
    }
}

@Composable
fun LocationUpdater(
    onLocationAvailable: (Location) -> Unit,
    context: Context = LocalContext.current,
) {

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    OnResume {
        if (!locationPermissionState.allPermissionsGranted) {
            locationPermissionState.launchMultiplePermissionRequest()
        } else {
            try {
                LocationServices.getFusedLocationProviderClient(context)
                    .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                    .addOnSuccessListener { onLocationAvailable(it) }
            } catch (e: SecurityException) {
                Timber.e(e)
            }
        }
    }
}
