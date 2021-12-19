package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns data sorted by bubble sort algorithm.
 */
public class BubbleSort implements Sorter {

    // Logger
    static Logger logger = LoggerFactory.getLogger(BubbleSort.class);

    /**
     * Main sorting function, returns sorted array using
     * the algorithm.
     * @param input Array of elements to be sorted.
     * @param direction Direction of the sort (descending or ascending)
     * @return Input array sorted using bubble sort.
     */
    public int[] sort(int[] input, String direction){

        // Exception for empty input data
        if(input.length == 0){
            logger.debug("Input data is empty. Throwing exception.");
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            logger.debug("Input order is incorrect. Throwing exception.");
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int temp;

        //Sorting for ascending order
        if(direction.equals("asc")){
            logger.debug("Sorting for ascending order.");
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
        if(direction.equals("desc")){
            logger.debug("Sorting for descending order.");
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

    /**
     * Function invoking bubble sort algorithm if the user did not provide
     * sorting direction (using ascending by default).
     * @param input input array to be sorted
     * @return input array sorted using bubble sort algorithm
     */
    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending.");
        input = sort(input, "asc");
        return input;
    }

    public String getName() {
        return "BubbleSort";
    }
}
