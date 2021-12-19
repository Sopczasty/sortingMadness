package pl.put.poznan.sorting.app;

import pl.put.poznan.sorting.logic.*;


public class SortingMadness {
    private static int [] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
    private static Sort [] input1 = {new Sort("Bubblesort", 0.087, 7),
            new Sort("Mergesort", 0.05, 12),
            new Sort("Insertsort", 0.09, 8),
            new Sort("Heapsort", 0.06, 15),
            new Sort("Randomsort", 1.231, 5),
            new Sort("Selectionsort", 0.1, 6),
            new Sort("Quicksort", 0.043, 14),
            new Sort("Bubblesort", 0.075, 4)};
    private static String direction = "asc";
    private static String parameter = "name";
    private static SortingWrapper wrapper = new SortingWrapper();
    private static Sorter sorter = null;
    private static ObjectSorter objectSorter = null;
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        if(true){ //!args[0].equals("object")
            // Example setup
            sorter = wrapper.getSorter("bubble");

            // Error handling
            if (input.length == 0) {
                System.out.println("Input data is empty.");
                return;
            }

            System.out.println("Zestaw danych:");
            for(int i = 0; i < input.length; i++) {
                System.out.print(input[i] + " ");
            }
            System.out.println("");

            /* Handling for sorting without specified order and with specified order
            Error handling for incorrect order */
            if(direction == null || direction.equals("")){
                timer.startMeasure();
                input = sorter.sort(input);
                timer.stopMeasure();
            }
            else if(direction.equals("asc") || direction.equals("desc")){
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
            for(int i = 0; i < input.length; i++) {
                System.out.print(input[i] + " ");
            }

            // Timing
            System.out.println("");
            System.out.println("Time elapsed: " + timer.getLastMeasure() + "ms");
        }
        else{
            objectSorter = wrapper.getObjectSorter("bubble");

            // Error handling
            if (input1.length == 0) {
                System.out.println("Input data is empty.");
                return;
            }

            System.out.println("Zestaw danych:");
            for(int i = 0; i < input1.length; i++) {
                System.out.print(input1[i].getSortName() + " " + input1[i].getSortAverageTime() + " " + input1[i].getSortOptimalSize() + "\n");
            }
            System.out.println("");

            /* Handling for sorting without specified order and with specified order
            Error handling for incorrect order */
            if(direction == null || direction.equals("")){
                timer.startMeasure();
                input1 = objectSorter.sort(input1);
                timer.stopMeasure();
            }
            else if(direction.equals("asc") || direction.equals("desc")){
                timer.startMeasure();
                input1 = objectSorter.sort(input1, parameter, direction);
                timer.stopMeasure();
            }
            else{
                System.out.println("Input order is incorrect.");
                return;
            }

            // Printing result
            System.out.println("Posortowane dane:");
            for(int i = 0; i < input1.length; i++) {
                System.out.print(input1[i].getSortName() + " " + input1[i].getSortAverageTime() + " " + input1[i].getSortOptimalSize()+ "\n");
            }

            // Timing
            System.out.println("");
            System.out.println("Time elapsed: " + timer.getLastMeasure() + "ms");

        }

    }
}
