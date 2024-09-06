plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "me.essejacques"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // https://mvnrepository.com/artifact/com.intuit.karate/karate-junit5
    testImplementation("com.intuit.karate:karate-junit5:1.4.1")
}

sourceSets {
    test {
        resources {
            srcDir (file("src/test/java"))
            exclude("**/*.java")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
