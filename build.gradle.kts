plugins {
    id("org.springframework.boot") version "2.7.6"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("org.jlleitschuh.gradle.ktlint-idea") version "10.2.1"
    id("project-report")
    id("org.sonarqube") version "3.5.0.2730"
    `java-test-fixtures`
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.4.32" // JPA를 사용하기 위한 플러그인
    kotlin("kapt") version "1.7.10"
    id("java-test-fixtures")
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

val querydslVersion = "5.0.0"
extra["springCloudVersion"] = "2021.0.3"

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

group = "com.junyoung"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    implementation("io.github.resilience4j:resilience4j-spring-boot2")
    implementation("io.github.resilience4j:resilience4j-circuitbreaker")
    implementation("io.github.resilience4j:resilience4j-timelimiter")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    implementation("org.apache.httpcomponents.client5:httpclient5:5.1.3")
    implementation("org.apache.httpcomponents.client5:httpclient5-fluent:5.1.3")
    implementation("io.github.microutils:kotlin-logging:2.1.23")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:5.3.0")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testRuntimeOnly("com.h2database:h2")
    testImplementation("p6spy:p6spy:3.9.1")
    testImplementation("com.github.tomakehurst:wiremock:2.27.2")
    testFixturesImplementation("io.github.microutils:kotlin-logging:1.5.9")
    testFixturesImplementation("com.github.tomakehurst:wiremock:2.27.2")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Test>("unitTest") {
    useJUnitPlatform {
        includeTags("unit")
    }
}

tasks.register<Test>("integrationTest") {
    useJUnitPlatform {
        includeTags("integration")
    }
}

sonarqube {
    properties {
        property("sonar.host.url", "http://localhost:9000")
        property("sonar.login", "squ_3ed81373818636e08a63588bfb028d41f7f4ec1a")
        property("sonar.language", "kotlin")
        property("sonar.sources", "src/main/kotlin")
        property("sonar.sourceEncoding", "UTF-8")
    }
}
