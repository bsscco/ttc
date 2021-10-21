plugins {
    id("com.android.application")
    kotlin("plugin.serialization") version Versions.Kotlin.KOTLIN
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.ttc"
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    buildFeatures {
        buildConfig = true
        viewBinding = false
        dataBinding = true
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":shared:domain"))
    implementation(project(":shared:data"))
    implementation(project(":feature:feed"))

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.Kotlin.KOTLIN_COROUTINE}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.KOTLINX_SERIALIZATION_JSON}")

    // Jetpack
    implementation("androidx.navigation:navigation-runtime-ktx:${Versions.Jetpack.NAVIGATION}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.Jetpack.NAVIGATION}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.Jetpack.NAVIGATION}")
    implementation("androidx.activity:activity-ktx:${Versions.Jetpack.ACTIVITY}")
    implementation("androidx.fragment:fragment-ktx:${Versions.Jetpack.FRAGMENT}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-process:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.appcompat:appcompat:${Versions.Jetpack.APP_COMPAT}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.Jetpack.CONSTRAINT_LAYOUT}")
    implementation("androidx.viewpager2:viewpager2:${Versions.Jetpack.VIEW_PAGER_2}")
    testImplementation("androidx.arch.core:core-testing:${Versions.Jetpack.ARCH_CORE}")
    implementation("com.google.dagger:hilt-android:${Versions.Jetpack.HILT}")
    testImplementation("com.google.dagger:hilt-android-testing:${Versions.Jetpack.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
    kaptTest("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
    implementation("androidx.hilt:hilt-navigation-fragment:${Versions.Jetpack.HILT_NAVIGATION_FRAGMENT}")
    implementation("androidx.legacy:legacy-support-v4:${Versions.Jetpack.LEGACY}")
    testImplementation("androidx.test:core-ktx:${Versions.Jetpack.TEST}")
    implementation("androidx.recyclerview:recyclerview:${Versions.Jetpack.RECYCLER_VIEW}")

    // Okhttp
    implementation("com.squareup.okhttp3:okhttp:${Versions.Okhttp.OKHTTP}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.Okhttp.LOGGING_INTERCEPTOR}")
}