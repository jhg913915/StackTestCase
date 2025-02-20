plugins {
    id("java")
    application
    checkstyle
}

group = "stack.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-csv:2.17.2")
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    implementation("commons-io:commons-io:2.6")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "stack.code.App"
}