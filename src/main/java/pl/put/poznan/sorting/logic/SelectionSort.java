package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns data sorted using selection sort algorithm.
 */
public class SelectionSort implements Sorter {

    //Logger
    static Logger logger = LoggerFactory.getLogger(SelectionSort.class);

    /**
     * Function sorting input array using selection sort algorithm.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using selection sort algorithm
     */
    public int[] sort(int[] input, String direction) {

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

        int min_idx;
        int temp;

        // Sorting for ascending order
        if(direction.equals("asc")) {
            logger.debug("Sorting for descending order");
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j] < input[min_idx])
                        min_idx = j;

                temp = input[min_idx];
                input[min_idx] = input[i];
                input[i] = temp;
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")) {
            logger.debug("Sorting for descending order.");
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j] > input[min_idx])
                        min_idx = j;

                temp = input[min_idx];
                input[min_idx] = input[i];
                input[i] = temp;
            }
        }

        return input;
    }

    /**
     * Function invoking selection sort algorithm if the user did not
     * provide sort direction (assuming ascending).
     * @param input input data to be sorted
     * @return input data sorted using selection sort algorithm
     */
    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending.");
        input = sort(input, "asc");
        return input;
    }

    /**
     * Function sorting input array of objects using selection sort algorithm.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param object attribute to sort by
     * @return input array sorted using selection sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        // Exception for empty input data
        if (input.size() == 0) {
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if (!direction.equals("asc") && !direction.equals("desc")) {
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int min_idx;
        Object temp;
        ObjectComparator objectComparator = new ObjectComparator(attribute);

        if (direction.equals("asc")) {
            for (int i = 0; i < input.size() - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.size(); j++)
                    if (objectComparator.compare(input.get(j), input.get(min_idx)) < 0)
                        min_idx = j;

                temp = input.get(min_idx);
                input.set(min_idx, input.get(i));
                input.set(i, temp);
            }
        }

        if (direction.equals("desc")) {
            for (int i = 0; i < input.size() - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.size(); j++)
                    if (objectComparator.compare(input.get(j), input.get(min_idx)) > 0)
                        min_idx = j;

                temp = input.get(min_idx);
                input.set(min_idx, input.get(i));
                input.set(i, temp);
            }
        }
        return input;
    }

    /**
     * Function invoking selection sort algorithm if the user did not
     * provide sort direction (assuming ascending).
     * @param input input data of objects to be sorted
     * @return input data sorted using selection sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc", "time");
        return input;
    }

    public String getName() {
        return "SelectionSort";
    }
}
