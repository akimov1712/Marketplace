plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp")
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs.kotlin")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.fake.marketplace"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fake.marketplace"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    val room = "2.6.1"
    val hilt = "2.48.1"
    val retrofit = "2.9.0"
    val navigation = "2.7.6"
    val okHttp = "4.12.0"

//    View
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")

//    Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofit")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttp")
    implementation("com.squareup.okhttp3:okhttp:$okHttp")

//    Hilt
    implementation ("com.google.dagger:hilt-android:$hilt")
    ksp ("com.google.dagger:hilt-compiler:$hilt")

//    Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$navigation")
    implementation ("androidx.navigation:navigation-ui-ktx:$navigation")

//    JSON
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("org.jsoup:jsoup:1.16.2")

//    Room
    implementation("androidx.room:room-runtime:$room")
    ksp("androidx.room:room-compiler:$room")
    implementation("androidx.room:room-ktx:$room")

//    ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

//    Default
    implementation("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}