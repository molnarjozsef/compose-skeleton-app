object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.3.1"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kaptPlugin = "kotlin-kapt"
    const val hiltPlugin = "dagger.hilt.android.plugin"
    const val kotlinSerializationPlugin = "plugin.serialization"
    const val versionsPlugin = "com.github.ben-manes.versions"
    const val detektPlugin = "io.gitlab.arturbosch.detekt"
}

object DetektPlugins {
    const val formatting = "io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0"
    const val twitterCompose = "com.twitter.compose.rules:detekt:0.0.26"
}

object AndroidSdk {
    const val min = 23
    const val compile = 33
    const val target = compile
}

object Versions {
    const val minsdk = 23
    const val targetsdk = 31

    const val kotlin = "1.7.20"
    const val coroutines = "1.6.4"

    const val compose = "1.3.1"
    const val lifecycle = "2.5.1"


    const val hilt = "2.44.2"

    const val room = "2.4.3"

    const val retrofit = "2.9.0"

    const val testRunner = "1.3.0"
}

object Libraries {

    object Kotlin {

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }

        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
        const val immutableCollections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.5"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.5.1"
        const val core = "androidx.core:core-ktx:1.9.0"
        const val lifecycleScope = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        object Ui {
            const val activity = "androidx.activity:activity-ktx:1.3.1"
        }
    }

    object Compose {
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

        const val activity = "androidx.activity:activity-compose:1.6.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03"

        object Ui {
            const val material = "androidx.compose.material3:material3:1.0.1"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
        }

        object Navigation {
            const val navigation = "androidx.navigation:navigation-compose:2.5.3"
            const val viewModel = "androidx.hilt:hilt-navigation-compose:1.0.0"
        }
    }

    object Accompanist {
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:0.28.0"
    }

    object DI {
        object Hilt {
            const val core = "com.google.dagger:hilt-android:${Versions.hilt}"
            const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        }
    }

    object Room {
        const val core = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val annotationProcessor = "androidx.room:room-compiler:${Versions.room}"
    }

    object Networking {

        object Retrofit {
            const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val kotlinSerializationConverter =
                "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
        }
    }

    object Common {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
        const val coilCompose = "io.coil-kt:coil-compose:2.2.2"
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.18.1"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"

        object Android {
            const val junit = "androidx.test.ext:junit-ktx:1.1.4"
            const val core = "androidx.arch.core:core-testing:2.1.0"
            const val runner = "androidx.test:runner:${Versions.testRunner}"
            const val rules = "androidx.test:rules:${Versions.testRunner}"
        }

        object Kotlin {
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        }
    }

}
