/*
 * This file was generated by the Gradle "init" task.
 */

plugins {
    id("java")
    id("maven-publish")
    id("me.bristermitten.pdm") version "0.0.30" //Replace with the latest version
    `java-library`
    `maven-publish`
    signing
}
java {
    withJavadocJar()
    withSourcesJar()
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_11
    sourceCompatibility = org.gradle.api.JavaVersion.VERSION_11

}
repositories {
    mavenLocal()
    maven("https://papermc.io/repo/repository/maven-public/")

    maven("https://oss.sonatype.org/content/groups/public/")

    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://jcenter.bintray.com")


    maven("https://repo.kingtux.me/storages/maven/kingtux-repo")

    maven("https://repo.kingtux.me/storages/maven/tuxjsql")


    maven("http://repo.extendedclip.com/content/repositories/placeholderapi/")


    maven("https://repo.maven.apache.org/maven2")

}

dependencies {
    pdm("me.kingtux:tuxorm:1.5-SNAPSHOT")
    pdm("net.dv8tion:JDA:4.1.1_108")
    pdm("dev.nitrocommand:core:1.0-SNAPSHOT")
    pdm("dev.nitrocommand:jda4:1.0-SNAPSHOT")
    pdm("dev.nitrocommand:bukkit:1.0-SNAPSHOT")
    pdm("me.kingtux:tuxjsql:2.2.0-SNAPSHOT")
    pdm("me.kingtux.tuxjsql:sqlite:2.2.0-SNAPSHOT")
    pdm("com.google.guava:guava:29.0-jre")
    pdm("org.slf4j:slf4j-log4j12:1.7.5")
    pdm("log4j:apache-log4j-extras:1.2.17")
    pdm("com.jcabi:jcabi-log:0.17.3")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.9.2")
}

group = "me.kingtux"
version = "1.0-SNAPSHOT"
description = "TuxCore"
val artifactName = "tuxcore"
if (hasProperty("buildNumber")) {
    version = "1.0-"+properties.get("buildNumber")+"-SNAPSHOT"
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            artifactId = artifactName
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(artifactName)
            }
        }
    }
    repositories {
        maven {

            val releasesRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            val snapshotsRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials(PasswordCredentials::class)

        }
        mavenLocal()
    }
}
tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
tasks {
    "jar"{
        dependsOn(project.tasks.getByName("pdm"));
    }
}