# wConfigAPI

wConfigAPI is a lightweight Java API for easily managing YAML configuration files in Spigot/Bukkit plugins, with automatic handling of missing keys via enums.

## 📦 Installation

### Gradle
```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Wayvi2:wConfigAPI:1.0.0'
}
```
### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Wayvi2</groupId>
        <artifactId>wConfigAPI</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
