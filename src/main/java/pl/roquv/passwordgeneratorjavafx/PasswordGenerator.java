package pl.roquv.passwordgeneratorjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Random;

public class PasswordGenerator {
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
    private CheckBox checkBoxSpecialCharacters;

    private final Random random;

    public PasswordGenerator() {
        random = new Random();
    }

    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase,
                                   boolean includeNumbers, boolean includeSpecialSymbols) {
        StringBuilder passwordBuilder = new StringBuilder();

        String validCharacters = "";
        if (includeUppercase) validCharacters += Constants.UPPERCASE_CHARACTERS;
        if (includeLowercase) validCharacters += Constants.LOWERCASE_CHARACTERS;
        if (includeNumbers) validCharacters += Constants.NUMBERS;
        if (includeSpecialSymbols) validCharacters += Constants.SPECIAL_SYMBOLS;

        return "ok";
    }
}