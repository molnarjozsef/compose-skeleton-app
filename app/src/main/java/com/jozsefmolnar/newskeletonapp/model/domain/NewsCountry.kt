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
    );

    companion object {
        fun fromCountryCode(countryCode: String): NewsCountry? {
            return when (countryCode) {
                CountryCodeGb -> GreatBritain
                CountryCodeHu -> Hungary
                CountryCodeNl -> Netherlands
                CountryCodeAt -> Austria
                CountryCodeDe -> Germany
                else -> null
            }
        }
    }
}

private const val CountryCodeGb = "gb"
private const val CountryCodeHu = "hu"
private const val CountryCodeNl = "nl"
private const val CountryCodeAt = "at"
private const val CountryCodeDe = "de"
