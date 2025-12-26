# What is Composer?

Composer is a library mod that I made for minecraft for my mods to use.
You are free to use it in any project, and are free to use any of it's code as an example for your own mods.

---

# Why is it reloaded?

I made the original version of Composer a long time ago, when I was a lot worse at programming.
It was set up in weird ways and never worked properly - so I remade it from scratch with actually good code, which is
this mod.

---

# Installation

You can download Composer from [Modrinth](https://modrinth.com/mod/composer-reloaded).  
If none of your mods require it, you don’t need to install it - it doesn’t add any gameplay content on its own, only
functionality for other mods.

> [!WARNING]
> If you do use Composer in your own mod, or want to include it anyway, you must also install any version of **Cardinal
Components** above (or equal to) `5.3.2`.

---

# Development Usage

If you want to use Composer as a library in your mod, add the Composer Maven repository and Cardinal Components
repository to your build file, then add Composer as a dependency.

Replace `(latest_version)` with the latest Composer version compatible with your Minecraft version.

---
<details>
<summary>Gradle (Groovy DSL) - build.gradle</summary>

```properties
# gradle.properties
composer_version = (latest_version)
```

```groovy
// build.gradle

repositories {
    maven {
        name = "Composer Maven"
        url = "https://dl.cloudsmith.io/public/lilbrocodes/composer-reloaded/maven/"
    }
    maven {
        name = "Cardinal Components"
        url = "https://maven.ladysnake.org/releases"
    }
    maven {
        name "FzzyMaven"
        url "https://maven.fzzyhmstrs.me/"
    }
}

dependencies {
    // Before 2.0
    modImplementation "org.lilbrocodes:composer-reloaded:$composer_version"

    // After 2.0
    modImplementation "org.lilbrocodes:composer-reloaded:$composer_version+mc$minecraft_version"
}
```

</details>

<details>
<summary>Gradle (Kotlin DSL) - build.gradle.kts</summary>

```properties
# gradle.properties
composer_version = (latest_version)
```

```groovy
// build.gradle.kts

repositories {
    maven("https://dl.cloudsmith.io/public/lilbrocodes/composer-reloaded/maven/") {
        name = "Composer Maven"
    }
    maven("https://maven.ladysnake.org/releases") {
        name = "Cardinal Components"
    }
    maven("https://maven.fzzyhmstrs.me/") {
        name = "FzzyMaven"
    }
}

dependencies {
    // Before 2.0
    modImplementation("org.lilbrocodes:composer-reloaded:$composer_version")
    
    // After 2.0
    modImplementation("org.lilbrocodes:composer-reloaded:$composer_version+mc$minecraft_version")
}
```

</details>

<details>
<summary>Maven - pom.xml</summary>

```xml
<repositories>
    <repository>
        <id>composer-maven</id>
        <url>https://dl.cloudsmith.io/public/lilbrocodes/composer-reloaded/maven/</url>
    </repository>
    <repository>
        <id>cardinal-components</id>
        <url>https://maven.ladysnake.org/releases</url>
    </repository>
    <repository>
        <id>fzzy-maven</id>
        <url>https://maven.fzzyhmstrs.me/</url>
    </repository>
</repositories>

<dependencies>
    <!-- Before 2.0 -->
    <dependency>
        <groupId>org.lilbrocodes</groupId>
        <artifactId>composer-reloaded</artifactId>
        <version>${composer.version}</version>
        <scope>compile</scope>
    </dependency>
    
    <!-- After 2.0 -->
    <dependency>
        <groupId>org.lilbrocodes</groupId>
        <artifactId>composer-reloaded</artifactId>
        <version>${composer.version}+mc${minecraft.version}</version>
        <scope>compile</scope>
    </dependency>
</dependencies>

```

Add the following property to your `<properties>` section:

```xml
<properties>
    <composer.version>(latest_version)</composer.version>
</properties>
```

</details>
