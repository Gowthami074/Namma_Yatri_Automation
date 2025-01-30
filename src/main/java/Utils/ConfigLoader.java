package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties properties;

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            String configFilePath = System.getProperty("user.dir") + "/app.config.properties";
            FileReader fr = new FileReader(configFilePath);
            properties = new Properties();
            properties.load(fr);
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
