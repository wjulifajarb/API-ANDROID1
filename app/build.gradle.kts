plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "newcom.example.userlistappjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "newcom.example.userlistappjava"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Dependencias básicas
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity) // <= versión compatible
    implementation(libs.constraintlayout)

    // 🔥 Retrofit y Gson (para consumir APIs REST)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.gson)

    // 🔥 RecyclerView (para mostrar lista de datos)
    implementation(libs.recyclerview)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}