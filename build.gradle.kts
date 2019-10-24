import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group = "id.owl_lang"
version = "10"

plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version "5.1.0"
    id("io.freefair.lombok") version "4.1.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}

dependencies {
    implementation("com.google.guava:guava:28.0-jre")
    implementation("com.beust:jcommander:1.78")
    implementation("org.slf4j:slf4j-api:1.7.25")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.25")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

application {
    mainClassName = "id.owl_lang.App"
}

// shadowJar {
//     exclude("about.html")
//     exclude("META-INF/LICENSE")
//     exclude("META-INF/LICENSE.txt")
//     exclude("META-INF/NOTICE")
// }

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(mapOf("Main-Class" to "com.github.csolem.gradle.shadow.kotlin.example.App"))
        }
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
