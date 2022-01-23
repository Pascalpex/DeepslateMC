import io.papermc.paperweight.util.constants.*

plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.1.1" apply false
    id("io.papermc.paperweight.patcher") version "1.3.3"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") {
        content { onlyForConfigurations(PAPERCLIP_CONFIG) }
    }
}

dependencies {
    remapper("net.fabricmc:tiny-remapper:0.7.0:fat")
    decompiler("net.minecraftforge:forgeflower:1.5.498.22")
    paperclip("io.papermc:paperclip:3.0.2")
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
}

subprojects {
    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/groups/public/")
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://ci.emc.gs/nexus/content/groups/aikar/")
        maven("https://repo.aikar.co/content/groups/aikar")
        maven("https://repo.md-5.net/content/repositories/releases/")
        maven("https://hub.spigotmc.org/nexus/content/groups/public/")
        maven("https://jitpack.io")
    }
}

paperweight {
    serverProject.set(project(":deepslateMC-server"))

    remapRepo.set("https://maven.fabricmc.net/")
    decompileRepo.set("https://files.minecraftforge.net/maven/")

    useStandardUpstream("patina") {
        url.set(github("PatinaMC", "Patina"))
        ref.set(providers.gradleProperty("patinaRef"))

        withStandardPatcher {
            baseName("patina")

            apiOutputDir.set(layout.projectDirectory.dir("deepslateMC-api"))
            serverOutputDir.set(layout.projectDirectory.dir("deepslateMC-server"))

            remapRepo.set("https://maven.fabricmc.net/")
            decompileRepo.set("https://files.minecraftforge.net/maven/")
        }

        reobfPackagesToFix.addAll(
            "net.pascalpex",
            "org.sugarcanemc",
            "gg.airplane",
            "net.pl3x",
            "ca.spottedleaf",
            "me.jellysquid.mods"
        )
    }
}