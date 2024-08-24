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

    @Override
    public void start(Stage stage) throws IOException {

        Locale language;

        if (ConfigManager.getProperty("app.language").equals("pl")) {
            language = Locale.of("pl");
        } else {
            language = Locale.of("en");
        }

        // Load the resource bundle with UTF-8 encoding
        String bundlePath = "/pl/roquv/passwordgeneratorjavafx/language_" + language.getLanguage() + ".properties";
        ResourceBundle bundle;
        try (InputStreamReader reader = new InputStreamReader(
                App.class.getResourceAsStream(bundlePath), StandardCharsets.UTF_8)) {
            bundle = new PropertyResourceBundle(reader);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/main-view.fxml"), bundle);
        Scene scene = new Scene(fxmlLoader.load(), 460, 445);
        stage.setTitle("PasswordGenerator");
        stage.setScene(scene);

        String iconPath = getClass().getResource("icons/main-icon.png").toExternalForm();
        stage.getIcons().add(new Image(iconPath));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}