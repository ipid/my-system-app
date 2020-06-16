plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    application

    id("com.github.johnrengelman.shadow") version "6.0.0"
}

repositories {
    jcenter()
}

dependencies {
    compileOnly(fileTree(mapOf("dir" to "compile-lib", "include" to listOf("*.jar"))))

    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

application {
    mainClassName = "me.ipid.android.mysystemapp.Daemon"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_6
    targetCompatibility = JavaVersion.VERSION_1_6
}

tasks {

    compileKotlin {
        kotlinOptions.jvmTarget = "1.6"
    }

    shadowJar {
        archiveFileName.set("my-system-app.jar")
    }

    BuildSrcUtils.registerTasks(project, "my-system-app")
}