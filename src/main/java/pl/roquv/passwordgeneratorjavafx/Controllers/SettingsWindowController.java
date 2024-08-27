package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.roquv.passwordgeneratorjavafx.ConfigManager;

public class SettingsWindowController {
    @FXML
    private ChoiceBox<String> choiceBoxLanguage;

    @FXML
    public Label appVersion;

    @FXML
    public TextField minPasswordLengthText;

    @FXML
    public TextField maxPasswordLengthText;

    @FXML
    private void initialize() {
        initializeChoiceBoxLanguage();
        appVersion.setText("App Version: " + ConfigManager.getProperty("app.version"));

        // Display values from properties
        minPasswordLengthText.setText(ConfigManager.getProperty("password.length.min"));
        maxPasswordLengthText.setText(ConfigManager.getProperty("password.length.max"));
    }



    private void initializeChoiceBoxLanguage() {
        choiceBoxLanguage.getItems().addAll("English", "Polish");

        choiceBoxLanguage.setValue("English");

        choiceBoxLanguage.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleChoiceBoxSelection(newValue);
        });
    }

    private void handleChoiceBoxSelection(String selectedItem) {
        if (selectedItem.equals("English")) {
            System.out.println("Selected Language: " + choiceBoxLanguage.getValue());
        } else if (selectedItem.equals("Polish")) {
            System.out.println("Selected Language: " + choiceBoxLanguage.getValue());
        }
    }


    public void handleSaveButtonClick(ActionEvent event) {
        String minPasswordLength = minPasswordLengthText.getText();
        String maxPasswordLength = maxPasswordLengthText.getText();
    }
}
