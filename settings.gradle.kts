pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "DeepslateMC"

include("DeepslateMC-API", "DeepslateMC-Server")
