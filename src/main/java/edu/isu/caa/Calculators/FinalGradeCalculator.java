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
    private static double roundDouble(double value, int digits) {
        BigDecimal toRound = BigDecimal.valueOf(value);
        toRound = toRound.setScale(digits, RoundingMode.HALF_UP);
        return toRound.doubleValue();
    }

    public static double simpleFinalGrade(double currentGrade, double desiredGrade, double finalWeight) {
        currentGrade /= 100;
        desiredGrade /= 100;
        finalWeight /= 100;
        if(finalWeight > 0) {
            return roundDouble(((desiredGrade - currentGrade + currentGrade * finalWeight) / finalWeight) * 100, 2);
        } else {
            return 0;
        }
    }

    public static double overallGrade(double overallGrade, double finalScore, double finalWeight) {
        overallGrade /= 100;
        finalScore /= 100;
        finalWeight /= 100;
        return roundDouble((overallGrade - overallGrade * finalWeight + finalScore * finalWeight) * 100, 2);
    }

    public static double finalCountsAsTest(double currentGrade, double desiredGrade, double testWeight, int numTestsTaken, double testAverage, int numTestsPerFinal) {
        currentGrade /= 100;
        desiredGrade /= 100;
        testWeight /= 100;
        testAverage /= 100;
        if(testWeight == 1) {
            return roundDouble(((desiredGrade * numTestsTaken + desiredGrade * numTestsPerFinal - testAverage * numTestsTaken) /numTestsPerFinal) * 100, 2);
        } else if(testWeight < 1) {
            return roundDouble((((desiredGrade - currentGrade + testWeight * testAverage) * (numTestsTaken + numTestsPerFinal) - testWeight * testAverage * numTestsTaken) / (testWeight * numTestsPerFinal)) * 100, 2);
        } else {
            return 0;
        }
    }

    public static double finalExamWeight(double numPointsPossible, double numPointsFinal) {
        if(numPointsPossible != 0) {
            return roundDouble((numPointsFinal / numPointsPossible) * 100, 2);
        } else {
            return 0;
        }
    }

}
