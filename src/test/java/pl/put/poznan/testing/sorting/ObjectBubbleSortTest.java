package pl.put.poznan.testing.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.logic.ObjectBubbleSort;
import pl.put.poznan.sorting.logic.ObjectSorter;
import pl.put.poznan.sorting.logic.Sort;


import static org.junit.jupiter.api.Assertions.*;

class ObjectBubbleSortTest {
    private ObjectSorter objectSorter;

    @BeforeEach
    public void setUp(){
        objectSorter = new ObjectBubbleSort();
    }

    // Default test for a list in ascending order: ordered by name
    @Test
    public void testAscending1(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                        new Sort("Mergesort", 0.05, 12),
                        new Sort("Insertsort", 0.09, 8),
                        new Sort("Heapsort", 0.06, 15),
                        new Sort("Randomsort", 1.231, 5),
                        new Sort("Selectionsort", 0.1, 6),
                        new Sort("Quicksort", 0.043, 14),
                        new Sort("Bubblesort", 0.075, 4)};
        String[] output =  {"Bubblesort", "Bubblesort", "Heapsort", "Insertsort", "Mergesort", "Quicksort", "Randomsort", "Selectionsort"};
        String direction = "asc";
        String parameter = "name";
        Sort[] input1 = objectSorter.sort(input, parameter, direction);
        String[] inputNames = {"", "", "", "", "", "", "", ""};
        for (int i = 0; i < input.length; i++) {
            inputNames[i]  = input1[i].getSortName();
        }
        assertArrayEquals(output, inputNames);
    }
    // Default test for a list in descending order: ordered by name
    @Test
    public void testDescending1(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        String[] output =  {"Selectionsort", "Randomsort", "Quicksort", "Mergesort", "Insertsort", "Heapsort", "Bubblesort", "Bubblesort"};
        String direction = "desc";
        String parameter = "name";
        Sort[] input1 = objectSorter.sort(input, parameter, direction);
        String[] inputNames = {"", "", "", "", "", "", "", ""};
        for (int i = 0; i < input.length; i++) {
            inputNames[i]  = input1[i].getSortName();
        }
        assertArrayEquals(output, inputNames);
    }

    // Default test for a list in ascending order: ordered by time
    @Test
    public void testAscending2(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        double[] output =  {0.043, 0.05, 0.06, 0.075, 0.087, 0.09, 0.1, 1.231};
        String direction = "asc";
        String parameter = "time";
        Sort[] input1 = objectSorter.sort(input, parameter, direction);
        double[] inputTimes = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < input.length; i++) {
            inputTimes[i]  = input1[i].getSortAverageTime();
        }
        assertArrayEquals(output, inputTimes);
    }

    // Default test for a list in descending order: ordered by time
    @Test
    public void testDescending2(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        double[] output =  {1.231, 0.1, 0.09, 0.087, 0.075, 0.06, 0.05, 0.043};
        String direction = "desc";
        String parameter = "time";
        Sort[] input1 = objectSorter.sort(input, parameter, direction);
        double[] inputTimes = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < input.length; i++) {
            inputTimes[i]  = input1[i].getSortAverageTime();
        }
        assertArrayEquals(output, inputTimes);
    }

    // Default test for a list in ascending order: ordered by optimal sample size
    @Test
    public void testAscending3(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        int[] output =  {4, 5, 6, 7, 8, 12, 14, 15};
        String direction = "asc";
        String parameter = "size";
        Sort[] input1 = objectSorter.sort(input, parameter, direction);
        int[] inputSize = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < input.length; i++) {
            inputSize[i]  = input1[i].getSortOptimalSize();
        }
        assertArrayEquals(output, inputSize);
    }

    // Default test for a list in descending order: ordered by optimal sample size
    @Test
    public void testDescending3(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        int[] output =  {15, 14, 12, 8, 7, 6, 5, 4};
        String direction = "desc";
        String parameter = "size";
        Sort[] input1 = objectSorter.sort(input, parameter, direction);
        int[] inputSize = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < input.length; i++) {
            inputSize[i]  = input1[i].getSortOptimalSize();
        }
        assertArrayEquals(output, inputSize);
    }

    // Test for list without specified order and sorting parameter
    @Test
    public void testOnlyArray(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        double[] output =  {0.043, 0.05, 0.06, 0.075, 0.087, 0.09, 0.1, 1.231};
        Sort[] input1 = objectSorter.sort(input);
        double[] inputTimes = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < input.length; i++) {
            inputTimes[i]  = input1[i].getSortAverageTime();
        }
        assertArrayEquals(output, inputTimes);
    }

    // Test for a small list in ascending order sorted by name
    @Test
    public void testSmall(){
        Sort[] input = {new Sort("Mergesort", 0.05, 12),
                        new Sort("Bubblesort", 0.087, 7) };
        String[] output = {"Bubblesort", "Mergesort"};
        String direction = "asc";
        String parameter = "name";
        Sort[] input1 = objectSorter.sort(input, parameter, direction);
        String[] inputNames = {"", ""};
        for (int i = 0; i < input.length; i++) {
            inputNames[i]  = input1[i].getSortName();
        }
        assertArrayEquals(output, inputNames);

    }

    // Test for a incorrect time input
    @Test
    public void testIncorrectInput(){
        Sort[] input = {};
        String direction = "asc";
        String parameter = "name";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            objectSorter.sort(input, parameter, direction);
        });

        String expectedMessage = "Input data is empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for incorrect input data
    @Test
    public void testIncorrectDirection(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        String direction = "error";
        String parameter = "name";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            objectSorter.sort(input, parameter, direction);
        });

        String expectedMessage = "Input order is incorrect.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for incorrect input data
    @Test
    public void tetsIncorectParammeter(){
        Sort[] input = {new Sort("Bubblesort", 0.087, 7),
                new Sort("Mergesort", 0.05, 12),
                new Sort("Insertsort", 0.09, 8),
                new Sort("Heapsort", 0.06, 15),
                new Sort("Randomsort", 1.231, 5),
                new Sort("Selectionsort", 0.1, 6),
                new Sort("Quicksort", 0.043, 14),
                new Sort("Bubblesort", 0.075, 4)};
        String direction = "asc";
        String parameter = "price";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            objectSorter.sort(input, parameter, direction);
        });

        String expectedMessage = "Input sorting argument is incorrect.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}