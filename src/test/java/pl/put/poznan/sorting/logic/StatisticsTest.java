package pl.put.poznan.sorting.logic;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.app.SortingMadness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for collecting statistics
 */

class StatisticsTest {

    /**
     * Test median for even size input - mean of two middle values
     */
    @Test
    public void testEvenMedian(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        assertEquals(9.5, statistics.median());
    }

    /**
     * Test median for even size input - mean of two identical values
     */
    @Test
    public void testEvenMedianSame(){
        int[] input = {0, 1, 3, 4, 10, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        assertEquals(10, statistics.median());
    }

    /**
     * Negative test median for even size - only first value of median
     */
    @Test
    public void testNegativeEvenMedian1(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        assertNotEquals(9, statistics.median());
    }

    /**
     * Negative test median for even size - only second value of median
     */
    @Test
    public void testNegativeEvenMedian2(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        assertNotEquals(10, statistics.median());
    }

    /**
     * Test median for odd size input - middle value
     */
    @Test
    public void testOddMedian(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43};
        Statistics statistics = new Statistics(input);
        assertEquals(9, statistics.median());
    }

    /**
     * Negative test for odd size input - one value before middle
     */
    @Test
    public void testNegativeOddMedian1(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43};
        Statistics statistics = new Statistics(input);
        assertNotEquals(4, statistics.median());
    }

    /**
     *  Negative test for odd size input - one value after middle
     */
    @Test
    public void testNegativeOddMedian2(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43};
        Statistics statistics = new Statistics(input);
        assertNotEquals(10, statistics.median());
    }

    /**
     * Test for arithmetic mean value
     */
    @Test
    public void testMean(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43};
        Statistics statistics = new Statistics(input);
        assertEquals(12.67, statistics.mean(), 0.01);
    }

    /**
     *  Test with negative numbers for arithmetic mean value
     */
    @Test
    public void testNegativeNumbersMean(){
        int[] input = {-11, -4, -1, 1, 3, 4, 10, 10, 12, 32, 43};
        Statistics statistics = new Statistics(input);
        assertEquals(9, statistics.mean(), 0.01);
    }

    /**
     * Test for root square mean value
     */
    @Test
    public void testRootSquareMean(){
        int[] input = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        assertEquals(24.56, statistics.rootSquareMean(), 0.01);
    }

    /**
     * Test with negative numbers for root square mean value
     */
    @Test
    public void testNegativeNumbersRootSquareMean(){
        int[] input = {-53, -32, -10, -4, -1, 0, 3, 9, 12, 43};
        Statistics statistics = new Statistics(input);
        assertEquals(24.56, statistics.rootSquareMean(), 0.01);
    }

    /**
     * Test for geometric mean value
     */
    @Test
    public void testGeometricMean(){
        int[] input = {1, 2, 3, 4, 9, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        assertEquals(8.46, statistics.geometricMean(), 0.01);
    }

    /**
     * Test for input with odd number of negative values for geometric mean value
     */
    @Test
    public void testOddNegativeNumbersGeometricMean(){
        int[] input = {-1, 2, 3, 4, 9, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        String message = "Value under root cannot be negative!";
        Throwable exception = assertThrows(ArithmeticException.class, () -> statistics.geometricMean());
        assertEquals(message, exception.getMessage());
    }

    /**
     * Test for input with even number of negative values for geometric mean value
     */
    @Test
    public void testEvenNegativeNumbersGeometricMean(){
        int[] input = {-9, -1, 2, 3, 4, 10, 12, 32, 43, 53};
        Statistics statistics = new Statistics(input);
        assertEquals(8.46, statistics.geometricMean(), 0.01);
    }

    /**
     * Test for harmonic mean value
     */
    @Test
    public void testHarmonicMean(){
        int[] input = {1, 2, 3, 4, 9, 10, 12, 32, 43};
        Statistics statistics = new Statistics(input);
        assertEquals(3.70, statistics.harmonicMean(), 0.01);
    }

    /**
     * Test with negative numbers for harmonic mean value
     */
    @Test
    public void testNegativeNumbersHarmonicMean(){
        int[] input = {-1, 2, 3, 4, 9, 10, 12, 32, 43};
        Statistics statistics = new Statistics(input);
        assertEquals(20.81, statistics.harmonicMean(), 0.01);
    }

    /**
     * Test for empty array
     */
     @Test
    public void testEmptyInput(){
         int[] input = {};
         String message = "Input data is empty.";
         Throwable excepion = assertThrows(IllegalArgumentException.class, () -> new Statistics(input));
         assertEquals(message, excepion.getMessage());
     }

    /**
     * Test for median using mock object
     */
    @Test
    public void testMedian(){
        int[] data = {1, 10, 42, 3, 51, 217, 123, 54, 2, 11};
        String[] algorithms = {"auto"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(data = new SortingMadness.PrimitiveBuilder(algorithms, data).build().getResult());
        Statistics statistics = new Statistics(data);
        assertEquals(26.5, statistics.median());
     }

    /**
     * Test for mean using mock object
     */
    @Test
    public void testMockMean(){
        int[] data = {1, 10, 42, 3, 51, 217, 123, 54, 2, 11};
        String[] algorithms = {"auto"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(data = new SortingMadness.PrimitiveBuilder(algorithms, data).build().getResult());
        Statistics statistics = new Statistics(data);
        assertEquals(51.4, statistics.mean());
    }

    /**
     * Test for root square mean using mock object
     */
    @Test
    public void testMockRootSquareMean(){
        int[] data = {1, 10, 42, 3, 51, 217, 13, 54, 2, 11};
        String[] algorithms = {"auto"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(data = new SortingMadness.PrimitiveBuilder(algorithms, data).build().getResult());
        Statistics statistics = new Statistics(data);
        assertEquals(74, statistics.rootSquareMean(), 0.01);
    }

    /**
     * Test for root square mean using mock object
     */
    @Test
    public void testMockGeometricMean(){
        int[] data = {1, 10, 42, 36, 51, 217, 13, 54, 2, 11};
        String[] algorithms = {"auto"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(data = new SortingMadness.PrimitiveBuilder(algorithms, data).build().getResult());
        Statistics statistics = new Statistics(data);
        assertEquals(17.43, statistics.geometricMean(), 0.01);
    }

    /**
     * Test for root square mean using mock object
     */
    @Test
    public void testMockHarmonicMean(){
        int[] data = {1, 101, 42, 36, 51, 217, 13, 54, 20, 11};
        String[] algorithms = {"auto"};
        SortingMadness sortingMadness = mock(SortingMadness.class);
        when(sortingMadness.getResult()).thenReturn(data = new SortingMadness.PrimitiveBuilder(algorithms, data).build().getResult());
        Statistics statistics = new Statistics(data);
        assertEquals(7.56, statistics.harmonicMean(), 0.01);
    }
}