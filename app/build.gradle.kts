plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidSdk.compile
    buildToolsVersion = "30.0.2"

    defaultConfig {
        applicationId = "com.jozsefmolnar.newskeletonapp"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "NEWSAPI_KEY", project.getApiKey())
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
//        useIR = true
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.2"
    }
}

dependencies {

    implementation(Libraries.AndroidX.core)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.Compose.Ui.material)
    implementation(Libraries.Compose.Ui.constraintLayout)
    implementation(Libraries.AndroidX.viewModel)
    implementation(Libraries.AndroidX.lifecycleScope)
    implementation(Libraries.AndroidX.livedata)
    implementation(Libraries.AndroidX.Ui.material)
    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.Android.junit)

    // Hilt Dependency Injection
    implementation(Libraries.DI.Hilt.core)
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt(Libraries.DI.Hilt.compiler)

    // Retrofit
    implementation(Libraries.Networking.Retrofit.core)
    implementation(Libraries.Networking.Retrofit.moshiConverter)

    // Kotlin Coroutines
    implementation(Libraries.Kotlin.Coroutines.android)

    // Timber
    implementation(Libraries.Common.timber)

    // Room
    implementation(Libraries.Room.core)
    implementation(Libraries.Room.ktx)
    kapt(Libraries.Room.annotationProcessor)

    // SwipeRefreshLayout
    // TODO remove
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Glide
    // TODO remove
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")

    // BindingCollectionAdapter
    // TODO remove
    implementation("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:4.0.0")

    // Jetpack Compose
    implementation(Libraries.Compose.ui)
    // Tooling support (Previews, etc.)
    implementation(Libraries.Compose.tooling)
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation(Libraries.Compose.foundation)
    // Material Design
    implementation(Libraries.Compose.Ui.material)
    // Integration with activities
    implementation(Libraries.Compose.activity)
    // Integration with ViewModels
    implementation(Libraries.Compose.viewModel)
    // Navigation
    implementation(Libraries.Compose.Navigation.navigation)
    implementation(Libraries.Compose.Navigation.viewModel)
    // Compose Runtime
    implementation("androidx.compose.runtime:runtime:1.1.0-alpha04")


    implementation("com.github.skydoves:landscapist-glide:1.3.6")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-beta01")


    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test - junit4:1.0.2")

}
