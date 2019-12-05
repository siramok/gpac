package edu.isu.caa.Calculators;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPACalculator {

    private double currentGPA;
    private int currentCredits;
    private Map<String, Double> scale;

    public GPACalculator(double currentGPA, int currentCredits) {
        this.currentGPA = currentGPA;
        this.currentCredits = currentCredits;
        scale = new HashMap<>();
        scale.put("A+", 4.0);
        scale.put("A", 4.0);
        scale.put("A-", 3.7);
        scale.put("B+", 3.3);
        scale.put("B", 3.0);
        scale.put("B-", 2.7);
        scale.put("C+", 2.3);
        scale.put("C", 2.0);
        scale.put("C-", 1.7);
        scale.put("D+", 1.3);
        scale.put("D", 1.0);
        scale.put("F", 0.0);
        scale.put("X", 4.0);
    }

    public void setCurrentGPA(double gpa) {
        this.currentGPA = gpa;
    }

    public void setCurrentCredits(int credits) {
        this.currentCredits = credits;
    }

    public double semesterGPA(List<Pair<String, Integer>> gradeList) {
        double newGPA = 0;
        int newCredits = 0;
        for(Pair<String, Integer> grade : gradeList) {
            newGPA += scale.get(grade.getKey()) * grade.getValue();
            newCredits += grade.getValue();
        }
        newGPA /= newCredits;

        return Math.floor(((newGPA * newCredits) / (newCredits)) * 100) / 100;
    }

    public double cumulativeGPA(List<Pair<String, Integer>> gradeList) {
        double newGPA = 0;
        int newCredits = 0;
        for(Pair<String, Integer> grade : gradeList) {
            newGPA += scale.get(grade.getKey()) * grade.getValue();
            newCredits += grade.getValue();
        }
        newGPA /= newCredits;

        return Math.floor(((currentGPA * currentCredits + newGPA * newCredits) / (currentCredits + newCredits)) * 100) / 100;
    }

    public int creditsNeededToGet(double desiredGPA, String grade) {
        return (int) Math.ceil((currentCredits * currentGPA) / (currentCredits * desiredGPA - scale.get(grade)));
    }

    public double gpaNeededToGet(double desiredGPA, int newCredits) {
        return Math.ceil((currentCredits * newCredits * desiredGPA - currentCredits * currentGPA) / newCredits);
    }

    public double calculateRetake() {
        return 0;
    }

}
