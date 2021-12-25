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
     * @param iterations how many iterations of the sort to run
     * @return input array sorted using bubble sort.
     */
    public int[] sort(int[] input, String direction, int iterations){

        int temp;
        logger.debug("Sorting in " + direction + " order.");
        for (int i = 0; i < input.length - 1; i++) {
            for (int j = 0; j < input.length - i - 1; j++) {
                if (direction.equals("asc") && (input[j] > input[j+1])) {
                    temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
                else if (direction.equals("desc") && (input [j] < input[j+1])) {
                    temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
            }
            if (iterations > 0 && (i+1 >= iterations)) return input;
        }
        return input;
    }

    /**
     * Main sorting function, returns sorted array of objects using
     * the algorithm.
     * @param input array of objects to be sorted
     * @param direction direction of the sort (descending or ascending)
     * @param attribute name of object attribute to sort by
     * @param iterations how many iterations of the sort to run
     * @return input array sorted using bubble sort.
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute, int iterations) {

        Object temp;
        ObjectComparator objectComparator = new ObjectComparator(attribute);
        for (int i = 0; i < input.size() - 1; i++) {
            for (int j = 0; j < input.size() - i - 1; j++) {
                if (direction.equals("asc") && (objectComparator.compare(input.get(j), input.get(j + 1)) > 0)) {
                    temp = input.get(j);
                    input.set(j, input.get(j + 1));
                    input.set(j + 1, temp);
                } else if (direction.equals("desc") && (objectComparator.compare(input.get(j), input.get(j + 1)) < 0)) {
                    temp = input.get(j);
                    input.set(j, input.get(j + 1));
                    input.set(j + 1, temp);
                }
            }
            if (iterations > 0 && (i+1 >= iterations)) return input;
        }

        return input;
    }

    public String getName() {
        return "BubbleSort";
    }
}
