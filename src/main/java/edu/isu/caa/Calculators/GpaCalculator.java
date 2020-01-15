package edu.isu.caa.Calculators;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javatuples.Pair;
import org.javatuples.Triplet;

/**
 * A class for performing GPA calculations.
 *
 * @author Andres Sewell
 */
public class GpaCalculator {

    private double currentGpa;
    private int currentCredits;
    private Map<String, Double> scale;

    /**
     * Generates a mapping between letter grades and their GPA values upon construction. Also requires a current GPA
     * and number of current credits, since these values are utilized in each function anyways.
     *
     * @param currentGpa A double representing a student's current GPA before calculation.
     * @param currentCredits An int representing a student's current number of credits before calculation.
     */
    public GpaCalculator(double currentGpa, int currentCredits) {
        this.currentGpa = currentGpa;
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

    public void setCurrentGpa(double gpa) { this.currentGpa = gpa; }
    public void setCurrentCredits(int credits) { this.currentCredits = credits; }

    /**
     * Calculates a semester GPA (does not consider current GPA or current credits).
     *
     * @param newGradeList An array of pairs of letter grades and credits.
     * @param retakeGradeList An array of triplets of original letter grades, new letter grades, and credits.
     * @return A GPA rounded to two decimal places.
     */
    public double semesterGpa(List<Pair<String, Integer>> newGradeList, List<Triplet<String, String, Integer>> retakeGradeList) {
        double newGpa = 0;
        int newCredits = 0;
        for(Pair<String, Integer> grade : newGradeList) {
            newGpa += scale.get(grade.getValue0()) * grade.getValue1();
            newCredits += grade.getValue1();
        }

        for(Triplet<String, String, Integer> grade : retakeGradeList) {
            newGpa += scale.get(grade.getValue1()) * grade.getValue2();
            newCredits += grade.getValue2();
        }

        if(newCredits > 0) {
            newGpa /= newCredits;
            return Math.floor(((newGpa * newCredits) / (newCredits)) * 100) / 100;
        } else {
            return 0;
        }
    }

    /**
     * Calculates a cumulative GPA (considers current GPA and current credits).
     *
     * @param newGradeList An array of pairs of letter grades and credits.
     * @param retakeGradeList An array of triplets of original letter grades, new letter grades, and credits.
     * @return A GPA rounded to two decimal places.
     */
    public double cumulativeGpa(List<Pair<String, Integer>> newGradeList, List<Triplet<String, String, Integer>> retakeGradeList) {
        double newGpa = 0;
        double retakeExpectedGpa = 0;
        double retakeOriginalGpa = 0;
        int newCredits = 0;
        int retakeCredits = 0;

        for(Pair<String, Integer> newGrade : newGradeList) {
            newGpa += scale.get(newGrade.getValue0()) * newGrade.getValue1();
            newCredits += newGrade.getValue1();
        }

        for(Triplet<String, String, Integer> retakeGrade : retakeGradeList) {
            retakeOriginalGpa += scale.get(retakeGrade.getValue0()) * retakeGrade.getValue2();
            retakeExpectedGpa += scale.get(retakeGrade.getValue1()) * retakeGrade.getValue2();
            retakeCredits += retakeGrade.getValue2();
        }

        if((newCredits + retakeCredits) > 0) {
            return Math.floor(((currentGpa * currentCredits + newGpa + retakeExpectedGpa - retakeOriginalGpa ) / (currentCredits + newCredits)) * 100) / 100;
        } else {
            return currentGpa;
        }
    }

    /**
     * A utility function for determining the semester GPA a student needs in order to raise their current GPA to
     * their desired GPA.
     *
     * @param desiredGpa A double representing a student's desired GPA.
     * @param newCredits An int representing the amount of new credits that a student is scheduled to take.
     * @return The GPA the student would have to get this semester to raise their current GPA to their desired GPA.
     */
    public double gpaNeededToGet(double desiredGpa, int newCredits) {
        if(newCredits > 0) {
            return (double) Math.round((((currentCredits * desiredGpa) + (newCredits * desiredGpa) - (currentCredits * currentGpa)) / newCredits) * 100) / 100;
        } else {
            return 0;
        }
    }

    /**
     * A utility function for determining the number of credits a student needs to take in order to raise their
     * current GPA to their desired GPA, assuming a given GPA.
     *
     * @param desiredGpa A double representing a student's desired GPA.
     * @param newGpa A double representing the assumed GPA for the semester.
     * @return The number of credits a student needs to take in order to raise their current GPA to their desired GPA.
     */
    public int creditsNeededToGet(double desiredGpa, double newGpa) {
        if(desiredGpa == 0 || newGpa == 0) {
            return 0;
        } else {
            return (int) Math.ceil((desiredGpa * currentCredits - currentGpa * currentCredits) / (newGpa - desiredGpa));
        }
    }
}