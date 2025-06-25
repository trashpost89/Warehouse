plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android") // Без версии - берется из корневого
    id("androidx.navigation.safeargs.kotlin") // Без версии
}
android {
    namespace = "com.example.warehouse"
    compileSdk = 34 // Изменено с 36 на 34 для совместимости

    defaultConfig {
        applicationId = "com.example.warehouse"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17 // Обновлено с 11 на 17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        compose = true  // Добавлено для поддержки Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"  // Совместимо с Kotlin 1.9.22
    }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-ktx:1.8.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6") // Обновлено
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6") // Обновлено

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
// Добавьте явную зависимость для kapt
    kapt("androidx.hilt:hilt-compiler:1.0.0")


    // Preferences
    implementation("androidx.preference:preference-ktx:1.2.1")

    // CSV
    implementation("com.opencsv:opencsv:5.7.1")

    // DateTime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // UI Components
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Compose dependencies (ДОБАВЛЕНО)
    val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")
    implementation(platform(libs.findLibrary("androidx-compose-bom").get()))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.8.1")
    debugImplementation("androidx.compose.ui:ui-tooling")

}

kapt {
    correctErrorTypes = true
}