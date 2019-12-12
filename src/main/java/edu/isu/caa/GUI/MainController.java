package edu.isu.caa.GUI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import edu.isu.caa.Calculators.GPACalculator;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.javatuples.Pair;
import org.javatuples.Triplet;

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
    TextField ExtraBox1;

    @FXML
    TextField ExtraBox2;

    @FXML
    Label ResultsSemesterGPA;

    @FXML
    Label ResultsCumulativeGPA;

    @FXML
    Label StatusMessage;

    @FXML
    Label UpdateText1;

    @FXML
    Label UpdateText2;

    @FXML
    Label ExtraResult1;

    @FXML
    Label ExtraResult2;

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
                new CourseEntry()
                );

        StatusMessage.setText(" ");
        InfoCurrentGPA.setText("0.0");
        InfoCurrentCredits.setText("0");
        InfoDesiredGPA.setText("0.0");
        ExtraBox1.setText("0.0");
        ExtraBox2.setText("0");

        InfoCurrentGPA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^(\\d+(\\.\\d{0,2})?|\\.?\\d*)$")) {
                    InfoCurrentGPA.setText(oldValue);
                }
            }
        });

        InfoCurrentCredits.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    InfoCurrentCredits.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        InfoDesiredGPA.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^(\\d{0,2}+(\\.\\d{0,2})?|\\.?\\d*)$")) {
                    InfoDesiredGPA.setText(oldValue);
                }
                UpdateText1.setText(String.format(" to raise my cumulative GPA to %s?", InfoDesiredGPA.getText()));
                UpdateText2.setText(String.format("credits. What GPA do I need to raise my cumulative GPA to %s?", InfoDesiredGPA.getText()));
            }
        });

        ExtraBox1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("^(\\d{0,2}+(\\.\\d{0,2})?|\\.?\\d*)$")) {
                    ExtraBox1.setText(oldValue);
                }
            }
        });

        ExtraBox2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    ExtraBox2.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        GradeScaleLetterColumn.setCellValueFactory(new PropertyValueFactory<>("Letter"));
        GradeScaleRangeColumn.setCellValueFactory(new PropertyValueFactory<>("Range"));
        GradeScaleNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        GradeScaleTableView.setItems(scaleList);

        CourseColumn.setCellValueFactory(new PropertyValueFactory<>("Course"));
        CourseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        CourseColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setCourse(newValue);
        });
        CreditHoursColumn.setCellValueFactory(new PropertyValueFactory<>("Credits"));
        CreditHoursColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("", "0", "1", "2", "3", "4", "5"));
        CreditHoursColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setCredits(newValue);
        });
        ExpectedGradeColumn.setCellValueFactory(new PropertyValueFactory<>("Expected"));
        ExpectedGradeColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        ExpectedGradeColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setExpected(newValue);
        });
        RetakeColumn.setCellValueFactory(new PropertyValueFactory<>("Retake"));
        RetakeColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("", "Yes", "No"));
        RetakeColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setRetake(newValue);
        });
        OriginalGradeColumn.setCellValueFactory(new PropertyValueFactory<>("Original"));
        OriginalGradeColumn.setCellFactory(ComboBoxTableCell.<CourseEntry, String>forTableColumn("", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"));
        OriginalGradeColumn.setOnEditCommit( (TableColumn.CellEditEvent<CourseEntry, String> e) -> {
            String newValue = e.getNewValue();
            int index = e.getTablePosition().getRow();
            CourseEntry entry = e.getTableView().getItems().get(index);
            entry.setOriginal(newValue);
        });
        CourseTableView.setItems(courseList);

    }

    @FXML
    public void calculateFields(ActionEvent event) {
        event.consume();

        if(InfoCurrentGPA.getText().isEmpty()) {
            StatusMessage.setText(" Problem: The Current GPA field cannot be empty.");
        } else if(InfoCurrentCredits.getText().isEmpty()) {
            StatusMessage.setText(" Problem: The Current Credits field cannot be empty");
        } else if(InfoDesiredGPA.getText().isEmpty()) {
            StatusMessage.setText(" Problem: The Desired field cannot be empty");
        } else {
            calculator.setCurrentGPA(Double.parseDouble(InfoCurrentGPA.getText()));
            calculator.setCurrentCredits(Integer.parseInt(InfoCurrentCredits.getText()));

            if(ExtraBox1.getText() != null && !ExtraBox1.getText().isEmpty()) {
                ExtraResult1.setText(String.format("%s", calculator.creditsNeededToGet(Double.parseDouble(InfoDesiredGPA.getText()), Double.parseDouble(ExtraBox1.getText()))));
            }

            if(ExtraBox2.getText() != null && !ExtraBox2.getText().isEmpty()) {
                ExtraResult2.setText(String.format("%s", calculator.gpaNeededToGet(Double.parseDouble(InfoDesiredGPA.getText()), Integer.parseInt(ExtraBox2.getText()))));
            }

            ArrayList<Pair<String, Integer>> newGradeList = new ArrayList<>();
            ArrayList<Triplet<String, String, Integer>> retakeGradeList = new ArrayList<>();

            for(int i = 0; i < 15; i++) {
                String expected = ExpectedGradeColumn.getCellData(i);
                String credits = CreditHoursColumn.getCellData(i);
                if(expected != null && expected != "" && credits != null && credits != "") {
                    String retake = RetakeColumn.getCellData(i);
                    String original = OriginalGradeColumn.getCellData(i);

                    if(retake != null && retake == "Yes" && original != null && original != "") {
                        Triplet<String, String, Integer> retakeData = new Triplet(original, expected, Integer.parseInt(credits));
                        retakeGradeList.add(retakeData);
                    } else {
                        Pair<String, Integer> newData = new Pair(expected, Integer.parseInt(credits));
                        newGradeList.add(newData);
                    }
                }
            }

            if(newGradeList.size() <= 0 && retakeGradeList.size() <= 0) {
                StatusMessage.setText(" GPA cannot be calculated. Credit hours and expected GPA are required for each course entered.");
            } else {
                ResultsSemesterGPA.setText(String.format("%s", calculator.semesterGPA(newGradeList, retakeGradeList)));
                ResultsCumulativeGPA.setText(String.format("%s", calculator.cumulativeGPA(newGradeList, retakeGradeList)));
                StatusMessage.setText(" GPA Calculated successfully.");
            }
        }


    }

    @FXML
    public void closeProgram(ActionEvent event) {
        event.consume();
        System.exit(0);
    }
}