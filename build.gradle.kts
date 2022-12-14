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
    version = "0.0.1-SNAPSHOT"

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
//        implementation("io.github.microutils:kotlin-logging:3.0.4")
        // querydsl
        implementation("com.querydsl:querydsl-jpa:5.0.0")
        kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

    }

    extra["springCloudVersion"] = "2021.0.5"

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
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
        implementation("org.springframework.boot:spring-boot-starter-web")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        implementation("org.springframework.cloud:spring-cloud-starter-config")
        implementation("org.springframework.cloud:spring-cloud-config-client")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-security")
        testImplementation("org.springframework.security:spring-security-test")
        implementation("io.jsonwebtoken:jjwt:0.9.1")
        // h2
        implementation("com.h2database:h2")
        runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

        // jpa ??????
        implementation(project(":jpa"))
        // common ??????
        implementation(project(":common"))

    }

}

project(":cloud_config") {

    dependencies {
        implementation("org.springframework.cloud:spring-cloud-config-server")
    }
}

//jpa ??????
project(":jpa") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    }
    // bootJar ?????????
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    // jar ??????
    val jar: Jar by tasks
    jar.enabled = true

}

//common ??????
project(":common") {
    dependencies {
//        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-security")
        testImplementation("org.springframework.security:spring-security-test")
        implementation("org.springframework.boot:spring-boot-starter-aop")
    }
    // bootJar ?????????
    val bootJar: BootJar by tasks
    bootJar.enabled = false
    // jar ??????
    val jar: Jar by tasks
    jar.enabled = true

}

val bootJar: BootJar by tasks
bootJar.enabled = false
//
//val jar: Jar by tasks
//jar.enabled = false