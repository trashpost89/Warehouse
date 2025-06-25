pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.1.3"
        id("org.jetbrains.kotlin.android") version "1.9.22"
        id("org.jetbrains.kotlin.plugin.compose") version "1.9.22"  // Добавлено
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            // Версии
            version("androidx-core", "1.12.0")
            version("appcompat", "1.6.1")
            version("material", "1.11.0")
            version("constraintlayout", "2.1.4")
            version("lifecycle", "2.7.0")
            version("navigation", "2.7.7")
            version("room", "2.6.1")
            version("junit", "4.13.2")
            version("androidx-junit", "1.1.5")
            version("espresso", "3.5.1")
            version("commons-csv", "1.10.0")
            version("compose-bom", "2024.02.01") // Добавьте в versionCatalogs
            // Библиотеки
            library("androidx-core-ktx", "androidx.core", "core-ktx").versionRef("androidx-core")
            library("androidx-appcompat", "androidx.appcompat", "appcompat").versionRef("appcompat")
            library("material", "com.google.android.material", "material").versionRef("material")
            library("androidx-constraintlayout", "androidx.constraintlayout", "constraintlayout").versionRef("constraintlayout")
            library("androidx-lifecycle-livedata", "androidx.lifecycle", "lifecycle-livedata-ktx").versionRef("lifecycle")
            library("androidx-lifecycle-viewmodel", "androidx.lifecycle", "lifecycle-viewmodel-ktx").versionRef("lifecycle")
            library("androidx-navigation-fragment", "androidx.navigation", "navigation-fragment-ktx").versionRef("navigation")
            library("androidx-navigation-ui", "androidx.navigation", "navigation-ui-ktx").versionRef("navigation")

            // Room
            library("androidx-room-runtime", "androidx.room", "room-runtime").versionRef("room")
            library("androidx-room-compiler", "androidx.room", "room-compiler").versionRef("room")
            library("androidx-room-ktx", "androidx.room", "room-ktx").versionRef("room")

            // CSV
            library("commons-csv", "org.apache.commons", "commons-csv").versionRef("commons-csv")

            // Тестирование
            library("junit", "junit", "junit").versionRef("junit")
            library("androidx-junit", "androidx.test.ext", "junit").versionRef("androidx-junit")
            library("androidx-espresso", "androidx.test.espresso", "espresso-core").versionRef("espresso")
        }
    }
}

rootProject.name = "WarehouseApp"
include(":app")