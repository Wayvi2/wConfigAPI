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
```
## 📖 Usage

### 1️⃣ Create your config keys enum
```java
public enum ConfigKeys implements ConfigKey<?> {
    PREFIX("prefix", "&7[&aTestPlugin&7]"),
    MAX_PLAYERS("max-players", 100),
    MOTD("motd", "Welcome to the server!");

    private final String path;
    private final Object defaultValue;

    ConfigKeys(String path, Object defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }
}
