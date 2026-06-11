pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version("1.0.0")
}

rootProject.name = "deepslateMC"

include("deepslateMC-api", "deepslateMC-server")

gradle.lifecycle.beforeProject {
    version = providers.gradleProperty("mcVersion").get().trim()
}