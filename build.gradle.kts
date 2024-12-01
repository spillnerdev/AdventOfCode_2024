plugins {
    `java-library`
    kotlin("jvm") version "2.0.20"
}

group = "dev.spillner"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    dependencies {
        implementation(rootProject.libs.bundles.ktor)
    }
}

tasks.test {
    useJUnitPlatform()
}
