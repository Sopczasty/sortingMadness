package pl.put.poznan.sorting.app;

import pl.put.poznan.sorting.logic.*;


public class SortingMadness {
    private static int [] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
    private static String direction = "asc";
    private static SortingWrapper wrapper = new SortingWrapper();
    private static Sorter sorter = null;
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        // Example setup
        sorter = wrapper.getSorter("bubble");

        // Error handling
        if (input.length == 0) {
            System.out.println("Input data is empty.");
            return;
        }

        System.out.println("Zestaw danych:");
        for(int i = 0; i < 10; i++) {
            System.out.print(input[i] + " ");
        }
        System.out.println("");


        /* Handling for sorting without specified order and with specified order
        Error handling for incorrect order */
        if(direction == null || direction == ""){
            timer.startMeasure();
            input = sorter.sort(input);
            timer.stopMeasure();
        }
        else if(direction == "asc" || direction == "desc"){
            timer.startMeasure();
            input = sorter.sort(input, direction);
            timer.stopMeasure();
        }
        else{
            System.out.println("Input order is incorrect.");
            return;
        }


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
