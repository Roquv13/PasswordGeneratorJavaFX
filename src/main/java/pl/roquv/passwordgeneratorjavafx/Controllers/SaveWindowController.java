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

        File file = fileChooser.showSaveDialog(new Stage());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String password : savedPasswords) {
                bufferedWriter.write(password);
                bufferedWriter.newLine();
            }
            System.out.println("Password saved successfully to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
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
        alerts.showInfoMessage("Password has been cleared");
    }
}
