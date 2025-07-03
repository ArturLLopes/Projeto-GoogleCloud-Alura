plugins {
	id("org.springframework.boot") version "3.2.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
	kotlin("plugin.jpa") version "1.9.20"
}

group = "com.projeto.alura"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

// Configurações de ambiente
val profile = project.findProperty("profile") as String? ?: "dev"

configurations {
	create("devDependencies")
	create("prodDependencies")
	create("testDependencies")
}

dependencies {
	// Spring Boot Starters (equivale ao FastAPI)
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation") // equivale ao email_validator
	implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

	// Database Drivers - Base (equivale ao SQLAlchemy)
	implementation("org.xerial:sqlite-jdbc:3.46.0.0")

	// Hibernate Community Dialects
	implementation("org.hibernate.orm:hibernate-community-dialects:6.4.4.Final")

	// JSON Processing (equivale ao pydantic)
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

	// Kotlin Support (equivale ao typing_extensions)
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Validation (equivale ao pydantic validation)
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.hibernate.validator:hibernate-validator")

	// Async Support (equivale ao anyio)
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

	// Email Validation (equivale ao email_validator)
	implementation("org.apache.commons:commons-email:1.5")
	implementation("javax.validation:validation-api:2.0.1.Final")

	// Lombok (mantido do seu código original)
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	testCompileOnly("org.projectlombok:lombok:1.18.30")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

	// Dependências específicas por ambiente
	when (profile) {
		"dev" -> {
			implementation("org.springframework.boot:spring-boot-devtools")
			implementation("com.h2database:h2")
			runtimeOnly("org.springframework.boot:spring-boot-starter-actuator")
		}
		"test" -> {
			implementation("com.h2database:h2")
			testImplementation("org.testcontainers:testcontainers")
			testImplementation("org.testcontainers:junit-jupiter")
		}
		"prod" -> {
			// Para produção, você pode adicionar dependências específicas
			// como drivers de banco específicos, monitoramento, etc.
			runtimeOnly("org.postgresql:postgresql") // exemplo
		}
	}

	// Test Dependencies - sempre presentes
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Tasks personalizadas para cada ambiente
tasks.register("runDev") {
	group = "application"
	description = "Executa a aplicação em modo desenvolvimento"
	dependsOn("bootRun")
	doFirst {
		System.setProperty("spring.profiles.active", "dev")
	}
}

tasks.register("runProd") {
	group = "application"
	description = "Executa a aplicação em modo produção"
	dependsOn("bootRun")
	doFirst {
		System.setProperty("spring.profiles.active", "prod")
	}
}

tasks.register("buildDev") {
	group = "build"
	description = "Build para desenvolvimento"
	dependsOn("build")
	doFirst {
		project.setProperty("profile", "dev")
	}
}

tasks.register("buildProd") {
	group = "build"
	description = "Build para produção"
	dependsOn("build")
	doFirst {
		project.setProperty("profile", "prod")
	}
}

// Configuração do bootRun para usar profiles
tasks.bootRun {
	val activeProfile = System.getProperty("spring.profiles.active") ?: "dev"
	systemProperty("spring.profiles.active", activeProfile)

	// Argumentos JVM específicos por ambiente
	when (activeProfile) {
		"dev" -> {
			jvmArgs = listOf("-Xms256m", "-Xmx512m", "-Dspring.devtools.restart.enabled=true")
		}
		"prod" -> {
			jvmArgs = listOf("-Xms512m", "-Xmx1024m", "-Dspring.jpa.show-sql=false")
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	systemProperty("spring.profiles.active", "test")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}