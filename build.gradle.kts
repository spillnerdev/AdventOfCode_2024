plugins {
    `java-library`
    kotlin("jvm") version "2.0.20"
}

group = "dev.spillner"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    dependencies {
        if (project.name != "common") {
            implementation(project(":common"))
        }
    }
}


allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    dependencies {
        testImplementation(kotlin("test"))
    }
}

tasks.test {
    useJUnitPlatform()
}
