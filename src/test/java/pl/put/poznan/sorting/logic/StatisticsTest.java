
package pl.put.poznan.sorting.logic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.engine.discovery.predicates.IsNestedTestClass;
import org.xmlunit.builder.Input;
import pl.put.poznan.sorting.app.SortingMadness;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for collecting statistics
 */
class StatisticsTest {

    private Statistics statistics;

    @BeforeEach
    public void setUp(){
        statistics = new Statistics();
    }

    /**
     * Test for empty array
     */
    @Test
    public void testEmptyInput(){
        Integer[] input = {};
        String message = "Input data is empty.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> statistics.validateForStatistics(input));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Test for null item
     */
    @Test
    public void testNullInput(){
        Integer[] input = null;
        String message = "Input data is empty.";
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> statistics.validateForStatistics(input));
        assertEquals(message, exception.getMessage());
    }

    /**
     * Test for not numeric input
     */
    @Test
    public void testStringInput(){
        String[] input = {"John", "Anna", "James", "Mark", "Zack", "Mathew"};
        assertFalse(statistics.validateForStatistics(input));
    }

    /**
     * Test median for even size input - mean of two middle values
     */
    @Test
    public void testEvenMedian(){
        Integer[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        if (statistics.validateForStatistics(input)){
            statistics.median();
            assertEquals(9.5, statistics.getStatistics().get("Median"));
        }

    }

    /**
     * Test median for even size input - mean of two identical values
     */
    @Test
    public void testEvenMedianSame(){
        Integer[] input = {0, 1, 3, 4, 10, 10, 12, 32, 43, 53};
        if (statistics.validateForStatistics(input)){
            statistics.median();
            assertEquals(10.0, statistics.getStatistics().get("Median"));
        }

    }

    /**
     * Test median for odd size input - middle value
     */
    @Test
    public void testOddMedian(){
        Integer[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43};
        if (statistics.validateForStatistics(input)) {
            statistics.median();
            assertEquals(9.0, statistics.getStatistics().get("Median"));
        }
    }

    /**
     * Test for arithmetic mean value
     */
    @Test
    public void testMean(){
        Integer[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43};
        if (statistics.validateForStatistics(input)){
            statistics.mean();
            assertEquals(12.67, Double.parseDouble(statistics.getStatistics().get("Mean").toString()), 0.01);
        }
    }

    /**
     *  Test with negative numbers for arithmetic mean value
     */
    @Test
    public void testNegativeNumbersMean(){
        Integer[] input = {-11, -4, -1, 1, 3, 4, 10, 10, 12, 32, 43};
        if (statistics.validateForStatistics(input)){
            statistics.mean();
            assertEquals(9, Double.parseDouble(statistics.getStatistics().get("Mean").toString()), 0.01);
        }
    }

    /**
     * Test for root square mean value
     */
    @Test
    public void testRootSquareMean(){
        Integer[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        if (statistics.validateForStatistics(input)){
            statistics.rootSquareMean();
            assertEquals(24.56, Double.parseDouble(statistics.getStatistics().get("Root square mean").toString()), 0.01);
        }
    }

    /**
     * Test with negative numbers for root square mean value
     */
    @Test
    public void testNegativeNumbersRootSquareMean(){
        Integer[] input = {-53, -32, -10, -4, -1, 0, 3, 9, 12, 43};
        if (statistics.validateForStatistics(input)){
            statistics.rootSquareMean();
            assertEquals(24.56, Double.parseDouble(statistics.getStatistics().get("Root square mean").toString()), 0.01);
        }

    }

    /**
     * Test for geometric mean value
     */
    @Test
    public void testGeometricMean(){
        Integer[] input = {1, 2, 3, 4, 9, 10, 12, 32, 43, 53};
        if (statistics.validateForStatistics(input)){
            statistics.geometricMean();
            assertEquals(8.46, Double.parseDouble(statistics.getStatistics().get("Geometric mean").toString()), 0.01);
        }
    }

    /**
     * Test for input with odd number of negative values for geometric mean value
     */
    @Test
    public void testOddNegativeNumbersGeometricMean(){
        Integer[] input = {-1, 2, 3, 4, 9, 10, 12, 32, 43, 53};
        if (statistics.validateForStatistics(input)){
            String message = "Value under root cannot be negative!";
            Throwable exception = assertThrows(ArithmeticException.class, () -> statistics.geometricMean());
            assertEquals(message, exception.getMessage());
        }

    }

    /**
     * Test for input with even number of negative values for geometric mean value
     */
    @Test
    public void testEvenNegativeNumbersGeometricMean(){
        Integer[] input = {-9, -1, 2, 3, 4, 10, 12, 32, 43, 53};
        if(statistics.validateForStatistics(input)){
            statistics.geometricMean();
            assertEquals(8.46, Double.parseDouble(statistics.getStatistics().get("Geometric mean").toString()), 0.01);
        }
    }

    /**
     * Test for harmonic mean value
     */
    @Test
    public void testHarmonicMean(){
        Integer[] input = {1, 2, 3, 4, 9, 10, 12, 32, 43};
        if(statistics.validateForStatistics(input)){
            statistics.harmonicMean();
            assertEquals(3.70, Double.parseDouble(statistics.getStatistics().get("Harmonic mean").toString()), 0.01);
        }
    }

    /**
     * Test with negative numbers for harmonic mean value
     */
    @Test
    public void testNegativeNumbersHarmonicMean(){
        Integer[] input = {-1, 2, 3, 4, 9, 10, 12, 32, 43};
        if(statistics.validateForStatistics(input)){
            statistics.harmonicMean();
            assertEquals(20.82, Double.parseDouble(statistics.getStatistics().get("Harmonic mean").toString()), 0.01);
        }
    }

    /**
     *  Test for calculating all statistics
     */
    @Test
    public void testAllStatistics(){
        Integer[] input = {1, 2, 3, 4, 9, 10, 12, 32, 43};
        if(statistics.validateForStatistics(input)){
            statistics.calculateStatistics();
            assertNotEquals(-1.0, Double.parseDouble(statistics.getStatistics().get("Geometric mean").toString()), 0.01);
        }
    }

    /**
     *  Test for calculating all statistics - geometric mean default value
     */
    @Test
    public void testAllStatisticsNegative(){
        Integer[] input = {-1, 2, 3, 4, 9, 10, 12, 32, 43};
        if(statistics.validateForStatistics(input)){
            statistics.calculateStatistics();
            assertEquals(-1.0, Double.parseDouble(statistics.getStatistics().get("Geometric mean").toString()), 0.01);
        }
    }

    /**
     * Test for median using mock object - Integer
     */
    @Test
    public void testMedian(){
        Integer[] input = {1, 10, 42, 3, 51, 217, 123, 54, 2, 11};
        String[] algorithms = {"auto"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(input = (Integer[]) new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("desc").build().getResult());
        if (statistics.validateForStatistics(input)){
            statistics.calculateStatistics();
            assertEquals(26.5, statistics.getStatistics().get("Median"));
        }
     }

    /**
     * Test for mean using mock object - Float
     */
    @Test
    public void testMockMean(){
        Float[] input = {1f, 10f, 42f, 3f, 51f, 217f, 123f, 54f, 2f, 11f};
        String[] algorithms = {"insertion"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(input = (Float[]) new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build().getResult());
        if (statistics.validateForStatistics(input)) {
            statistics.calculateStatistics();
            assertEquals(51.4, statistics.getStatistics().get("Mean"));
        }
    }

    /**
     * Test for root square mean using mock object - Double
     */
    @Test
    public void testMockRootSquareMean(){
        Double[] input = {1.0, 10.0, 42.0, 3.0, 51.0, 217.0, 13.0, 54.0, 2.0, 11.0};
        String[] algorithms = {"heap"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(input = (Double[]) new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build().getResult());
        if (statistics.validateForStatistics(input)) {
            statistics.calculateStatistics();
            assertEquals(74.01, Double.parseDouble(statistics.getStatistics().get("Root square mean").toString()), 0.01);
        }
    }

    /**
     * Test for geometric mean using mock object - small array
     */
    @Test
    public void testMockGeometricMean(){
        Double[] input = {1.0, -1.0};
        String[] algorithms = {"bubble"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(input = (Double[]) new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build().getResult());
        if (statistics.validateForStatistics(input)) {
            statistics.calculateStatistics();
            assertEquals(-1.0, Double.parseDouble(statistics.getStatistics().get("Geometric mean").toString()), 0.01);
        }
    }

    /**
     * Test for not numeric data sorted by mock
     */
    @Test
    public void testMockSorted(){
        String[] input = {"Jacek", "Kasia", "Zuzana", "Julia", "Krzysztof"};
        String[] algorithms = {"bubble"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(input = (String[]) new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build().getResult());
        assertNotEquals(true, statistics.validateForStatistics(input));
    }

    /**
     * Test for not Boolean data input
     */
    @Test
    public void testMockBoolean(){
        Boolean[] input = {true, true, false, false, true};
        String[] algorithms = {"quick"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(input = (Boolean[]) new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build().getResult());
        assertFalse(statistics.validateForStatistics(input));
    }

    /**
     * Test for not Double data with limited iterations
     */
    @Test
    public void testMockSorted1(){
        Double[] input = {1.0, 10.0, 42.0, 3.0, 51.0, 217.0, 13.0, 54.0, 2.0, 11.0};
        String[] algorithms = {"selection"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(input = (Double[]) new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").iterations(4).build().getResult());
        if (statistics.validateForStatistics(input)) {
            statistics.calculateStatistics();
            assertNotEquals(26.5, Double.parseDouble(statistics.getStatistics().get("Median").toString()), 0.01);
        }
    }

    @AfterEach
    public void tearDown(){
        statistics = null;
    }
}
