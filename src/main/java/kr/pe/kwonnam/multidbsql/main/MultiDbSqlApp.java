package kr.pe.kwonnam.multidbsql.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MultiDB SQL Executor UI Main
 */
public class MultiDbSqlApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MultiDB SQL Executor");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));

        Parent root = fxmlLoader.load();
        final MainController mainController = fxmlLoader.getController();
        mainController.setPrimaryStage(primaryStage);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
