package pl.put.poznan.testing.sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.app.App;
import pl.put.poznan.sorting.app.SortingMadness;
import pl.put.poznan.sorting.logic.BubbleSort;
import pl.put.poznan.sorting.logic.Sorter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObjectQuickSortTest {
    private Object s1, s2, s3, s4, s5, s6, s7, s8;
    private String[] algorithms = {"quick"};

    @BeforeEach
    public void setUp(){
        s1 = new Sort("Cubblesort", 0.087, 7);
        s2 = new Sort("Mergesort", 0.05, 12);
        s3 = new Sort("Insertsort", 0.09, 8);
        s4 = new Sort("Heapsort", 0.06, 15);
        s5 = new Sort("Randomsort", 1.231, 5);
        s6 = new Sort("Selectionsort", 0.1, 6);
        s7 = new Sort("Quicksort", 0.043, 14);
        s8 = new Sort("Bubblesort", 0.075, 4);
    }

    /**
     * Default test for a list in ascending order: ordered by name.
     */
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
        output.add(s8);
        output.add(s1);
        output.add(s4);
        output.add(s3);
        output.add(s2);
        output.add(s7);
        output.add(s5);
        output.add(s6);

        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("asc").attribute("name").build();
        assertArrayEquals(output.toArray(), app.getObjResult().toArray());
    }


    /**
     * Default test for a list in descending order: ordered by name.
     */
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


        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("desc").attribute("name").build();
        assertArrayEquals(output.toArray(), app.getObjResult().toArray());
    }

    /**
     * Default test for a list in ascending order: ordered by time.
     */
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

        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("asc").attribute("time").build();
        assertArrayEquals(output.toArray(), app.getObjResult().toArray());
    }

    /**
     * Default test for a list in descending order: ordered by time
     */
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

        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("desc").attribute("time").build();
        assertArrayEquals(output.toArray(), app.getObjResult().toArray());
    }

    /**
     * Default test for a list in ascending order: ordered by optimal sample size.
     */
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

        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("asc").attribute("size").build();
        assertArrayEquals(output.toArray(), app.getObjResult().toArray());
    }

    /**
     * Default test for a list in descending order: ordered by optimal sample size.
     */
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

        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("desc").attribute("size").build();
        assertArrayEquals(output.toArray(), app.getObjResult().toArray());
    }


    /**
     * Test for a small list in ascending order sorted by name
     */
    @Test
    public void testSmall(){
        ArrayList<Object> input = new ArrayList<>();
        input.add(s7);
        input.add(s8);

        ArrayList<Object> output = new ArrayList<>();
        output.add(s8);
        output.add(s7);

        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("asc").attribute("name").build();
        assertArrayEquals(output.toArray(), app.getObjResult().toArray());

    }

    /**
     * Test for an incorrect time input
     */
    @Test
    public void testIncorrectInput(){
        ArrayList<Object> input = new ArrayList<>();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("asc").attribute("name").build();
        });

        String expectedMessage = "Input data is empty.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for incorrect input data
     */
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
            SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("error").attribute("name").build();
        });

        String expectedMessage = "Sorting order is incorrect.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test for incorrect input data
     */
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

        SortingMadness app = new SortingMadness.ObjectBuilder(algorithms, input).direction("desc").attribute("price").build();
        Exception exception = assertThrows(RuntimeException.class, () -> app.getObjResult());

        String expectedMessage = "Cannot compare objects - getter do not exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
