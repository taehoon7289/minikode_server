import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "2.7.6"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.7.21"
    kotlin("plugin.spring") version "1.7.21"
    kotlin("plugin.jpa") version "1.7.21"
    kotlin("kapt") version "1.7.21"
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "com.minikode"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }
}

subprojects {

    apply(plugin = "java")

    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring") //all-open
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-kapt")

    dependencies {
        //spring boot
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        //kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        // log
        implementation("io.github.microutils:kotlin-logging:3.0.4")
        // querydsl
        implementation("com.querydsl:querydsl-jpa:5.0.0")
        kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

    }

    extra["springCloudVersion"] = "2021.0.5"

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
//            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
        all {
            resolutionStrategy {
                // don't cache changing modules at all

                cacheDynamicVersionsFor(0, "seconds")
                cacheChangingModulesFor(0, "seconds")
            }
        }
    }


}

project(":api_common") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-aop")
//        implementation("org.springframework.boot:spring-boot-starter-security")
//        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        implementation("org.springframework.cloud:spring-cloud-starter-config")
        implementation("org.springframework.cloud:spring-cloud-config-client")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        // h2
        runtimeOnly("com.h2database:h2")

        // jpa 의존
        implementation(project(":jpa"))

//        testImplementation("org.springframework.boot:spring-boot-starter-test") {
//            exclude("org.springframework.boot:spring-boot-starter-test")
//        }


    }

}

project(":cloud_config") {

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-config-server")
    }
}

//jpa 설정
project(":jpa") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    }
    // bootJar 비활성
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    // jar 활성
    val jar: Jar by tasks
    jar.enabled = true

}