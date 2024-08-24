package pl.roquv.passwordgeneratorjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/main-view.fxml"));
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