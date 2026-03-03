pluginManagement {
    repositories {
        google()
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
rootProject.name = "Zauberfluff"
include(":core:domain")
include(":core:data")
include(":feature:monetization")
include(":feature:compliance")
include(":core:ui")
include(":feature:game")
include(":feature:tutorial")
