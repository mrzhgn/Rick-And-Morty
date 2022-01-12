object Dependencies {

    object Core {
        const val core = "androidx.core:core-ktx:1.7.0"
        const val appcompat = "androidx.appcompat:appcompat:1.4.0"
        const val material = "com.google.android.material:material:1.4.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.2"
        const val fragment = "androidx.fragment:fragment-ktx:1.4.0"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
    }

    object Test {
        const val junit = "junit:junit:4.+"
        const val junit2 = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object DI {
        const val hiltVersion = "2.38.1"
        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltX = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val hiltXCompiler = "androidx.hilt:hilt-compiler:1.0.0"
    }

    object Architecture {
        const val lifecycleVersion = "2.4.0"
        const val navVersion = "2.3.5"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:$navVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navVersion"
        const val navFeatures = "androidx.navigation:navigation-dynamic-features-fragment:$navVersion"
    }

    object Async {
        const val rxJavaVersion = "3.0.0"
        const val rxJava = "io.reactivex.rxjava3:rxjava:$rxJavaVersion"
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:$rxJavaVersion"
    }

    object Data {
        const val dataStoreVersion = "1.0.0"
        const val roomVersion = "2.4.0"
        const val dataStore = "androidx.datastore:datastore-preferences:$dataStoreVersion"
        const val dataStoreCore = "androidx.datastore:datastore-preferences-core:$dataStoreVersion"
        const val dataStoreAdapter = "androidx.datastore:datastore-preferences-rxjava3:$dataStoreVersion"
        const val room = "androidx.room:room-runtime:$roomVersion"
        const val roomKotlin = "androidx.room:room-ktx:$roomVersion"
        const val roomAdapter = "androidx.room:room-rxjava3:$roomVersion"
        const val roomCompiler =  "androidx.room:room-compiler:$roomVersion"
    }

    object Network {
        const val retrofitVersion = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val adapter = "com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0"
    }

    object Image {
        const val glideVersion = "4.12.0"
        const val glide = "com.github.bumptech.glide:glide:$glideVersion"
        const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"
    }

}