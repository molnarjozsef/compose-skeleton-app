package com.jozsefmolnar.newskeletonapp.model.domain

enum class NewsCountry(
    val countryCode: String,
    val displayName: String,
) {
    Hungary(
        countryCode = CountryCodeHu,
        displayName = "Hungary",
    ),
    GreatBritain(
        countryCode = CountryCodeGb,
        displayName = "Great Britain",
    ),
    Netherlands(
        countryCode = CountryCodeNl,
        displayName = "Netherlands",
    ),
    Germany(
        countryCode = CountryCodeDe,
        displayName = "Germany",
    ),
    Austria(
        countryCode = CountryCodeAt,
        displayName = "Austria",
    ),
    Czechia(
        countryCode = CountryCodeCz,
        displayName = "Czechia",
    );

    companion object {
        fun fromCountryCode(countryCode: String): NewsCountry? =
            NewsCountry.values().firstOrNull { it.countryCode == countryCode }
    }
}

private const val CountryCodeGb = "gb"
private const val CountryCodeHu = "hu"
private const val CountryCodeNl = "nl"
private const val CountryCodeAt = "at"
private const val CountryCodeDe = "de"
private const val CountryCodeCz = "cz"
