package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SaveWindowController {
    @FXML
    ListView<String> passwordListView;

    @FXML
    private Button closeButton;

    @FXML
    private Button clearButton;

    private static final List<String> savedPasswords = new ArrayList<>();

    @FXML
    public void initialize() {
        passwordListView.getItems().setAll(savedPasswords);
    }

    @FXML
    public void handleCloseButtonClick() {
       closeWindow();
    }

    @FXML
    public void handleClearButtonClick() {
        passwordListView.getItems().clear();
        closeWindow();
    }

    public static void addPassword(String password) {
        savedPasswords.add(password);
    }

    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
