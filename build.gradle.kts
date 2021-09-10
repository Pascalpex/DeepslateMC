import io.papermc.paperweight.util.constants.*

plugins {
    java
    `maven-publish`
    id("com.github.johnrengelman.shadow") version "7.0.0" apply false
    id("io.papermc.paperweight.patcher") version "1.1.11"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/") {
        content { onlyForConfigurations(PAPERCLIP_CONFIG) }
    }
}

dependencies {
    remapper("org.quiltmc:tiny-remapper:0.4.3")
    decompiler("net.minecraftforge:forgeflower:1.5.498.12")
    paperclip("io.papermc:paperclip:2.0.1")
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(16))
        }
    }
}

subprojects {
    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(16)
    }
    tasks.withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }
    tasks.withType<ProcessResources> {
        filteringCharset = Charsets.UTF_8.name()
    }

    repositories {
        mavenCentral()
        maven("https://libraries.minecraft.net/")
        maven("https://repo.codemc.io/repository/maven-public/")
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
    serverProject.set(project(":DeepslateMC-Server"))

    remapRepo.set("https://maven.quiltmc.org/repository/release/")
    decompileRepo.set("https://files.minecraftforge.net/maven/")

    useStandardUpstream("Sugarcane") {
        url.set(github("SugarcaneMC", "Sugarcane"))
        ref.set(providers.gradleProperty("sugarcaneRef"))

        withStandardPatcher {
            baseName("Sugarcane")

            apiOutputDir.set(layout.projectDirectory.dir("DeepslateMC-API"))
            serverOutputDir.set(layout.projectDirectory.dir("DeepslateMC-Server"))

            remapRepo.set("https://maven.quiltmc.org/repository/release/")
            decompileRepo.set("https://files.minecraftforge.net/maven/")
        }

        reobfPackagesToFix.addAll(
            "net.pascalpex",
            "org.sugarcanemc",
            "gg.airplane",
            "net.pl3x",
            "com.tuinity",
            "ca.spottedleaf",
            "me.jellysquid.mods"
        )
    }
}

//
// Everything below here is optional if you don't care about publishing API or dev bundles to your repository
//

tasks.generateDevelopmentBundle {
    apiCoordinates.set("com.example.paperfork:forktest-api")
    mojangApiCoordinates.set("io.papermc.paper:paper-mojangapi")
    libraryRepositories.set(
        listOf(
            "https://libraries.minecraft.net/",
            "https://maven.quiltmc.org/repository/release/",
            "https://repo.aikar.co/content/groups/aikar",
            "https://ci.emc.gs/nexus/content/groups/aikar/",
            "https://papermc.io/repo/repository/maven-public/", // for paper-mojangapi

            // "https://my.repo/" // This should be a repo hosting your API (in this example, 'com.example.paperfork:forktest-api')
        )
    )
}

allprojects {
    // Publishing API:
    // ./gradlew :ForkTest-API:publish[ToMavenLocal]
    publishing {
        repositories {
            maven {
                name = "myRepoSnapshots"
                url = uri("https://my.repo/")
                // See Gradle docs for how to provide credentials to PasswordCredentials
                // https://docs.gradle.org/current/samples/sample_publishing_credentials.html
                credentials(PasswordCredentials::class)
            }
        }
    }
}

publishing {
    // Publishing dev bundle:
    // ./gradlew publishDevBundlePublicationTo(MavenLocal|MyRepoSnapshotsRepository) -PpublishDevBundle
    if (project.hasProperty("publishDevBundle")) {
        publications.create<MavenPublication>("devBundle") {
            artifact(tasks.generateDevelopmentBundle) {
                artifactId = "dev-bundle"
            }
        }
    }
}
