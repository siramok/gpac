package edu.isu.caa.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Boilerplate code for starting a JavaFX application.
 *
 * @author Andres Sewell
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("css/styles.css").toExternalForm());
        primaryStage.setTitle("GPA Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void init(String[] args) {
        launch(args);
    }
}