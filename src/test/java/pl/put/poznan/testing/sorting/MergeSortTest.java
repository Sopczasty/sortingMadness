package pl.put.poznan.testing.sorting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import pl.put.poznan.sorting.app.App;
import pl.put.poznan.sorting.app.SortingMadness;

/**
 * Tests for merge sort.
 */
class MergeSortTest {

    private String[] algorithms = {"merge"};

    /**
     * Test for calling mock SortingMadness class.
     */
    @Test
    public void testIsAppAvailable() {
        SortingMadness sm = mock(SortingMadness.class);
        when(sm.getResult()).thenReturn(null);
        assertNull(sm.getResult());
    }

    /**
     * Test for calling standalone mock builder for given algorithm
     */
    @Test
    public void testMockBuilder() {
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        Integer[] output = new Integer[] {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        SortingMadness.PrimitiveBuilder builder = mock(SortingMadness.PrimitiveBuilder.class);
        when(builder.build()).thenReturn(new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build());
        assertArrayEquals(output, builder.build().getResult());
    }

    /**
     * Test for array in correct form (ascending order).
     */
    @Test
    public void testAscending(){
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        Integer[] output = new Integer[] {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array in correct form (descending order).
     */
    @Test
    public void testDescending(){
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        Integer[] output = new Integer[] {53, 43, 32, 12, 10, 9, 4, 3, 1, 0};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("desc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array with negative values (ascending order).
     */
    @Test
    public void testAscendingNegative(){
        Integer[] input = new Integer[] {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        Integer[] output = new Integer[] {-43, -3, -1, 0, 4, 9, 10, 12, 32, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array with negative values (descending order).
     */
    @Test
    public void testDescendingNegative(){
        Integer[] input = new Integer[] {32, -43, 12, 53, -3, 9, -1, 0, 10, 4};
        Integer[] output = new Integer[] {53, 32, 12, 10, 9, 4, 0, -1, -3, -43};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("desc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for small array (ascending order).
     */
    @Test
    public void testSmall(){
        Integer[] input = new Integer[] {32, 5};
        Integer[] output = new Integer[] {5, 32};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("asc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array without specified order.
     */
    @Test
    public void testNullDirection() {
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        Integer[] output = new Integer[] {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for 1 iteration in ascending mode.
     */
    @Test
    public void testOneIterationAsc() {
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        Integer[] output = new Integer[] {9, 1, 0, 10, 4, 32, 43, 12, 53, 3};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).iterations(1).build();
        assertArrayEquals(output, app.getResult());
    }

    /**
    *Test for 1 iteration in descending mode
     */
    @Test
    public void testOneIterationDesc() {
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        Integer[] output = new Integer[] {32, 43, 12, 53, 9, 3, 1, 0, 10, 4};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("desc").iterations(1).build();
        assertArrayEquals(output, app.getResult());
    }

    /**
    *Test for given limit of iterations greater than actual algorithm iterations
     */
    @Test
    public void testTooFar(){
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
        Integer[] output = new Integer[] {0, 1, 3, 4, 9, 10, 12, 32, 43, 53};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).iterations(1000000000).build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for string type objects (default - ascending order)
     */
    @Test
    public void testString(){
        Object[] input = new Object[] {"ad", "ba", "aa", "bc", "ac", "ab", "bd", "bb"};
        Object[] output = new Object[] {"aa", "ab", "ac", "ad", "ba", "bb", "bc", "bd"};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for string type objects (descending order)
     */
    @Test
    public void testStringDesc(){
        Object[] input = new Object[] {"ad","ba", "aa", "bc", "ac", "ab", "bd", "bb"};
        Object[] output = new Object[] {"bd", "bc", "bb", "ba", "ad", "ac", "ab", "aa"};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("desc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for float type objects (default - ascending order)
     */
    @Test
    public void testFloat(){
        Float[] input = {9.81f, 2.7f, 6.997f, 3.14f, 0.2312356f, 7f};
        Float[] output = {0.2312356f, 2.7f, 3.14f, 6.997f, 7.0f, 9.81f};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).build();
        assertArrayEquals(output, app.getResult());
    }
    /**
     * Test for float type objects (descending order)
     */
    @Test
    public void testFloatDesc(){
        Float[] input = {9.81f, 2.7f, 6.997f, 3.14f, 0.2312356f, 7f};
        Float[] output = {9.81f, 7.0f, 6.997f, 3.14f, 2.7f, 0.2312356f};
        SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("desc").build();
        assertArrayEquals(output, app.getResult());
    }

    /**
     * Test for array in incorrect format.
     */
    @Test
    public void testIncorrectInput(){
        Integer[] input = new Integer[] {};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).build();
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
        Integer[] input = new Integer[] {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            SortingMadness app = new SortingMadness.PrimitiveBuilder(algorithms).data(input).direction("error").build();
        });

        String expectedMessage = "Sorting order is incorrect.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
