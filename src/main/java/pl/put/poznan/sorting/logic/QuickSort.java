package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns data sorted using quick sort algorithm.
 */
public class QuickSort implements Sorter {

    // Logger
    static Logger logger = LoggerFactory.getLogger(QuickSort.class);

    /**
     * Helper function swapping two elements in array
     * @param arr input array to swap elements in
     * @param i index of first element to swap
     * @param j index of second element to swap
     */
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
  
    /**
     * Helper function swapping two objects in array
     * @param arr input array of objects to swap elements in
     * @param i index of first element to swap
     * @param j index of second element to swap
     */
    static void swap(ArrayList<Object> arr, int i, int j) {
        Object temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    /**
     * Function sorting the input subarray starting from
     * start index up to end index
     * based on pivot and returning a new pivot.
     * @param arr input subarray
     * @param low start index of the sort
     * @param high end index of the sort
     * @param direction direction of the sort (ascending or descending)
     * @return new pivot
     */
    static int partition(int[] arr, int low, int high, String direction) {
        int pivot = arr[high];
        int i = (low - 1);

        // Sorting for ascending order
        if(direction.equals("asc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j] < pivot)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j] > pivot)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }
        return (i + 1);
    }

    /**
     * Function sorting the input subarray starting from
     * start index up to end index
     * based on pivot and returning a new pivot.
     * @param arr input subarray of objects
     * @param low start index of the sort
     * @param high end index of the sort
     * @param direction direction of the sort (ascending or descending)
     * @param object attribute to sort by
     * @return new pivot
     */
    static int partition(ArrayList<Object> arr, int low, int high, String direction, String attribute) {
        Object pivot = arr.get(high);
        ObjectComparator objectComparator = new ObjectComparator(attribute);
        int i = (low - 1);

        // Sorting for ascending order
        if(direction.equals("asc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (objectComparator.compare(arr.get(j), pivot) < 0)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (objectComparator.compare(arr.get(j), pivot) > 0)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }
        return (i + 1);
    }

    /**
     * Main quick sort function sorting input array starting from
     * start index up to end index.
     * @param input input array to be sorted
     * @param low start index to sort starting from
     * @param high end index to sort up to
     * @param direction direction of the sort (ascending or descending)
     * @return input subarray sorted using quick sort algorithm
     */
    static int[] quickSort(int[] input, int low, int high, String direction) {
        int pi;
        if (low < high) {
            pi = partition(input, low, high, direction);
            quickSort(input, low, pi - 1, direction);
            quickSort(input, pi + 1, high, direction);
        }
        return input;
    }

    /**
     * Main quick sort function sorting input array of objects starting from
     * start index up to end index.
     * @param input input array to be sorted
     * @param low start index to sort starting from
     * @param high end index to sort up to
     * @param direction direction of the sort (ascending or descending)
     * @param object attribute to sort by
     * @return input subarray sorted using quick sort algorithm
     */
    static ArrayList<Object> quickSort(ArrayList<Object> input, int low, int high, String direction, String attribute) {
        int pi;
        if (low < high) {
            pi = partition(input, low, high, direction, attribute);
            quickSort(input, low, pi - 1, direction, attribute);
            quickSort(input, pi + 1, high, direction, attribute);
        }
        return input;
    }

    /**
     * Function invoking quick sort algorithm.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using quick sort algorithm
     */
    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            logger.debug("Input data is empty. Throwing exception");
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            logger.debug("Input order is incorrect. Throwing exception.");
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        logger.debug("Sorting for " + direction + "ending order.");
        input = quickSort(input, 0, input.length - 1, direction);
        return input;
    }

    /**
     * Function invoking quick sort algorithm if the user did not
     * provide sort direction (assuming ascending order).
     * @param input input data to be sorted
     * @return input data sorted using quick sort algorithm
     */
    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending");
        input = sort(input, "asc");
        return input;
    }

    /**
     * Function invoking quick sort algorithm.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @return input array sorted using quick sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        // Exception for empty input data
        if(input.size() == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if (!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }
        input = quickSort(input, 0, input.size() - 1, direction, attribute);
        return input;
    }

    /**
     * Function invoking quick sort algorithm if the user did not
     * provide sort direction (assuming ascending order).
     * @param input input data of objects to be sorted
     * @return input data sorted using quick sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "asc", "time");
        return input;
    }
  
    public String getName() {
        return "QuickSort";
    }
}
