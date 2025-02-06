package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WindowController {
    public void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
