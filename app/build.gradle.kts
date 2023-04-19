plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kaptPlugin)
    id(BuildPlugins.hiltPlugin)
    id(BuildPlugins.detektPlugin) version "1.22.0"
    kotlin(BuildPlugins.kotlinSerializationPlugin) version Versions.kotlin
}

android {
    namespace = "com.jozsefmolnar.newskeletonapp"
    compileSdk = AndroidSdk.compile
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.jozsefmolnar.skeletonapp"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
}

dependencies {

    // AndroidX
    implementation(Libraries.AndroidX.core)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.AndroidX.viewModel)
    implementation(Libraries.AndroidX.lifecycleScope)

    // Compose
    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.tooling)
    implementation(Libraries.Compose.foundation)
    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.viewModel)
    implementation(Libraries.Compose.lifecycle)
    implementation(Libraries.Compose.Ui.material)
    implementation(Libraries.Compose.Navigation.navigation)
    implementation(Libraries.Compose.Navigation.viewModel)

    // Accompanist
    implementation(Libraries.Accompanist.systemUiController)

    // Hilt
    implementation(Libraries.Hilt.core)
    kapt(Libraries.Hilt.compiler)

    // Retrofit
    implementation(Libraries.Retrofit.core)
    implementation(Libraries.Retrofit.kotlinSerializationConverter)

    // Kotlin
    implementation(Libraries.Kotlin.Coroutines.android)
    implementation(Libraries.Kotlin.serialization)
    implementation(Libraries.Kotlin.immutableCollections)

    // Timber
    implementation(Libraries.Common.timber)

    // Room
    implementation(Libraries.Room.core)
    implementation(Libraries.Room.ktx)
    kapt(Libraries.Room.annotationProcessor)

    // Coil
    implementation(Libraries.Common.coilCompose)
    implementation(Libraries.AndroidX.lifecycleScope)

    // Tests
    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.Android.junit)

    // Detekt plugins
    detektPlugins(DetektPlugins.formatting)
    detektPlugins(DetektPlugins.twitterCompose)

}

detekt {
    config = rootProject.files("detekt/config.yml")
    baseline = rootProject.file("detekt/baseline.xml")
    buildUponDefaultConfig = true

    autoCorrect = true

    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")

    basePath = projectDir.toString()
}
