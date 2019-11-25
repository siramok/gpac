package edu.isu.caa.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;


public class MainController implements Initializable {

    @FXML
    ChoiceBox choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.getItems().clear();
        choiceBox.getItems().addAll("GPA Calculator", "How many credits at ... do I need to raise my GPA?", "What GPA would I need to raise my GPA to ...?");
        choiceBox.getSelectionModel().select(0);
    }

    @FXML
    public void closeProgram(ActionEvent event) {
        event.consume();
        System.exit(0);
    }
}