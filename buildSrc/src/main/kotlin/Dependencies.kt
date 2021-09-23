object BuildPlugins {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.2"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"

}

object AndroidSdk {
    const val min = 23
    const val compile = 31
    const val target = compile
}

object Versions {
    const val minsdk = 23
    const val targetsdk = 31

    const val kotlin = "1.5.21"
    const val coroutines = "1.5.1"

    const val compose = "1.0.2"
    const val lifecycle = "2.4.0-alpha03"


    const val hilt = "2.38.1"

    const val room = "2.3.0"

    const val retrofit = "2.9.0"

    const val testRunner = "1.3.0"
}

object Libraries {

    object Kotlin {

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val core = "androidx.core:core-ktx:1.6.0"
        const val lifecycleScope = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        object Ui {
            const val material = "com.google.android.material:material:1.4.0"
            const val activity = "androidx.activity:activity-ktx:1.3.1"
        }
    }

    object Compose {
        const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

        const val activity = "androidx.activity:activity-compose:1.3.1"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha04"

        object Ui {
            const val material = "androidx.compose.material:material:${Versions.compose}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
        }

        object Navigation {
            const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha08"
            const val viewModel = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
        }
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
            const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        }
    }

    object Common {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
        const val coilCompose = "io.coil-kt:coil-compose:1.3.2"
    }

    object Test {
        const val junit = "junit:junit:4.13.2"

        object Android {
            const val junit = "androidx.test.ext:junit-ktx:1.1.3"
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
