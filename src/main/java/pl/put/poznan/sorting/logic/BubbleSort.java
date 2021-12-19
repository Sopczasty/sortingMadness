package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
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
     * @param input array of elements to be sorted.
     * @param direction direction of the sort (descending or ascending)
     * @return input array sorted using bubble sort.
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

    /**
     * Main sorting function, returns sorted array of objects using
     * the algorithm.
     * @param input array of objects to be sorted
     * @param direction direction of the sort (descending or ascending)
     * @param attribute name of object attribute to sort by
     * @return input array sorted using bubble sort.
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        // Exception for empty input data
        if(input.size() == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        Object temp;
        ObjectComparator objectComparator = new ObjectComparator(attribute);

        //Sorting for ascending order
        if(direction.equals("asc")){
            for (int i = 0; i < input.size() - 1; i++) {
                for (int j = 0; j < input.size() - i - 1; j++) {
                    if (objectComparator.compare(input.get(j), input.get(j+1)) > 0) {
                        temp = input.get(j);
                        input.set(j, input.get(j+1));
                        input.set(j + 1, temp);
                    }
                }
            }
        }

        //Sorting for descending order
        if(direction.equals("desc")){
            for (int i = 0; i < input.size() - 1; i++) {
                for (int j = 0; j < input.size() - i - 1; j++) {
                    if (objectComparator.compare(input.get(j), input.get(j+1)) < 0) {
                        temp = input.get(j);
                        input.set(j, input.get(j+1));
                        input.set(j + 1, temp);
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
    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "asc", "time");
        return input;
    }
  
    public String getName() {
        return "BubbleSort";
    }
}
