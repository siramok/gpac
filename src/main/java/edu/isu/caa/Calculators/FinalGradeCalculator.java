package edu.isu.caa.Calculators;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A set of functions for performing final grade calculations. The functions are based on the math formulas from
 * RogerHub's formula sheet found at https://rogerhub.com/~r/final/formula.pdf
 *
 * @author Andres Sewell
 */
public class FinalGradeCalculator {
    // Method Citation: https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double simpleFinalGrade(double currentGrade, double desiredGrade, double finalWeight) {
        currentGrade /= 100;
        desiredGrade /= 100;
        finalWeight /= 100;
        if(finalWeight > 0) {
            return round(((desiredGrade - currentGrade + currentGrade * finalWeight) / finalWeight) * 100, 2);
        } else {
            return 0;
        }
    }

    public static double overallGrade(double overallGrade, double finalScore, double finalWeight) {
        overallGrade /= 100;
        finalScore /= 100;
        finalWeight /= 100;
        return round((overallGrade - overallGrade * finalWeight + finalScore * finalWeight) * 100, 2);
    }

    public static double finalCountsAsTest(double currentGrade, double desiredGrade, double testWeight, int numTestsTaken, double testAverage, int numTestsPerFinal) {
        currentGrade /= 100;
        desiredGrade /= 100;
        testWeight /= 100;
        testAverage /= 100;
        if(testWeight == 1) {
            return round(((desiredGrade * numTestsTaken + desiredGrade * numTestsPerFinal - testAverage * numTestsTaken) /numTestsPerFinal) * 100, 2);
        } else if(testWeight < 1) {
            return round((((desiredGrade - currentGrade + testWeight * testAverage) * (numTestsTaken + numTestsPerFinal) - testWeight * testAverage * numTestsTaken) / (testWeight * numTestsPerFinal)) * 100, 2);
        } else {
            return 0;
        }
    }

    public static double finalExamWeight(double numPointsPossible, double numPointsFinal) {
        if(numPointsPossible != 0) {
            return round((numPointsFinal / numPointsPossible) * 100, 2);
        } else {
            return 0;
        }
    }

}
