package edu.isu.caa.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.isu.caa.Calculators.GPACalculator;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.util.Pair;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainController implements Initializable {

    public static class ScaleEntry {
        String letter;
        String range;
        String number;

        public ScaleEntry(String letter, String range, String number) {
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
    }

    public static class CourseEntry {
        private final StringProperty course = new SimpleStringProperty();
        private final StringProperty credits = new SimpleStringProperty();
        private final StringProperty expected = new SimpleStringProperty();
        private final StringProperty retake = new SimpleStringProperty();
        private final StringProperty original = new SimpleStringProperty();

        public CourseEntry() {
        }

        public String getCourse() { return course.get(); }
        public StringProperty courseProperty() { return course; }
        public void setCourse(String course) { this.course.set(course); }
        public String getCredits() { return credits.get(); }
        public StringProperty creditsProperty() { return credits; }
        public void setCredits(String credits) { this.credits.set(credits); }
        public String getExpected() { return expected.get(); }
        public StringProperty expectedProperty() { return expected; }
        public void setExpected(String expected) { this.expected.set(expected); }
        public String getRetake() { return retake.get(); }
        public StringProperty retakeProperty() { return retake; }
        public void setRetake(String retake) { this.retake.set(retake); }
        public String getOriginal() { return original.get(); }
        public StringProperty originalProperty() { return original; }
        public void setOriginal(String original) { this.original.set(original); }
    }

    GPACalculator calculator = new GPACalculator(0, 0);

    @FXML
    TableView GradeScaleTableView;

    @FXML
    TableColumn GradeScaleLetterColumn;

    @FXML
    TableColumn GradeScaleRangeColumn;

    @FXML
    TableColumn GradeScaleNumberColumn;

    @FXML
    TableView<CourseEntry> CourseTableView;

    @FXML
    TableColumn<CourseEntry, String> CourseColumn;

    @FXML
    TableColumn<CourseEntry, String> CreditHoursColumn;

    @FXML
    TableColumn<CourseEntry, String> ExpectedGradeColumn;

    @FXML
    TableColumn<CourseEntry, String> RetakeColumn;

    @FXML
    TableColumn<CourseEntry, String> OriginalGradeColumn;

    @FXML
    TextField InfoCurrentGPA;

    @FXML
    TextField InfoCurrentCredits;

    @FXML
    TextField InfoDesiredGPA;

    @FXML
    Label ResultsSemesterGPA;

    @FXML
    Label ResultsCumulativeGPA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<ScaleEntry> scaleList = FXCollections.observableArrayList(
                new ScaleEntry("A ", "93-100", "4.0"),
                new ScaleEntry("A-", "90-92.99", "3.7"),
                new ScaleEntry("B+", "87-89.99", "3.3"),
                new ScaleEntry("B ", "83-86.99", "3.0"),
                new ScaleEntry("B-", "80-82.99", "2.7"),
                new ScaleEntry("C+", "77-79.99", "2.3"),
                new ScaleEntry("C ", "73-76.99", "2.0"),
                new ScaleEntry("C-", "70-72.99", "1.7"),
                new ScaleEntry("D+", "67-69.99", "1.3"),
                new ScaleEntry("D ", "65-66.99", "1.0"),
                new ScaleEntry("F ", "0-64.99", "0.0")
        );

        ObservableList<CourseEntry> courseList = FXCollections.observableArrayList(
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry(),
                new CourseEntry()
                );

        GradeScaleLetterColumn.setCellValueFactory(new PropertyValueFactory<>("Letter"));
        GradeScaleRangeColumn.setCellValueFactory(new PropertyValueFactory<>("Range"));
        GradeScaleNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        GradeScaleTableView.setItems(scaleList);

        CourseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CreditHoursColumn.setCellValueFactory(new PropertyValueFactory<>("Credits"));
        CreditHoursColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("0", "1", "2", "3", "4", "5"));
        CreditHoursColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setCredits(newValue);
        });
        ExpectedGradeColumn.setCellValueFactory(new PropertyValueFactory<>("Expected"));
        ExpectedGradeColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        ExpectedGradeColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setExpected(newValue);
        });
        RetakeColumn.setCellValueFactory(new PropertyValueFactory<>("Retake"));
        RetakeColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("Yes", "No"));
        RetakeColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setExpected(newValue);
        });
        OriginalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("Original"));
        OriginalGradeColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        OriginalGradeColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setExpected(newValue);
        });
        CourseTableView.setItems(courseList);

    }

    @FXML
    public void calculateFields(ActionEvent event) {
        event.consume();

        calculator.setCurrentGPA(Double.parseDouble(InfoCurrentGPA.getText()));
        calculator.setCurrentCredits(Integer.parseInt(InfoCurrentCredits.getText()));

        ArrayList<Pair<String, Integer>> gradeList = new ArrayList<>();

        for(int i = 0; i < 15; i++) {
            String expected = ExpectedGradeColumn.getCellData(i);
            String credits = CreditHoursColumn.getCellData(i);
            if(expected != null && expected != "" && credits != null && credits != "") {
                Pair<String, Integer> data = new Pair(ExpectedGradeColumn.getCellData(i), Integer.parseInt(CreditHoursColumn.getCellData(i)));
                gradeList.add(data);
            }
        }

        if(gradeList.size() > 0) {
            ResultsSemesterGPA.setText(String.format("Semester GPA: %s", calculator.semesterGPA(gradeList)));
            ResultsCumulativeGPA.setText(String.format("Cumulative GPA: %s", calculator.cumulativeGPA(gradeList)));
        }

    }

    @FXML
    public void closeProgram(ActionEvent event) {
        event.consume();
        System.exit(0);
    }
}