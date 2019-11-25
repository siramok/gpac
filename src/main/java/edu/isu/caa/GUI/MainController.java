package edu.isu.caa.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;


public class MainController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //String javaVersion = System.getProperty("java.version");
        //String javafxVersion = System.getProperty("javafx.version");
        //master.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".\n Hi Sam this is a test.");
    }

    @FXML
    public void closeProgram(ActionEvent event) {
        event.consume();
        System.exit(0);
    }
}