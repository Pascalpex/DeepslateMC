pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "deepslateMC"

include("deepslateMC-api", "deepslateMC-server")
