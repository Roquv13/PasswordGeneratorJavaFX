package pl.roquv.passwordgeneratorjavafx.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.roquv.passwordgeneratorjavafx.Alerts;

import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveWindowController {
    @FXML
    ListView<String> passwordListView;

    @FXML
    private Button closeButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button saveToFileButton;

    private static final List<String> savedPasswords = new ArrayList<>();

    private final Alerts alerts = new Alerts();

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
        clearPasswords();
        closeWindow();
    }

    @FXML
    public void handleSaveToFileButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Passwords");

        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        if (!savedPasswords.isEmpty()) {
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                savePasswordsToFile(savedPasswords, file);
            } else {
                alerts.showErrorMessage("Password saving operation was interrupted");
            }
        } else {
            alerts.showErrorMessage("There is no passwords to save");
        }
    }

    private void savePasswordsToFile(List<String> passwordsList, File file) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String password : passwordsList) {
                bufferedWriter.write(password);
                bufferedWriter.newLine();
            }
            alerts.showInfoMessage("Passwords successfully saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            alerts.showErrorMessage("An error occurred while saving the file");
        }
    }

    public static void addPassword(String password) {
        savedPasswords.add(password);
    }

    private void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void clearPasswords() {
        // Clear List View
        passwordListView.getItems().clear();
        // Clear List of passwords
        savedPasswords.clear();
        alerts.showInfoMessage("Password list has been cleared");
    }
}
