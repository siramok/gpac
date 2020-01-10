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
public class GPACalculator {

    private double currentGPA;
    private int currentCredits;
    private Map<String, Double> scale;

    /**
     * Generates a mapping between letter grades and their GPA values upon construction. Also requires a current GPA
     * and number of current credits, since these values are utilized in each function anyways.
     *
     * @param currentGPA A double representing a student's current GPA before calculation.
     * @param currentCredits An int representing a student's current number of credits before calculation.
     */
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

    public void setCurrentGPA(double gpa) { this.currentGPA = gpa; }
    public void setCurrentCredits(int credits) { this.currentCredits = credits; }

    /**
     * Calculates a semester GPA (does not consider current GPA or current credits).
     *
     * @param newGradeList An array of pairs of letter grades and credits.
     * @param retakeGradeList An array of triplets of original letter grades, new letter grades, and credits.
     * @return A GPA rounded to two decimal places.
     */
    public double semesterGPA(List<Pair<String, Integer>> newGradeList, List<Triplet<String, String, Integer>> retakeGradeList) {
        double newGPA = 0;
        int newCredits = 0;
        for(Pair<String, Integer> grade : newGradeList) {
            newGPA += scale.get(grade.getValue0()) * grade.getValue1();
            newCredits += grade.getValue1();
        }

        for(Triplet<String, String, Integer> grade : retakeGradeList) {
            newGPA += scale.get(grade.getValue1()) * grade.getValue2();
            newCredits += grade.getValue2();
        }

        if(newCredits > 0) {
            newGPA /= newCredits;
            return Math.floor(((newGPA * newCredits) / (newCredits)) * 100) / 100;
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
    public double cumulativeGPA(List<Pair<String, Integer>> newGradeList, List<Triplet<String, String, Integer>> retakeGradeList) {
        double newGPA = 0;
        double retakeExpectedGPA = 0;
        double retakeOriginalGPA = 0;
        int newCredits = 0;
        int retakeCredits = 0;

        for(Pair<String, Integer> newGrade : newGradeList) {
            newGPA += scale.get(newGrade.getValue0()) * newGrade.getValue1();
            newCredits += newGrade.getValue1();
        }

        for(Triplet<String, String, Integer> retakeGrade : retakeGradeList) {
            retakeOriginalGPA += scale.get(retakeGrade.getValue0()) * retakeGrade.getValue2();
            retakeExpectedGPA += scale.get(retakeGrade.getValue1()) * retakeGrade.getValue2();
            retakeCredits += retakeGrade.getValue2();
        }

        if((newCredits + retakeCredits) > 0) {
            return Math.floor(((currentGPA * currentCredits + newGPA + retakeExpectedGPA - retakeOriginalGPA ) / (currentCredits + newCredits)) * 100) / 100;
        } else {
            return currentGPA;
        }
    }

    /**
     * A utility function for determining the semester GPA a student needs in order to raise their current GPA to
     * their desired GPA.
     *
     * @param desiredGPA A double representing a student's desired GPA.
     * @param newCredits An int representing the amount of new credits that a student is scheduled to take.
     * @return The GPA the student would have to get this semester to raise their current GPA to their desired GPA.
     */
    public double gpaNeededToGet(double desiredGPA, int newCredits) {
        if(newCredits > 0) {
            return (double) Math.round((((currentCredits * desiredGPA) + (newCredits * desiredGPA) - (currentCredits * currentGPA)) / newCredits) * 100) / 100;
        } else {
            return 0;
        }
    }

    /**
     * A utility function for determining the number of credits a student needs to take in order to raise their
     * current GPA to their desired GPA, assuming a given GPA.
     *
     * @param desiredGPA A double representing a student's desired GPA.
     * @param newGPA A double representing the assumed GPA for the semester.
     * @return The number of credits a student needs to take in order to raise their current GPA to their desired GPA.
     */
    public int creditsNeededToGet(double desiredGPA, double newGPA) {
        if(desiredGPA == 0 || newGPA == 0) {
            return 0;
        } else {
            return (int) Math.ceil((desiredGPA * currentCredits - currentGPA * currentCredits) / (newGPA - desiredGPA));
        }
    }
}