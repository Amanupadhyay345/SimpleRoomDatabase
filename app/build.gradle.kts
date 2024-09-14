plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    // Added KAPT for annotation processing
    id("kotlin-kapt")
}

android {
    namespace = "com.example.simpleroomdatabase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.simpleroomdatabase"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
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
    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room dependencies — the stars of our show!
    implementation("androidx.room:room-ktx:2.6.1")

    // Room compiler — powered by KAPT, not annotationProcessor
    kapt("androidx.room:room-compiler:2.6.1")

    // Coroutines to keep things async and sleek
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // ViewModel KTX for lifecycle management
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
}

kapt {
    correctErrorTypes = true
}
