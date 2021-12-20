package pl.put.poznan.testing.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.app.App;

/**
 * Tests for merge sort.
 */
class MergeSortTest {

    private App app = new App();

    /**
     * Test for array in correct form (ascending order).
     */
    @Test
    public void testAscending(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        assertArrayEquals(output, app.getResult(input, "merge", "asc"));
    }

    /**
     * Test for array in correct form (descending order).
     */
    @Test
    public void testDescending(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {53, 43, 32, 12, 10, 9, 4, 3, 1, 0};
        assertArrayEquals(output, app.getResult(input, "merge", "desc"));
    }

    /**
     * Test for array with negative values (ascending order).
     */
    @Test
    public void testAscendingNegative(){
        int[] input = {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        int[] output = {-43, -3, -1, 0, 4, 9, 10, 12, 32, 53};
        assertArrayEquals(output, app.getResult(input, "merge", "asc"));
    }

    /**
     * Test for array with negative values (descending order).
     */
    @Test
    public void testDescendingNegative(){
        int[] input = {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        int[] output = {53, 32, 12, 10, 9, 4, 0, -1, -3, -43};
        assertArrayEquals(output, app.getResult(input, "merge", "desc"));
    }

    /**
     * Test for small array (ascending order).
     */
    @Test
    public void testSmall(){
        int[] input = {32, 5};
        int[] output = {5, 32};
        assertArrayEquals(output, app.getResult(input, "merge", "asc"));
    }

    /**
     * Test for array without specified order.
     */
    @Test
    public void testNullDirection() {
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        assertArrayEquals(output, app.getResult(input, "merge"));
    }

    /**
     * Test for array in incorrect format.
     */
    @Test
    public void testIncorrectInput(){
        int[] input = {};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.getResult(input, "merge", "asc");
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
        String direction = "error";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.getResult(input, "merge", direction);
        });

        String expectedMessage = "Sorting order is incorrect.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
