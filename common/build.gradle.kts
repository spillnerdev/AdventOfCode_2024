group = "dev.spillner"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(rootProject.libs.bundles.ktor)
}

tasks.test {
    useJUnitPlatform()
}
