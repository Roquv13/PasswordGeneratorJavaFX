package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.converter.EffectConverter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pl.roquv.passwordgeneratorjavafx.Alerts;
import pl.roquv.passwordgeneratorjavafx.App;
import pl.roquv.passwordgeneratorjavafx.PasswordGenerator;

public class AppWindowController {
    @FXML
    private TextField passwordTextField;

    @FXML
    private Text passwordLengthText;

    @FXML
    private Slider passwordLengthSlider;

    @FXML
    private CheckBox checkBoxLowercase;

    @FXML
    private CheckBox checkBoxUppercase;

    @FXML
    private CheckBox checkBoxNumbers;

    @FXML
    private CheckBox checkBoxSpecialSymbols;

    private final PasswordGenerator passwordGenerator = new PasswordGenerator();

    private final SaveWindowController saveWindowController = new SaveWindowController();

    private final Alerts alerts = new Alerts();

    // Minimal and Maximal size of password
    private final int passwordMinLength = 4;
    private final int passwordMaxLength = 100;

    @FXML
    public void initialize() {
        passwordLengthSlider.setMin(passwordMinLength);
        passwordLengthSlider.setMax(passwordMaxLength);

        // Set Initial value of slider to title
        passwordLengthText.setText("Characters: " + (int) passwordLengthSlider.getValue());

        // Set text color and effect
        passwordLengthText.setFill(Color.WHITE);
        passwordLengthText.setStroke(Color.BLACK);


        // Add listener for slider
        passwordLengthSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                updatePasswordLengthTitle(newValue.intValue());
            }
        });
    }

    public void handleGenerateButtonClick() {

        int passwordLength = getPasswordLength();

        if (isPasswordLengthInRange(passwordMinLength, passwordMaxLength)) {
            boolean checkBoxLowercaseSelected = checkBoxLowercase.isSelected();
            boolean checkBoxUppercaseSelected = checkBoxUppercase.isSelected();
            boolean checkBoxNumbersSelected = checkBoxNumbers.isSelected();
            boolean checkBoxSpecialSymbolsSelected = checkBoxSpecialSymbols.isSelected();

            if (!isAnyCheckboxSelected()) {
                alerts.showErrorMessage("One or more checkbox must be selected");
            } else {
                String generatedPassword = passwordGenerator.generatePassword(passwordLength, checkBoxLowercaseSelected,
                        checkBoxUppercaseSelected, checkBoxNumbersSelected, checkBoxSpecialSymbolsSelected);

                updatePasswordLabel(generatedPassword);
            }
        } else {
            alerts.showErrorMessage("Password length must be between " + passwordMinLength + " and " + passwordMaxLength);
        }
    }

    public void handleCopyToClipboardButtonClick() {
        if (isPasswordLabelEmpty()) {
            alerts.showErrorMessage("Password is empty");
        } else {
            String password = passwordTextField.getText();

            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(password);
            clipboard.setContent(content);
        }

    }

    public void handleSavePasswordButtonClick() {
        String password = getPassword();

        if (isPasswordLabelEmpty()) {
            alerts.showErrorMessage("Password is empty");
        } else {
            // Save password to list
            SaveWindowController.addPassword(password);
            // Show alert about saving password
            alerts.showInfoMessage("Password has been saved");
        }
    }

    public void handleOpenPasswordsWindowButtonClick() {
        // Open password list window
        openNewWindow("Save Password", "save.fxml", "icons/main-icon.png");
    }

    public void handleSettingsButtonClick() {
        openNewWindow("Settings", "settings.fxml", "icons/settings-icon.png");
    }

    public void handleHelpButtonClick() {
        openNewWindow("Help", "help.fxml", "icons/help-icon.png");
    }

    private String getPassword() {
        return passwordTextField.getText();
    }

    private void updatePasswordLengthTitle(int newValue) {
        passwordLengthText.setText("Characters: " + newValue);
    }

    private void openNewWindow(String title, String fxmlPathName, String iconPathName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlPathName));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle(title);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            String iconPath = App.class.getResource(iconPathName).toExternalForm();
            stage.getIcons().add(new Image(iconPath));

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            alerts.showErrorMessage("Could not open help window");
        }
    }

    private int getPasswordLength() {
        try {
            return (int) passwordLengthSlider.getValue();
        } catch (NumberFormatException e) {
            alerts.showErrorMessage("Invalid password length");
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
        return passwordTextField.getText().isEmpty();
    }

    private void updatePasswordLabel(String password) {
        passwordTextField.setText(password);
    }
}