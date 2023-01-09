@file:OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)

package com.jozsefmolnar.newskeletonapp.feature.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jozsefmolnar.newskeletonapp.model.domain.NewsCountry
import com.jozsefmolnar.newskeletonapp.ui.theme.Sizes
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Destination
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val selectedCountries by viewModel.selectedCountries.collectAsStateWithLifecycle()

    SettingsScreenContent(
        selectedCountries = selectedCountries.toPersistentList(),
        selectCountry = viewModel::selectCountry
    )
}

@Composable
private fun SettingsScreenContent(
    selectedCountries: ImmutableList<NewsCountry?>,
    selectCountry: (NewsCountry) -> Unit,
) {
    Column {
        TopAppBar(
            title = { Text("Settings") },
            windowInsets = WindowInsets(0.dp),
        )
        Spacer(Modifier.height(Sizes.Size500))
        Text(
            modifier = Modifier.padding(horizontal = Sizes.Size200),
            text = "News countries",
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(Modifier.height(Sizes.Size200))
        NewsCountry.values().forEach { newsCountry ->
            SettingsEntry(
                country = newsCountry,
                isSelected = selectedCountries.contains(newsCountry),
                selectCountry = { selectCountry(newsCountry) },
            )
        }
    }
}

@Composable
private fun SettingsEntry(
    country: NewsCountry,
    isSelected: Boolean,
    selectCountry: (Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            vertical = Sizes.Size100,
            horizontal = Sizes.Size200,
        ),
    ) {
        Text(
            text = country.displayName,
            modifier = Modifier.weight(1f),
        )
        Checkbox(
            checked = isSelected,
            onCheckedChange = selectCountry,
        )
    }
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsScreenContent(
        selectedCountries = persistentListOf(NewsCountry.GreatBritain),
        selectCountry = { },
    )
}
