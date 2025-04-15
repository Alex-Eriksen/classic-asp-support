plugins {
  id("org.jetbrains.intellij.platform") version "2.4.0"
  id("org.jetbrains.kotlin.jvm") version "2.1.20"
}

group = "dk.ave.classic_asp_support"
version = "0.0.1"

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaUltimate("2024.3")
  }
}

sourceSets {
  main {
    java {
      srcDirs("src/main/gen")
    }
  }
}

intellijPlatform {
  pluginConfiguration {
    id = "classic-asp-support"
    name = "Classic ASP Support"
    version = "0.0.1"
    description = "Adds Classic ASP syntax highlighting to WebStorm"
    ideaVersion {
      sinceBuild = "243"
      untilBuild = provider { null }
    }
    vendor {
      name = "Alexander V. Eriksen"
      email = "alexanderv.eriksen@gmail.com"
      url = "https://github.com/alex-eriksen"
    }
  }
  publishing {
    token = System.getenv("PUBLISH_TOKEN")
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
//intellij {
//  pluginName.set("webstorm-classic-asp-support")
//
//  version.set("IU-243.24978.46")
//
//  updateSinceUntilBuild.set(false)
//
//  plugins.set(
//    listOf(
//      "java",
//      "JavaScript",
//      "com.intellij.css",
//      "properties",
//      "yaml"
//    )
//  )
//}

tasks {
  runIde {
    systemProperties["idea.auto.reload.plugins"] = false
    jvmArgs = listOf(
      "-Xms512m",
      "-Xmx2048m",
    )

    args = listOf("C:\\Users\\Alexander\\IdeaProjects\\testproject")
  }

  jar {
    archiveBaseName.set("classic-asp-support")
  }
}
