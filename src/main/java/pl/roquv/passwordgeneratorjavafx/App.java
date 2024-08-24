package pl.roquv.passwordgeneratorjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class App extends Application {
    
    private static final String FXML_FILE_PATH = "views/main-view.fxml";
    private static final String ICON_FILE_PATH = "icons/main-icon.png";

    @Override
    public void start(Stage stage) throws IOException {
        try {
            Locale locale = determinateLocale();
            ResourceBundle bundle = loadResourceBundle(locale);

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(FXML_FILE_PATH), bundle);
            Scene scene = new Scene(fxmlLoader.load(), 460, 445);

            configureStage(stage, bundle, scene);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private ResourceBundle loadResourceBundle(Locale locale) throws IOException {
        // Load the resource bundle
        String bundlePath = String.format("/pl/roquv/passwordgeneratorjavafx/language_%s.properties", locale.getLanguage());
        try (InputStreamReader reader = new InputStreamReader(
                getClass().getResourceAsStream(bundlePath), StandardCharsets.UTF_8)) {
            return new PropertyResourceBundle(reader);
        }
    }

    private static Locale determinateLocale() {
        String language = ConfigManager.getProperty("app.language");
        switch (language) {
            case "pl":
                return Locale.of("pl");
            default:
                return Locale.of("en");
        }
    }

    private void configureStage(Stage stage, ResourceBundle bundle, Scene scene) {
        stage.setTitle(bundle.getString("window.title"));
        stage.setScene(scene);

        String iconPath = getClass().getResource(ICON_FILE_PATH).toExternalForm();
        stage.getIcons().add(new Image(iconPath));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}