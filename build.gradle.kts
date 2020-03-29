import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.lang.System

plugins {
    application
    kotlin("jvm") version "1.3.71"
}

group = "de.wirvsvirus.testresults"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("io.rest-assured:rest-assured:4.3.0")
    testImplementation("io.rest-assured:kotlin-extensions:4.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.3.71")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.1")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val test: Test by tasks
test.useJUnitPlatform()
// pass system properties to test
listOf("httpPort", "httpHost", "postUser", "postUserPass", "adminUser", "adminUserPass")
    .filter {System.getProperties().containsKey(it) }
    .forEach { test.systemProperties.put(it, System.getProperty(it)) }
