package pl.put.poznan.testing.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.logic.InsertionSort;
import pl.put.poznan.sorting.logic.Sorter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectInsertionSortTest {

    private Sorter sorter;
    private Object s1, s2, s3, s4, s5, s6, s7, s8;

    @BeforeEach
    void setUp() {
        sorter = new InsertionSort();
        s1 = new Sort("Bubblesort", 0.087, 7);
        s2 = new Sort("Mergesort", 0.05, 12);
        s3 = new Sort("Insertsort", 0.09, 8);
        s4 = new Sort("Heapsort", 0.06, 15);
        s5 = new Sort("Randomsort", 1.231, 5);
        s6 = new Sort("Selectionsort", 0.1, 6);
        s7 = new Sort("Quicksort", 0.043, 14);
        s8 = new Sort("Bubblesort", 0.075, 4);
    }

    // Default test for a list in ascending order: ordered by name
    @Test
    public void testAscending1(){
        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s1);
        output.add(s8);
        output.add(s4);
        output.add(s3);
        output.add(s2);
        output.add(s7);
        output.add(s5);
        output.add(s6);
        String direction = "asc";
        String attribute = "name";
        assertArrayEquals(output.toArray(), sorter.sort(input, direction, attribute).toArray());
    }


    // Default test for a list in descending order: ordered by name
    @Test
    public void testDescending1(){

        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s6);
        output.add(s5);
        output.add(s7);
        output.add(s2);
        output.add(s3);
        output.add(s4);
        output.add(s1);
        output.add(s8);


        String direction = "desc";
        String attribute = "name";

        assertArrayEquals(output.toArray(), sorter.sort(input, direction, attribute).toArray());
    }

    // Default test for a list in ascending order: ordered by time
    @Test
    public void testAscending2(){

        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s7);
        output.add(s2);
        output.add(s4);
        output.add(s8);
        output.add(s1);
        output.add(s3);
        output.add(s6);
        output.add(s5);

        String direction = "asc";
        String attribute = "time";
        assertArrayEquals(output.toArray(), sorter.sort(input, direction, attribute).toArray());
    }

    // Default test for a list in descending order: ordered by time
    @Test
    public void testDescending2(){

        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s5);
        output.add(s6);
        output.add(s3);
        output.add(s1);
        output.add(s8);
        output.add(s4);
        output.add(s2);
        output.add(s7);

        String direction = "desc";
        String attribute = "time";
        assertArrayEquals(output.toArray(), sorter.sort(input, direction, attribute).toArray());
    }

    // Default test for a list in ascending order: ordered by optimal sample size
    @Test
    public void testAscending3(){

        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s8);
        output.add(s5);
        output.add(s6);
        output.add(s1);
        output.add(s3);
        output.add(s2);
        output.add(s7);
        output.add(s4);

        String direction = "asc";
        String attribute = "size";
        assertArrayEquals(output.toArray(), sorter.sort(input, direction, attribute).toArray());
    }

    // Default test for a list in descending order: ordered by optimal sample size
    @Test
    public void testDescending3() {

        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s4);
        output.add(s7);
        output.add(s2);
        output.add(s3);
        output.add(s1);
        output.add(s6);
        output.add(s5);
        output.add(s8);

        String direction = "desc";
        String attribute = "size";
        assertArrayEquals(output.toArray(), sorter.sort(input, direction, attribute).toArray());
    }

    // Test for list without specified order and sorting parameter
    @Test
    public void testOnlyArray(){
        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);
        ArrayList<Object> output = new ArrayList<>();
        output.add(s7);
        output.add(s2);
        output.add(s4);
        output.add(s8);
        output.add(s1);
        output.add(s3);
        output.add(s6);
        output.add(s5);

        assertArrayEquals(output.toArray(), sorter.sort(input).toArray());
    }

    // Test for a small list in ascending order sorted by name
    @Test
    public void testSmall(){
        ArrayList<Object> input = new ArrayList<>();
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s8);
        output.add(s7);
        String direction = "asc";
        String attribute = "name";
        assertArrayEquals(output.toArray(), sorter.sort(input, direction, attribute).toArray());

    }

    // Test for a incorrect time input
    @Test
    public void testIncorrectInput(){
        ArrayList<Object> input = new ArrayList<>();
        String direction = "asc";
        String parameter = "name";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ArrayList<Object> sort = sorter.sort(input, direction, parameter);
        });

        String expectedMessage = "Input data is empty.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for incorrect input data
    @Test
    public void testIncorrectDirection(){
        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);

        String direction = "error";
        String parameter = "name";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ArrayList<Object> sort = sorter.sort(input, direction, parameter);
        });

        String expectedMessage = "Input order is incorrect.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    // Test for incorrect input data
    @Test
    public void tetsIncorectParammeter(){
        ArrayList<Object> input = new ArrayList<>();
        input.add(s1);
        input.add(s2);
        input.add(s3);
        input.add(s4);
        input.add(s5);
        input.add(s6);
        input.add(s7);
        input.add(s8);
        String direction = "asc";
        String parameter = "price";

        Exception exception = assertThrows(RuntimeException.class, () -> sorter.sort(input, direction, parameter));

        String expectedMessage = "Cannot compare objects - getter do not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
