package edu.isu.caa.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class MainController implements Initializable {
    public class Scale {
        String letter;
        String range;
        String number;

        public Scale(String letter, String range, String number) {
            this.letter = letter;
            this.range = range;
            this.number = number;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public String getRange() {
            return range;
        }

        public void setRange(String range) {
            this.range = range;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }

    @FXML
    TableView GradeScaleTableView;

    @FXML
    TableColumn GradeScaleLetterColumn;

    @FXML
    TableColumn GradeScaleRangeColumn;

    @FXML
    TableColumn GradeScaleNumberColumn;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Scale> observableList = FXCollections.observableArrayList(
                new Scale("A ", "93-100", "4.0"),
                new Scale("A-", "90-92.99", "3.7"),
                new Scale("B+", "87-89.99", "3.3"),
                new Scale("B ", "83-86.99", "3.0"),
                new Scale("B-", "80-82.99", "2.7"),
                new Scale("C+", "77-79.99", "2.3"),
                new Scale("C ", "73-76.99", "2.0"),
                new Scale("C-", "70-72.99", "1.7"),
                new Scale("D+", "67-69.99", "1.3"),
                new Scale("D ", "65-66.99", "1.0"),
                new Scale("F ", "0-64.99", "0.0")
        );

        GradeScaleLetterColumn.setCellValueFactory(new PropertyValueFactory<>("Letter"));
        GradeScaleRangeColumn.setCellValueFactory(new PropertyValueFactory<>("Range"));
        GradeScaleNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        GradeScaleTableView.setItems(observableList);

    }

    @FXML
    public void closeProgram(ActionEvent event) {
        event.consume();
        System.exit(0);
    }


}