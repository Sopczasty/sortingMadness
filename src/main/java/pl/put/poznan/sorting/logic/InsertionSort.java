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
     * @param iterations how many iterations of the sort to run
     * @return input array sorted using insertion sort
     */
    public int[] sort(int[] input, String direction, int iterations) {
        int temp;
        int j;
        for (int i = 1; i < input.length; i++) {
            temp = input[i];
            j = i - 1;

            if (direction.equals("asc")) {
                while (j >= 0 && input[j] > temp) {
                    input[j + 1] = input[j];
                    j--;
                }
            }
            else if (direction.equals("desc")) {
                while (j >= 0 && input[j] < temp) {
                    input[j + 1] = input[j];
                    j--;
                }
            }
            input[j + 1] = temp;

            if(iterations > 0 && (i >= iterations)) {
                return input;
            }
        }

        return input;
    }

    /**
     * Main insertion sort algorithm on objects.
     * @param input input data of objects to be sorted
     * @param direction direction of the sort (descending or ascending)
     * @param attribute object attribute to sort by
     * @param iterations how many iterations of the sort to run
     * @return input array of objects sorted using insertion sort
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute, int iterations) {

        Object temp;
        ObjectComparator objectComparator = new ObjectComparator(attribute);
        int j;

        for(int i = 1; i < input.size(); i++){
            temp = input.get(i);
            j = i - 1;

            if (direction.equals("asc")) {
                while (j >= 0 && objectComparator.compare(input.get(j), temp) > 0){
                    input.set(j+1, input.get(j));
                    j--;
                }
            }
            else if (direction.equals("desc")) {
                while (j >= 0 && objectComparator.compare(input.get(j), temp) < 0){
                    input.set(j+1, input.get(j));
                    j--;
                }
            }
            input.set(j+1, temp);

            if(iterations > 0 && (i >= iterations)) {
                return input;
            }
        }
        return input;
    }
  
    public String getName() {
        return "InsertionSort";
    }
}
