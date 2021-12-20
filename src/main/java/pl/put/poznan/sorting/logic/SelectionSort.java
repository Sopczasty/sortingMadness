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
        int min_idx;
        int temp;

        for (int i = 0; i < input.length - 1; i++) {
            min_idx = i;
            for (int j = i + 1; j < input.length; j++) {
                if (direction.equals("asc") && (input[j] < input[min_idx])) min_idx = j;
                else if (direction.equals("desc") && (input[j] > input[min_idx])) min_idx = j;
            }

            temp = input[min_idx];
            input[min_idx] = input[i];
            input[i] = temp;
        }
        return input;
    }

    /**
     * Function sorting input array of objects using selection sort algorithm.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @return input array sorted using selection sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        int min_idx;
        Object temp;
        ObjectComparator objectComparator = new ObjectComparator(attribute);

        for (int i = 0; i < input.size() - 1; i++) {
            min_idx = i;
            for (int j = i + 1; j < input.size(); j++) {
                if (direction.equals("asc") &&
                        (objectComparator.compare(input.get(j), input.get(min_idx)) < 0))
                    min_idx = j;
                else if (direction.equals("desc") &&
                        (objectComparator.compare(input.get(j), input.get(min_idx)) > 0))
                    min_idx = j;
            }

            temp = input.get(min_idx);
            input.set(min_idx, input.get(i));
            input.set(i, temp);
        }
        return input;
    }

    public String getName() {
        return "SelectionSort";
    }
}
