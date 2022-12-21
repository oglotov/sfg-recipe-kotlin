import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.10"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("plugin.jpa") version "1.7.21"
    jacoco
}

group = "ua.wwind.glotov"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

sourceSets {
    create("intTest") {
        compileClasspath += sourceSets.main.get().output
        runtimeClasspath += sourceSets.main.get().output
    }
}

val intTestImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}
configurations["intTestImplementation"].extendsFrom(configurations.testImplementation.get())
configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {

    //
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Validator
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // WebJars
    compileOnly("org.webjars.npm:bootstrap:5.2.3")
    compileOnly("org.webjars.npm:jquery:3.6.1")
    
    // Thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("com.ninja-squad:springmockk:3.1.2")

    // Integration tests https://docs.gradle.org/current/userguide/java_testing.html#sec:configuring_java_integration_tests
    intTestImplementation("org.springframework.boot:spring-boot-starter-test")
    intTestImplementation("org.springframework.boot:spring-boot-starter-webflux")
}

allOpen {
    annotation("javax.persistence.Entity")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

jacoco {
    toolVersion = "0.8.8"
    // reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }

        rule {
            isEnabled = false
            element = "CLASS"
            includes = listOf("ua.wwind.*")

            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "0.3".toBigDecimal()
            }
        }
    }
}

val integrationTest = task<Test>("integrationTest") {
    description = "Runs integration tests."
    group = "verification"

    testClassesDirs = sourceSets["intTest"].output.classesDirs
    classpath = sourceSets["intTest"].runtimeClasspath
    shouldRunAfter("test")

    useJUnitPlatform()

    testLogging {
        events("passed")
    }
}

tasks.check { dependsOn(integrationTest) }