package pl.roquv.passwordgeneratorjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label passwordLabel;

    @FXML
    private Label passwordLengthLabel;

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