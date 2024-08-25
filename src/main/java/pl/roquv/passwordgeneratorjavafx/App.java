package pl.roquv.passwordgeneratorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class App extends Application {

    private static Locale currentLocale = determinateLocale();

    private static final String FXML_FILE_PATH = "views/main-view.fxml";
    private static final String ICON_FILE_PATH = "icons/main-icon.png";

    @Override
    public void start(Stage stage) throws IOException {
        try {
            ResourceBundle bundle = loadResourceBundle(currentLocale);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_FILE_PATH), bundle);
            Scene scene = new Scene(fxmlLoader.load(), 460, 445);

            configureStage(stage, bundle, scene);
        } catch (IOException e) {
            System.err.println("Initialization error. Check FXML file.");
            System.exit(1);
        }

    }

    private void configureStage(Stage stage, ResourceBundle bundle, Scene scene) {
        stage.setTitle(bundle.getString("window.title"));
        stage.setScene(scene);

        // Check that icon path exists
        InputStream iconStream = getClass().getResourceAsStream(ICON_FILE_PATH);
        if (iconStream != null) {
            stage.getIcons().add(new Image(iconStream));
        }  else {
            System.err.println("Couldn't find icon file: " + ICON_FILE_PATH);
        }

        stage.show();
    }

    private static Locale determinateLocale() {
        String language = ConfigManager.getProperty("app.language");
        return switch (language) {
            case "pl" -> Locale.of("pl");
            default -> Locale.of("en");
        };
    }

    private ResourceBundle loadResourceBundle(Locale locale) throws IOException {
        // Load the resource bundle
        String bundlePath = String.format("/pl/roquv/passwordgeneratorjavafx/language_%s.properties", locale.getLanguage());
        InputStream bundleStream = getClass().getResourceAsStream(bundlePath);

        if (bundleStream == null) {
            // Resource not found
            System.err.println("Couldn't find resource bundle: " + bundlePath);
            throw new IOException("Resource bundle not found: " + bundlePath);
        }

        try (InputStreamReader reader = new InputStreamReader(bundleStream, StandardCharsets.UTF_8)) {
            return new PropertyResourceBundle(reader);
        } catch (IOException e) {
            // Error while reading stream
            System.err.println("Error reading resource bundle: " + bundlePath);
            throw e;
        } catch (IllegalArgumentException e) {
            // Resource format is incorrect
            System.err.println("Error parsing resource bundle: " + bundlePath);
            throw new IOException("Error parsing resource bundle: " + bundlePath, e);
        }
    }

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void main(String[] args) {
        launch();
    }
}