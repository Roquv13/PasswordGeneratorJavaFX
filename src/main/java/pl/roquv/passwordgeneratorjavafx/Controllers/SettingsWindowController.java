package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

public class SettingsWindowController {
    @FXML
    private ChoiceBox<String> choiceBoxLanguage;

    @FXML
    private void initialize() {
        initializeChoiceBoxLanguage();
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
