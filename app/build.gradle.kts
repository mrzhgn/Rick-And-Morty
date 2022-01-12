plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.mrzhgn.rickandmortytest"
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    viewBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Core
    implementation(Dependencies.Core.core)
    implementation(Dependencies.Core.appcompat)
    implementation(Dependencies.Core.material)
    implementation(Dependencies.Core.constraintLayout)
    implementation(Dependencies.Core.fragment)
    implementation(Dependencies.Core.recyclerView)

    // Tests
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.junit2)
    androidTestImplementation(Dependencies.Test.espresso)

    // Dependency Injection
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.DI.hiltX)
    kapt(Dependencies.DI.hiltCompiler)
    kapt(Dependencies.DI.hiltXCompiler)

    // Architecture components
    implementation(Dependencies.Architecture.lifecycle)
    implementation(Dependencies.Architecture.viewModel)
    implementation(Dependencies.Architecture.liveData)
    kapt(Dependencies.Architecture.lifecycleCompiler)

    // Navigation
    implementation(Dependencies.Architecture.navigation)
    implementation(Dependencies.Architecture.navigationUi)
    implementation(Dependencies.Architecture.navFeatures)

    // RxJava
    implementation(Dependencies.Async.rxJava)
    implementation(Dependencies.Async.rxAndroid)

    // DataStore
    implementation(Dependencies.Data.dataStore)
    implementation(Dependencies.Data.dataStoreCore)
    implementation(Dependencies.Data.dataStoreAdapter)

    // Room
    implementation(Dependencies.Data.room)
    implementation(Dependencies.Data.roomKotlin)
    implementation(Dependencies.Data.roomAdapter)
    kapt(Dependencies.Data.roomCompiler)

    // Retrofit
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.gson)
    implementation(Dependencies.Network.adapter)

    // Glide
    implementation(Dependencies.Image.glide)
    kapt(Dependencies.Image.glideCompiler)
}