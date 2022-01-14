package pl.put.poznan.testing.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.app.App;
import pl.put.poznan.sorting.app.SortingMadness;

/**
 * Tests for bubble sort.
 */
class BubbleSortTest {

    private String[] algorithms = {"bubble"};

    /**
     * Test for array in correct form (ascending order).
     */
    @Test
    public void testAscending(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).direction("asc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array in correct form (descending order).
     */
    @Test
    public void testDescending(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {53, 43, 32, 12, 10, 9, 4, 3, 1, 0};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).direction("desc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array with negative values (ascending order).
     */
    @Test
    public void testAscendingNegative(){
        int[] input = {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        int[] output = {-43, -3, -1, 0, 4, 9, 10, 12, 32, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).direction("asc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array with negative values (descending order).
     */
    @Test
    public void testDescendingNegative(){
        int[] input = {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        int[] output = {53, 32, 12, 10, 9, 4, 0, -1, -3, -43};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).direction("desc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for small array (ascending order).
     */
    @Test
    public void testSmall(){
        int[] input = {32, 5};
        int[] output = {5, 32};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).direction("asc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array without specified order.
     */
    @Test
    public void testNullDirection() {
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for 1 iteration in ascending mode.
     */
    @Test
    public void testOneIterationAsc() {
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {32, 12, 43, 3, 9, 1, 0, 10, 4, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).iterations(1).build();
        assertArrayEquals(output, app.getResult());
    }

    //Test for 1 iteration in descending mode
    @Test
    public void testOneIterationDesc() {
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {43, 32, 53, 12, 9, 3, 1, 10, 4, 0};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).direction("desc").iterations(1).build();
        assertArrayEquals(output, app.getResult());
    }

    //Test for given limit of iterations greater than actual algorithm iterations
    @Test
    public void testTooFar(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).iterations(1000000000).build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array in incorrect format.
     */
    @Test
    public void testIncorrectInput(){
        int[] input = {};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).build();
        });

        String expectedMessage = "Input data is empty.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for array with unknown direction order.
     */
    @Test
    public void testIncorrectDirection(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms, input).direction("error").build();
        });

        String expectedMessage = "Sorting order is incorrect.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
