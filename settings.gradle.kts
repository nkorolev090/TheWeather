pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TheWeather"
include(":weatherAPI")
include(":dataBase")
include(":weatherData")
include(":weatherCommon")
include(":theweather")
include(":features:weather-main:ui")
include(":features:weather-main:ui-logic")
include(":uikit")
include(":firebaseAPI")
include(":features:clothes:ui")
include(":features:clothes:ui-logic")
