plugins {
    id("java")
    id("application")
    id("io.ktor.plugin") version "2.3.12"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.16.2")
    implementation("org.camunda.bpm:camunda-engine:7.22.0")
    implementation("org.reflections:reflections:0.10.2")
}

application {
    mainClass.set("org.example.Main")
}

ktor {
    fatJar {
        archiveFileName.set(rootProject.name + ".jar")
    }
}
tasks.test {
    useJUnitPlatform()
}