package pl.put.poznan.sorting.app;

import pl.put.poznan.sorting.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class responsible for sorting objects.
 * Prints out initial data and after sorting
 * prints sorted data in specified order.
 */
public class SortingMadness {
    private static int [] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
    private static String direction = "";
    private static SortingWrapper wrapper = new SortingWrapper();
    private static Sorter sorter = null;
    private static Timer timer = new Timer();
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);
    private static String sorting_type = "bubble";


    public static void main(String[] args) {

        logger.info("Initializing sorter of selected type.");

        // Example setup
        sorter = wrapper.getSorter("bubble");

        logger.info("Selected type: " + sorting_type);

        // Error handling
        if (input.length == 0) {
            logger.debug("Input data is empty. Returning.");
            System.out.println("Input data is empty.");
            return;
        }

        System.out.println("Zestaw danych:");
        for(int i = 0; i < 10; i++) {
            System.out.print(input[i] + " ");
        }
        System.out.println("");

        logger.info("Initializing sorting.");

        /* Handling for sorting without specified order and with specified order
        Error handling for incorrect order */
        if(direction == null || direction.equals("")){
            timer.startMeasure();
            logger.debug("Direction undefined - initializing with input only");
            input = sorter.sort(input);
            timer.stopMeasure();
        }
        else if(direction.equals("asc") || direction.equals("desc")){
            timer.startMeasure();
            input = sorter.sort(input, direction);
            timer.stopMeasure();
        }
        else{
            logger.info("Input order is incorrect. Returning.");
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
