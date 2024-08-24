package pl.roquv.passwordgeneratorjavafx;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties = new Properties();

    static {
        try(InputStream input = ConfigManager.class.getResourceAsStream("/pl/roquv/passwordgeneratorjavafx/config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            } else {
                System.out.println("Loading config from " + input);
                properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
