package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import pl.roquv.passwordgeneratorjavafx.ConfigManager;

public class SettingsWindowController {
    @FXML
    private ChoiceBox<String> choiceBoxLanguage;

    @FXML
    public Label appVersion;

    @FXML
    private void initialize() {
        initializeChoiceBoxLanguage();
        appVersion.setText("App Version: " + ConfigManager.getProperty("app.version"));
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
}
