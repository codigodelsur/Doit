import src.Deps
import src.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Versions.compileSDK

    defaultConfig {
        applicationId = "com.codigodelsur.doit"
        minSdk = Versions.minSDK
        targetSdk = Versions.targetSDK
        versionCode = Versions.appVersionCode
        versionName = Versions.appVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(project(":core"))

    // Kotlin
    implementation(Deps.kotlinStdLib)

    // AndroidX
    implementation(Deps.androidExt)
    implementation(Deps.composeUi)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeUiPreviewTools)
    implementation(Deps.composeActivity)

    // Jetpack Navigation
    implementation(Deps.composeNavigation)

    // Jetpack Lifecycle
    implementation(Deps.lifecycle)
    implementation(Deps.viewModelLifecycle)

    // Dependency Injection
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltNavigationCompose)

    // Concurrency
    implementation(Deps.coroutines)
    implementation(Deps.coroutinesAndroid)

    // Testing
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitX)
    androidTestImplementation(Deps.espressoCore)
    androidTestImplementation(Deps.testRunner)
    androidTestImplementation(Deps.composeUiTest)

    // Debug
    debugImplementation(Deps.composeUiTools)
}
