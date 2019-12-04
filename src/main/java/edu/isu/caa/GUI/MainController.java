package edu.isu.caa.GUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;


public class MainController implements Initializable {
    public class TableEntry {
        String letter;
        String range;
        String number;
        String course;

        public TableEntry(String course) {
            this.course = course;
        }

        public TableEntry(String letter, String range, String number) {
            this.letter = letter;
            this.range = range;
            this.number = number;
        }

        public String getLetter() { return letter; }
        public void setLetter(String letter) { this.letter = letter; }
        public String getRange() { return range; }
        public void setRange(String range) { this.range = range; }
        public String getNumber() { return number; }
        public void setNumber(String number) { this.number = number; }
        public String getCourse() { return course; }
        public void setCourse(String course) { this.course = course; }
    }

    @FXML
    TableView GradeScaleTableView;

    @FXML
    TableColumn GradeScaleLetterColumn;

    @FXML
    TableColumn GradeScaleRangeColumn;

    @FXML
    TableColumn GradeScaleNumberColumn;

    @FXML
    TableView CourseTableView;

    @FXML
    TableColumn CourseColumn;

    @FXML
    TableColumn CreditHoursColumn;

    @FXML
    TableColumn ExpectedGradeColumn;

    @FXML
    TableColumn RetakeColumn;

    @FXML
    TableColumn OriginalGradeColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<TableEntry> scaleList = FXCollections.observableArrayList(
                new TableEntry("A ", "93-100", "4.0"),
                new TableEntry("A-", "90-92.99", "3.7"),
                new TableEntry("B+", "87-89.99", "3.3"),
                new TableEntry("B ", "83-86.99", "3.0"),
                new TableEntry("B-", "80-82.99", "2.7"),
                new TableEntry("C+", "77-79.99", "2.3"),
                new TableEntry("C ", "73-76.99", "2.0"),
                new TableEntry("C-", "70-72.99", "1.7"),
                new TableEntry("D+", "67-69.99", "1.3"),
                new TableEntry("D ", "65-66.99", "1.0"),
                new TableEntry("F ", "0-64.99", "0.0")
        );

        ObservableList<TableEntry> courseList = FXCollections.observableArrayList(
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry(""),
                new TableEntry("")
                );

        GradeScaleLetterColumn.setCellValueFactory(new PropertyValueFactory<>("Letter"));
        GradeScaleRangeColumn.setCellValueFactory(new PropertyValueFactory<>("Range"));
        GradeScaleNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        GradeScaleTableView.setItems(scaleList);

        CourseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CreditHoursColumn.setCellFactory(ComboBoxTableCell.forTableColumn("", "0", "1", "2", "3", "4", "5"));
        ExpectedGradeColumn.setCellFactory(ComboBoxTableCell.forTableColumn("", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        RetakeColumn.setCellFactory(ComboBoxTableCell.forTableColumn("", "Yes", "No"));
        OriginalGradeColumn.setCellFactory(ComboBoxTableCell.forTableColumn("", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        CourseTableView.setItems(courseList);

    }

    @FXML
    public void closeProgram(ActionEvent event) {
        event.consume();
        System.exit(0);
    }
}