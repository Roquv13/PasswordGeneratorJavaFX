package pl.roquv.passwordgeneratorjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private TextField passwordLabel;

    @FXML
    private TextField passwordLengthLabel;

    @FXML
    private Button generateButton;

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

        boolean checkBoxLowercaseSelected = checkBoxLowercase.isSelected();
        boolean checkBoxUppercaseSelected = checkBoxUppercase.isSelected();
        boolean checkBoxNumbersSelected = checkBoxNumbers.isSelected();
        boolean checkBoxSpecialSymbolsSelected = checkBoxSpecialSymbols.isSelected();

        String generatedPassword = passwordGenerator.generatePassword(passwordLength, checkBoxLowercaseSelected,
                checkBoxUppercaseSelected, checkBoxNumbersSelected, checkBoxSpecialSymbolsSelected);

        passwordLabel.setText(generatedPassword);
    }

    private int getPasswordLength() {
        try {
            return Integer.parseInt(passwordLengthLabel.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}