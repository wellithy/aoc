plugins {
    kotlin("jvm") version "+"
    kotlin("plugin.serialization") version "+"
}
repositories {
    mavenCentral()
}
dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:+")
    testImplementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
}
