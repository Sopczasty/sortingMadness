package pl.put.poznan.sorting.app;

import pl.put.poznan.sorting.logic.*;


public class SortingMadness {
    private static int [] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
    private static String direction = "desc";
    private static SortingWrapper wrapper = new SortingWrapper();
    private static Sorter sorter = null;
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        // Example setup
        sorter = wrapper.getSorter("selection");
        System.out.println("Zestaw danych:");
        for(int i = 0; i < 10; i++) {
            System.out.print(input[i] + " ");
        }
        System.out.println("");

        // Error handling
        if (input.length == 0) {
            System.out.println("Input data is empty.");
            return;
        }

        // Error handling for incorrect ascending/descending order
        if (direction != "desc" && direction != "asc"){
            System.out.println("Input order is incorrect.");
            return;
        }

        // Example sorting
        timer.startMeasure();
        input = sorter.sort(input, direction);
        timer.stopMeasure();

        // Printing result
        System.out.println("Posortowane dane:");
        for(int i = 0; i < 10; i++) {
            System.out.print(input[i] + " ");
        }

        // Timing
        System.out.println("");
        System.out.println("Time elapsed: " + timer.getLastMeasure() + "ms");
    }
}
