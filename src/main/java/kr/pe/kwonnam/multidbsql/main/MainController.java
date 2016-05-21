package kr.pe.kwonnam.multidbsql.main;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleExit(Event event) {
        System.out.println("Exit");
        Platform.exit();
    }
}
