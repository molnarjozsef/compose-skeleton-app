package com.jozsefmolnar.newskeletonapp.feature.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jozsefmolnar.newskeletonapp.model.domain.Weather
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes

@Composable
fun WeatherCard(
    weather: Weather,
    city: String?,
    modifier: Modifier = Modifier,
) {
    val cityLabel = if (city != null) {
        " – $city"
    } else {
        ""
    }

    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(Sizes.Size200)) {
            Text(
                text = "${weather.temperature}°C$cityLabel",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )

            weather.description?.let {
                Spacer(Modifier.height(Sizes.Size100))

                Text(
                    text = weather.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}
