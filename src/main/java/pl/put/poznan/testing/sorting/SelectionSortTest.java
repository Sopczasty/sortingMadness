package pl.put.poznan.testing.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.logic.SelectionSort;
import pl.put.poznan.sorting.logic.Sorter;

class SelectionSortTest {

    private Sorter sorter;

    @BeforeEach
    public void setUp(){
        sorter = new SelectionSort();
    }

    // Default test for a list in ascending order
    @Test
    public void testAscending(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        String direction = "asc";
        assertArrayEquals(output, sorter.sort(input, direction));
    }

    // Default test for a list in descending order
    @Test
    public void testDescending(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {53, 43, 32, 12, 10, 9, 4, 3, 1, 0};
        String direction = "desc";
        assertArrayEquals(output, sorter.sort(input, direction));
    }

    // Test for a list with negative values in ascending order
    @Test
    public void testAscendingNegative(){
        int[] input = {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        int[] output = {-43, -3, -1, 0, 4, 9, 10, 12, 32, 53};
        String direction = "asc";
        assertArrayEquals(output, sorter.sort(input, direction));
    }

    // Test for a list with negative values in descending order
    @Test
    public void testDescendingNegative(){
        int[] input = {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        int[] output = {53, 32, 12, 10, 9, 4, 0, -1, -3, -43};
        String direction = "desc";
        assertArrayEquals(output, sorter.sort(input, direction));
    }

    // Test for a small list in ascending order
    @Test
    public void testSmall(){
        int[] input = {32, 5};
        int[] output = {5, 32};
        String direction = "asc";
        assertArrayEquals(output, sorter.sort(input, direction));
    }

    // Test for list without specified order
    @Test
    public void testNullDirection() {
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        int[] output = {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        assertArrayEquals(output, sorter.sort(input));

    }

    // Test for incorrect input data
    @Test
    public void testIncorrectInput(){
        int[] input = {};
        String direction = "asc";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sorter.sort(input, direction);
        });

        String expectedMessage = "Input data is empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for incorrect input data
    @Test
    public void testIncorrectDirection(){
        int[] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        String direction = "error";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            sorter.sort(input, direction);
        });

        String expectedMessage = "Input order is incorrect.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}