import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group = "id.owl_lang"
version = "10"

plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.beust:jcommander:1.78")
    implementation("org.slf4j:slf4j-api:1.7.25")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.25")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

application {
    mainClassName = "id.owl_lang.App"
}

tasks.withType<ShadowJar>() {
    // TODO: Exclude unused files
    manifest {
        attributes["Main-Class"] = "id.owl_lang.App"
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}
