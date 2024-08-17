package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import pl.roquv.passwordgeneratorjavafx.App;
import pl.roquv.passwordgeneratorjavafx.PasswordGenerator;

public class AppWindowController {
    @FXML
    private TextField passwordLabel;

    @FXML
    private TextField passwordLengthLabel;

    @FXML
    private CheckBox checkBoxLowercase;

    @FXML
    private CheckBox checkBoxUppercase;

    @FXML
    private CheckBox checkBoxNumbers;

    @FXML
    private CheckBox checkBoxSpecialSymbols;

    private final PasswordGenerator passwordGenerator = new PasswordGenerator();

    public void handleGenerateButtonClick() {

        int passwordLength = getPasswordLength();

        if (isPasswordLengthInRange(4, 100)) {
            boolean checkBoxLowercaseSelected = checkBoxLowercase.isSelected();
            boolean checkBoxUppercaseSelected = checkBoxUppercase.isSelected();
            boolean checkBoxNumbersSelected = checkBoxNumbers.isSelected();
            boolean checkBoxSpecialSymbolsSelected = checkBoxSpecialSymbols.isSelected();

            if (!isAnyCheckboxSelected()) {
                showErrorMessage("One or more checkbox must be selected");
            } else {
                String generatedPassword = passwordGenerator.generatePassword(passwordLength, checkBoxLowercaseSelected,
                        checkBoxUppercaseSelected, checkBoxNumbersSelected, checkBoxSpecialSymbolsSelected);

                updatePasswordLabel(generatedPassword);
            }
        } else {
            showErrorMessage("Password length must be between 4 and 100");
        }
    }

    public void handleCopyToClipboardButtonClick() {
        if (isPasswordLabelEmpty()) {
            showErrorMessage("Password is empty");
        } else {
            String password = passwordLabel.getText();

            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(password);
            clipboard.setContent(content);
        }

    }

    public void handleSaveButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("save.fxml"));
            Parent root = fxmlLoader.load();

            Stage saveStage = new Stage();
            saveStage.setTitle("Saving Password");

            Scene scene = new Scene(root);
            saveStage.setScene(scene);

            String iconPath = getClass().getResource("icons/help-icon.png").toExternalForm();
           saveStage.getIcons().add(new Image(iconPath));

            saveStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Could not open help window");
        }
    }

    public void handleSettingsButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("settings.fxml"));
            Parent root = fxmlLoader.load();

            Stage settingsStage = new Stage();
            settingsStage.setTitle("Settings");

            Scene scene = new Scene(root);
            settingsStage.setScene(scene);

            String iconPath = getClass().getResource("icons/settings-icon.png").toExternalForm();
            settingsStage.getIcons().add(new Image(iconPath));

            settingsStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Could not open help window");
        }
    }

    public void handleHelpButtonClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("help.fxml"));
            Parent root = fxmlLoader.load();

            Stage helpStage = new Stage();
            helpStage.setTitle("Help");

            Scene scene = new Scene(root);
            helpStage.setScene(scene);

            String iconPath = getClass().getResource("icons/help-icon.png").toExternalForm();
            helpStage.getIcons().add(new Image(iconPath));

            helpStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorMessage("Could not open help window");
        }
    }

    private int getPasswordLength() {
        try {
            return Integer.parseInt(passwordLengthLabel.getText());
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid password length");
            return 0;
        }
    }

    private boolean isPasswordLengthInRange(int min, int max){
        int passwordLength = getPasswordLength();
        return  ((passwordLength >= min) && (passwordLength <= max));
    }

    private boolean isAnyCheckboxSelected() {
        return checkBoxLowercase.isSelected() || checkBoxUppercase.isSelected() ||
                checkBoxNumbers.isSelected() || checkBoxSpecialSymbols.isSelected();
    }

    private boolean isPasswordLabelEmpty() {
        return passwordLabel.getText().isEmpty();
    }

    private void updatePasswordLabel(String password) {
        passwordLabel.setText(password);
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}