package pl.roquv.passwordgeneratorjavafx;

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
    private CheckBox checkBoxSpecialCharacters;
}