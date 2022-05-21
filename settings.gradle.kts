pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

rootProject.name = "deepslateMC"

include("deepslateMC-api", "deepslateMC-server")
