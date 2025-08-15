

# wConfigAPI

[![](https://jitpack.io/v/Wayvi2/wConfigAPI.svg)](https://jitpack.io/#Wayvi2/wConfigAPI)

wConfigAPI is a lightweight Java API for easily managing YAML configuration files in Spigot/Bukkit plugins, with automatic handling of missing keys via enums.

## 📦 Installation

### Gradle
```gradle
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Wayvi2:wConfigAPI:1.0.1'
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
        <version>1.0.1</version>
    </dependency>
</dependencies>
```
## 📖 Usage

### 1️⃣ Create your config keys enum
```java
public enum ConfigEnum implements ConfigKey<Object> {
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
```
## 2️⃣ Initialize ConfigAPI in your plugin

```java
public class MyPlugin extends JavaPlugin {

    private ConfigAPI<ConfigEnum> configFile;

    @Override
    public void onEnable() {
        configFile = new ConfigAPI<>(plugin, ConfigEnum.class, "config.yml");
    }

    public ConfigAPI<ConfigEnum> getConfigFile() {
        return configFile;
    }
}
```
## 3️⃣ Get and set values

```java
// Get a value
String prefix = plugin.getConfigFile().get(ConfigEnum.MAX_PLAYRRS);

// Set a value
plugin.getConfigFile().set(ConfigEnum.MAX_PLAYERS, 150);
```
## 4️⃣ Reload the configuration

```java
plugin.getConfigFile().reload();
```
## 5️⃣ Don't forget to create your config file

Make sure to create the configuration file in your plugin's `resources` folder with the correct name and path.  
For example, if you use `"config.yml"` in your `ConfigAPI` constructor, it should be located at: `src/main/resources/config.yml`

