pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "Pascalpex"

include("Pascalpex-API", "Pascalpex-Server")
