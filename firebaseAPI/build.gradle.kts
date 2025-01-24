plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)

    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.firebaseapi"
    compileSdk = libs.versions.androidSdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidSdk.min.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.android)

    implementation(platform(libs.google.firebase.bom))
    implementation(libs.google.firebase.firestore)
    implementation(libs.google.firebase.auth)
}