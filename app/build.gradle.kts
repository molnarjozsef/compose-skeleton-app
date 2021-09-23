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

    // Androidx
    implementation(Libraries.AndroidX.core)
    implementation(Libraries.AndroidX.appcompat)
    implementation(Libraries.AndroidX.viewModel)
    implementation(Libraries.AndroidX.lifecycleScope)
    implementation(Libraries.AndroidX.livedata)
    implementation(Libraries.AndroidX.Ui.material)

    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.tooling)
    implementation(Libraries.Compose.foundation)
    implementation(Libraries.Compose.activity)
    implementation(Libraries.Compose.viewModel)
    implementation(Libraries.Compose.Ui.material)
    implementation(Libraries.Compose.Ui.constraintLayout)
    implementation(Libraries.Compose.Navigation.navigation)
    implementation(Libraries.Compose.Navigation.viewModel)

    // Hilt
    implementation(Libraries.DI.Hilt.core)
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

    // Coil
    implementation(Libraries.Common.coilCompose)
    implementation(Libraries.AndroidX.lifecycleScope)

    // Tests
    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.Android.junit)

}
