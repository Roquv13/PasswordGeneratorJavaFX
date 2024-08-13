package pl.roquv.passwordgeneratorjavafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 400);
        stage.setTitle("PasswordGenerator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}