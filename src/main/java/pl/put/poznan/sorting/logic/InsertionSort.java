package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns data sorted using insertion sort algorithm.
 */
public class InsertionSort implements Sorter {

    // Logger
    static Logger logger = LoggerFactory.getLogger(InsertionSort.class);

    /**
     * Main insertion sort algorithm.
     * @param input input data to be sorted
     * @param direction direction of the sort (descending or ascending)
     * @return input array sorted using insertion sort
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

        int temp;
        int j;

        // Sorting for ascending order
        if(direction.equals("asc")) {
            logger.debug("Sorting for ascending order.");
            for (int i = 1; i < input.length; i++) {
                temp = input[i];
                j = i - 1;

                while (j >= 0 && input[j] > temp) {
                    input[j + 1] = input[j];
                    j--;
                }
                input[j + 1] = temp;
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")) {
            logger.debug("Sorting for descending order.");
            for (int i = 1; i < input.length; i++) {
                temp = input[i];
                j = i - 1;

                while (j >= 0 && input[j] < temp) {
                    input[j + 1] = input[j];
                    j--;
                }
                input[j + 1] = temp;
            }
        }

        return input;
    }

    /**
     * Function invoking insertion sort if the user did not provide sort
     * direction (assuming ascending order).
     * @param input input array to be sorted
     * @return input array sorted using insertion sort algorithm
     */
    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending");
        input = sort(input, "asc");
        return input;
    }

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
        int j;

        //Sorting for ascending order
        if(direction.equals("asc")){
            for(int i = 1; i < input.size(); i++){
                temp = input.get(i);
                j = i - 1;

                while (j >= 0 && objectComparator.compare(input.get(j), temp) > 0){
                    input.set(j+1, input.get(j));
                    j--;
                }
                input.set(j+1, temp);
            }
        }

        //Sorting for descending order
        if(direction.equals("desc")){
            for(int i = 1; i < input.size(); i++){
                temp = input.get(i);
                j = i - 1;

                while (j >= 0 && objectComparator.compare(input.get(j), temp) < 0){
                    input.set(j+1, input.get(j));
                    j--;
                }
                input.set(j+1, temp);

            }
        }
        return input;
    }

    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc", "time");
        return input;
    }
  
    public String getName() {
        return "InsertionSort";
    }
}
