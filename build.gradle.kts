plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "org.zapto.jiansoft"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation ("com.fasterxml.jackson.core:jackson-core")
	implementation ("com.fasterxml.jackson.core:jackson-databind")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.telegram:telegrambots-longpolling:7.2.1")
	implementation ("org.telegram:telegrambots-client:7.2.1")
	implementation("io.github.cdimascio:dotenv-java:3.0.0")
	implementation("org.slf4j:slf4j-api:2.0.13")
	implementation("ch.qos.logback:logback-core")
	implementation ("ch.qos.logback:logback-classic")
	compileOnly ("org.projectlombok:lombok:1.18.32")
	annotationProcessor ("org.projectlombok:lombok:1.18.32")


	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
