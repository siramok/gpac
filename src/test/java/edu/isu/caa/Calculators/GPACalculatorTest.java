package edu.isu.caa.Calculators;

import java.util.List;
import java.util.ArrayList;

import javafx.util.Pair;
import org.junit.*;


public class GPACalculatorTest {
    private GPACalculator fixture;
    private List<Pair<String, Integer>> grades;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        fixture = new GPACalculator(0, 0);
        grades = new ArrayList<>();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCalculate_1() {
        // Ensure that the default value of 0 for GPA and Credits does not break the calculation.
        grades.add(new Pair("A", 3));
        grades.add(new Pair("A", 3));
        grades.add(new Pair("A", 3));
        grades.add(new Pair("A", 3));
        Assert.assertEquals(4.0, fixture.calculate(grades), 0.0001);
    }

    @Test
    public void testCalculate_2() {
        // Ensure that GPA is calculated correctly and rounded to 2 decimal places.
        fixture.setCurrentGPA(2.8);
        fixture.setCurrentCredits(17);
        grades.add(new Pair("A", 3));
        grades.add(new Pair("A", 3));
        grades.add(new Pair("A", 3));
        grades.add(new Pair("A", 3));
        Assert.assertEquals(3.29, fixture.calculate(grades), 0.0001);
    }

    @Test
    public void testCreditsNeededToGet_1() {

    }

    @Test
    public void testGPANeededToGet_1() {
    }
}
