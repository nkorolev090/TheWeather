plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.google.services)

    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.project.theweather"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()

    defaultConfig {
        applicationId = "com.project.theweather"
        minSdk = libs.versions.androidSdk.min.get().toInt()
        targetSdk = libs.versions.androidSdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "WEATHER_API_KEY", "\"0ab1b21790762bc0577c8fd4b075fb23\"")
        buildConfigField("String", "WEATHER_API_BASE_URL", "\"https://api.openweathermap.org/data/2.5/\"")
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
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.android)

    implementation(platform(libs.squareup.okhttp.bom))
    implementation(libs.squareup.okhttp)
    implementation(libs.squareup.okhttp.logging)

    implementation(libs.kotlinx.serialization.json)

    implementation(platform(libs.google.firebase.bom))
    implementation(libs.google.firebase.firestore)
    implementation(libs.google.firebase.auth)

    implementation(libs.google.services.auth)

    implementation(project(":features:weather-main:ui"))
    implementation(project(":features:authentication:ui"))
    implementation(project(":uikit"))

    implementation(project(":weatherAPI"))
    implementation(project(":weatherData"))
    implementation(project(":dataBase"))
    implementation(project(":weatherCommon"))
}