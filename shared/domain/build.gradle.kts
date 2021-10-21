plugins {
    id("com.android.library")
    kotlin("plugin.serialization") version Versions.Kotlin.KOTLIN
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    api(project(":shared:util"))

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.Kotlin.KOTLIN_COROUTINE}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.KOTLINX_SERIALIZATION_JSON}")

    // Jetpack
    implementation("com.google.dagger:hilt-android:${Versions.Jetpack.HILT}")
    testImplementation("com.google.dagger:hilt-android-testing:${Versions.Jetpack.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
    kaptTest("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
}