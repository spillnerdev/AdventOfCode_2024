group = "dev.spillner"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
}

tasks.test {
    useJUnitPlatform()
}
