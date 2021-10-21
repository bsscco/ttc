import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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
            buildConfigField("String", "devApiHost", gradleLocalProperties(rootDir).getProperty("devApiHost"))
            buildConfigField("String", "apiHost", gradleLocalProperties(rootDir).getProperty("apiHost"))
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        release {
            buildConfigField("String", "apiHost", gradleLocalProperties(rootDir).getProperty("apiHost"))
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
    implementation(project(":shared:util"))
    implementation(project(":shared:domain"))

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.Kotlin.KOTLIN_COROUTINE}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.KOTLIN_COROUTINE}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.KOTLINX_SERIALIZATION_JSON}")

    // Jetpack
    implementation("androidx.room:room-runtime:${Versions.Jetpack.ROOM}")
    kapt("androidx.room:room-compiler:${Versions.Jetpack.ROOM}")
    implementation("androidx.room:room-ktx:${Versions.Jetpack.ROOM}")
    implementation("com.google.dagger:hilt-android:${Versions.Jetpack.HILT}")
    testImplementation("com.google.dagger:hilt-android-testing:${Versions.Jetpack.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
    kaptTest("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
    testImplementation("androidx.test:core-ktx:${Versions.Jetpack.TEST}")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.Retrofit.RETROFIT}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.Retrofit.CONVERTER_GSON}")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Retrofit.KOTLINX_SERIALIZATION_CONVERTER}")

    // Okhttp
    implementation("com.squareup.okhttp3:okhttp:${Versions.Okhttp.OKHTTP}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.Okhttp.LOGGING_INTERCEPTOR}")
}