package pl.put.poznan.sorting.app;

import org.slf4j.event.Level;
import pl.put.poznan.sorting.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SortingMadness {
    private static int [] input = {32, 43, 12, 53, 3, 9, 1, 0, 10, 4};
    private static String direction = "asc";
    private static String type = "merge";
    private static SortingWrapper wrapper = new SortingWrapper();
    private static Sorter sorter = null;
    private static Timer timer = new Timer();
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);

    public static void main(String[] args) {

        logger.info("Initializing sorter of selected type");
        // Example setup
        sorter = wrapper.getSorter(type);
        logger.info("Selected type: " + type);


        // Error handling
        if (input.length == 0) {
            logger.debug("Input data is empty. Returning");
            return;
        }

        System.out.println("Zestaw danych:");
        for(int i = 0; i < 10; i++) {
            System.out.print(input[i] + " ");
        }
        System.out.println("");


        logger.info("Initializing sorting");
        /* Handling for sorting without specified order and with specified order
        Error handling for incorrect order */
        if(direction == null || direction == ""){
            timer.startMeasure();
            logger.debug("Direction undefined - initializing with input only");
            input = sorter.sort(input);
            timer.stopMeasure();
        }
        else if(direction == "asc" || direction == "desc"){
            timer.startMeasure();
            input = sorter.sort(input, direction);
            timer.stopMeasure();
        }
        else{
            logger.debug("Input order is incorrect. Returning");
            return;
        }
        logger.info("Ending sorting");


        // Printing result
        System.out.println("Posortowane dane:");
        for(int i = 0; i < 10; i++) {
            System.out.print(input[i] + " ");
        }

        // Timing
        System.out.println("");
        System.out.println("Time elapsed: " + timer.getLastMeasure() + "ms");
        logger.info("Sorting running time: " + timer.getLastMeasure() + "ms");
    }
}
