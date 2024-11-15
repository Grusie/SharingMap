import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.serialization)
    kotlin("kapt")
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.gruise.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField(
            "String",
            "KAKAO_REST_API_KEY",
            localProperties.getProperty("KAKAO_REST_API_KEY"),
        )
        buildConfigField(
            "String",
            "NAVER_ACCESS_KEY",
            localProperties.getProperty("NAVER_ACCESS_KEY"),
        )
        buildConfigField(
            "String",
            "NAVER_SECRET_KEY",
            localProperties.getProperty("NAVER_SECRET_KEY"),
        )
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // room
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // serialization
    implementation(libs.serialization.json)
    implementation(libs.serialization.converter)

    // retrofit2
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.gson)

    // okhttp
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.interceptor)

    // dataStore
    implementation(libs.data.store)
}