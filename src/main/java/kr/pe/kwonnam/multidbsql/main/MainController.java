package kr.pe.kwonnam.multidbsql.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static org.slf4j.LoggerFactory.getLogger;

public class MainController implements Initializable {
    private final Logger log = getLogger(MainController.class);

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleExit(Event event) {
        log.info("Exit");
        Platform.exit();
    }

    public void handleOpenProperties(ActionEvent event) {
        log.info("Open Properties");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Properties file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("java properties", "*.properties"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("all", "*.*"));

        final File propertyFile = fileChooser.showOpenDialog(primaryStage);

        log.info("Chosen file : {}", propertyFile);
    }
}
