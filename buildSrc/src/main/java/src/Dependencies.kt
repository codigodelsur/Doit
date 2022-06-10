package src

object Versions {

    // Build Config
    const val minSDK = 26
    const val compileSDK = 32
    const val targetSDK = 32
    const val buildTools = "30.0.2"

    // App version
    const val appVersionCode = 1
    const val appVersionName = "0.0.1"

    // Kotlin
    const val kotlin = "1.6.21"

    // AndroidX
    const val androidExt = "1.7.0"
    const val compose = "1.2.0-beta02"
    const val activity = "1.4.0"

    // Jetpack
    const val navigation = "2.4.2"
    const val lifecycle = "2.4.1"
    const val viewModelLifecycle = "2.4.0-rc01"
    const val room = "2.4.2"

    // Concurrency
    const val coroutines = "1.5.2"

    // Dependency Injection
    const val hilt = "2.42"
    const val hiltNavigationCompose = "1.0.0-alpha03"

    // Testing
    const val junit = "4.13.2"
    const val junitX = "1.1.3"
    const val espresso = "3.4.0"
    const val runner = "1.1.0"
}

object Deps {

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // AndroidX
    const val androidExt = "androidx.core:core-ktx:${Versions.androidExt}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeUiPreviewTools = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeUiTools = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.activity}"

    // AndroidX Room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomExt = "androidx.room:room-ktx:${Versions.room}"

    // Jetpack Navigation
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    // Jetpack Lifecycle
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelLifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelLifecycle}"

    // Concurrency
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Dependency Injection
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

    // Testing
    const val junit = "junit:junit:${Versions.junit}"
    const val junitX = "androidx.test.ext:junit:${Versions.junitX}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val testRunner = "androidx.test:runner:${Versions.runner}"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
}
