package pl.put.poznan.testing.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.app.App;
import pl.put.poznan.sorting.logic.HeapSort;
import pl.put.poznan.sorting.logic.Sorter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectHeapSortTest {

    private App app = new App();
    private Object s1, s2, s3, s4, s5, s6, s7, s8;

    @BeforeEach
    void setUp() {
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
        assertArrayEquals(output.toArray(), app.getResult(input, "heap", "asc", "name").toArray());
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
        output.add(s8);
        output.add(s1);



        String direction = "desc";
        String attribute = "name";

        assertArrayEquals(output.toArray(), app.getResult(input, "heap", "desc", "name").toArray());
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

        assertArrayEquals(output.toArray(), app.getResult(input, "heap", "asc", "time").toArray());
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

        assertArrayEquals(output.toArray(), app.getResult(input, "heap", "desc", "time").toArray());
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

        assertArrayEquals(output.toArray(), app.getResult(input, "heap", "asc", "size").toArray());
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

        assertArrayEquals(output.toArray(), app.getResult(input, "heap", "desc", "size").toArray());
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
        assertArrayEquals(output.toArray(), app.getResult(input, "heap", "asc", "name").toArray());

    }

    // Test for a incorrect time input
    @Test
    public void testIncorrectInput(){
        ArrayList<Object> input = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.getResult(input, "heap", "asc", "name").toArray();
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

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            app.getResult(input, "heap", "error", "name").toArray();
        });

        String expectedMessage = "Sorting order is incorrect.";
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
        Exception exception = assertThrows(RuntimeException.class, () -> app.getResult(input, "heap", "asc", "price").toArray());

        String expectedMessage = "Cannot compare objects - getter do not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
