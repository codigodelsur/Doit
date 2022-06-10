import src.Deps
import src.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = Versions.compileSDK

    defaultConfig {
        minSdk = Versions.minSDK
        targetSdk = Versions.targetSDK

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    // Dependency Injection
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)

    // Database
    implementation(Deps.room)
    kapt(Deps.roomCompiler)
    implementation(Deps.roomExt)

    // Concurrency
    implementation(Deps.coroutines)
    implementation(Deps.coroutinesAndroid)
}
