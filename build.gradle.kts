    plugins {
    id("fabric-loom") version "1.6.3"
    id("maven-publish")
    id("com.gradleup.shadow") version "9.0.0-beta4"
    id("idea")
}

base {
    archivesName = properties["archives_base_name"] as String
    group = properties["maven_group"] as String

    val suffix = if (project.hasProperty("build_number")) {
        project.findProperty("build_number")
    } else {
        "local"
    }

    version = properties["mod_version"] as String + "-" + suffix
}

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
    
    maven {
        url = uri("https://maven.aliyun.com/repository/public/")
    }
    maven {
        url = uri("https://maven.fabricmc.net/")
    }
	mavenCentral()
}

loom {
    accessWidenerPath = file("src/main/resources/cmua.accesswidener")
}

dependencies {
    // Fabric
    minecraft("com.mojang:minecraft:${properties["minecraft_version"] as String}")
    mappings("net.fabricmc:yarn:${properties["yarn_mappings"] as String}:v2")
    modImplementation("net.fabricmc:fabric-loader:${properties["loader_version"] as String}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${properties["fabric_version"] as String}")

    // Imgui
    modImplementation("io.github.spair:imgui-java-app:${properties["imgui_version"] as String}")
    include("io.github.spair:imgui-java-app:${properties["imgui_version"] as String}")

    // Cmua api
    modImplementation(files("lib/cmua-api-${properties["cmua_api_version"] as String}.jar"))
    include(files("lib/cmua-api-${properties["cmua_api_version"] as String}.jar"))
}

tasks {
    processResources {
        // 设置重复的策略：排除
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        // 包含版本
        inputs.property("version", project.version)

        // 其他要处理的资源
    }

    java {
        // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
        // if it is present.
        // If you remove this line, sources will not be generated.
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    jar {
        val licenseSuffix = project.base.archivesName.get()
        from("LICENSE") {
            rename { "${it}_${licenseSuffix}" }
        }
    }
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "NewCat"

            version = properties["minecraft_version"] as String + "-SNAPSHOT"
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}