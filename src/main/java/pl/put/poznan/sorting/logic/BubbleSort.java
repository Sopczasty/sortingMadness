package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.app.SortingMadness;

/**
 * Bubble sorting algorithm
 */
public class BubbleSort implements Sorter {
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);
    public int[] sort(int[] input, String direction){

        // Exception for empty input data
        if(input.length == 0){
            logger.debug("Input data is empty. Throwing exception");
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(direction != "asc" && direction != "desc"){
            logger.debug("Input order is incorrect. Throwing exception");
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int temp;

        //Sorting for ascending order
        if(direction == "asc"){
            logger.debug("Sorting for ascending order");
            for (int i = 0; i < input.length - 1; i++) {
                for (int j = 0; j < input.length - i - 1; j++) {
                    if (input[j] > input[j+1]) {
                        temp = input[j];
                        input[j] = input[j+1];
                        input[j+1] = temp;
                    }
                }
            }
        }

        //Sorting for descending order
        if(direction == "desc"){
            logger.debug("Sorting for descending order");
            for (int i = 0; i < input.length - 1; i++) {
                for (int j = 0; j < input.length - i - 1; j++) {
                    if (input[j] < input[j+1]) {
                        temp = input[j];
                        input[j] = input[j+1];
                        input[j+1] = temp;
                    }
                }
            }
        }
        return input;
    }

    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending");
        input = sort(input, "asc");
        return input;
    }
}
