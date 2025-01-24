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
include(":features:recommendations:ui")
include(":features:recommendations:ui-logic")
include(":features:authentication:ui")
include(":features:authentication:ui-logic")
