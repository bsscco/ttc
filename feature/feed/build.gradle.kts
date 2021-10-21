plugins {
    id("com.android.library")
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
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        buildConfig = true
        viewBinding = false
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Jetpack.COMPOSE
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
    implementation(project(":shared:util"))
    implementation(project(":shared:domain"))

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
    implementation("androidx.navigation:navigation-compose:${Versions.Jetpack.NAVIGATION}")
    implementation("androidx.activity:activity-ktx:${Versions.Jetpack.ACTIVITY}")
    implementation("androidx.activity:activity-compose:${Versions.Jetpack.ACTIVITY}")
    implementation("androidx.fragment:fragment-ktx:${Versions.Jetpack.FRAGMENT}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-process:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Jetpack.LIFECYCLE}")
    implementation("androidx.appcompat:appcompat:${Versions.Jetpack.APP_COMPAT}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.Jetpack.CONSTRAINT_LAYOUT}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${Versions.Jetpack.CONSTRAINT_LAYOUT_COMPOSE}")
    implementation("androidx.viewpager2:viewpager2:${Versions.Jetpack.VIEW_PAGER_2}")
    testImplementation("androidx.arch.core:core-testing:${Versions.Jetpack.ARCH_CORE}")
    api("com.google.dagger:hilt-android:${Versions.Jetpack.HILT}")
    testImplementation("com.google.dagger:hilt-android-testing:${Versions.Jetpack.HILT}")
    kapt("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
    kaptTest("com.google.dagger:hilt-android-compiler:${Versions.Jetpack.HILT}")
    implementation("androidx.hilt:hilt-navigation-fragment:${Versions.Jetpack.HILT_NAVIGATION_FRAGMENT}")
    implementation("androidx.hilt:hilt-navigation-compose:${Versions.Jetpack.HILT_NAVIGATION_COMPOSE}")
    implementation("androidx.legacy:legacy-support-v4:${Versions.Jetpack.LEGACY}")
    testImplementation("androidx.test:core-ktx:${Versions.Jetpack.TEST}")
    implementation("androidx.recyclerview:recyclerview:${Versions.Jetpack.RECYCLER_VIEW}")
    implementation("androidx.compose.compiler:compiler:${Versions.Jetpack.COMPOSE}")
    implementation("androidx.compose.ui:ui:${Versions.Jetpack.COMPOSE}")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:${Versions.Jetpack.COMPOSE}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.Jetpack.COMPOSE}")
    implementation("androidx.compose.ui:ui-viewbinding:${Versions.Jetpack.COMPOSE}")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:${Versions.Jetpack.COMPOSE}")
    // Material Design
    implementation("androidx.compose.material:material:${Versions.Jetpack.COMPOSE}")
    // Animations
    implementation("androidx.compose.animation:animation:${Versions.Jetpack.COMPOSE}")
    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:${Versions.Jetpack.COMPOSE}")
    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.Jetpack.COMPOSE}")

    // Accompanist
    implementation("com.google.accompanist:accompanist-flowlayout:${Versions.Accompanist.FLOW_LAYOUT}")
}