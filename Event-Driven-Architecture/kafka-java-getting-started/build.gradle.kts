plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.0.0")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.apache.kafka:kafka-clients:3.2.3")
}

tasks.test {
    useJUnitPlatform()
}