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
     * @param input input subarray
     * @param low start index of the sort
     * @param high end index of the sort
     * @param direction direction of the sort (ascending or descending)
     * @return new pivot
     */
    static int partition(int[] input, int low, int high, String direction) {
        int pivot = input[high];
        int new_pivot = (low - 1);

        for(int j = low; j <= high - 1; j++) {
            if (direction.equals("asc") && (input[j] < pivot)) {
                new_pivot++;
                swap(input, new_pivot, j);
            }
            else if (direction.equals("desc") && (input[j] > pivot)) {
                new_pivot++;
                swap(input, new_pivot, j);
            }
        }
        swap(input, new_pivot + 1, high);

        return (new_pivot + 1);
    }

    /**
     * Function sorting the input subarray starting from
     * start index up to end index
     * based on pivot and returning a new pivot.
     * @param input input subarray of objects
     * @param low start index of the sort
     * @param high end index of the sort
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @return new pivot
     */
    static int partition(ArrayList<Object> input, int low, int high, String direction, String attribute) {
        Object pivot = input.get(high);
        ObjectComparator objectComparator = new ObjectComparator(attribute);
        int new_pivot = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            if (direction.equals("asc") && (objectComparator.compare(input.get(j), pivot) < 0)) {
                new_pivot++;
                swap(input, new_pivot, j);
            }
            else if (direction.equals("desc") && (objectComparator.compare(input.get(j), pivot) > 0)) {
                new_pivot++;
                swap(input, new_pivot, j);
            }
        }
        swap(input, new_pivot + 1, high);
        return (new_pivot + 1);
    }

    /**
     * Main quick sort function sorting input array starting from
     * start index up to end index.
     * @param input input array to be sorted
     * @param low start index to sort starting from
     * @param high end index to sort up to
     * @param direction direction of the sort (ascending or descending)
     * @param iterations how many iterations of the sort to run
     * @return input subarray sorted using quick sort algorithm
     */
    static int[] quickSort(int[] input, int low, int high, String direction, int iterations) {
        int pi;
        if (low < high && (iterations != 0)) {
            pi = partition(input, low, high, direction);
            quickSort(input, low, pi - 1, direction, iterations-1);
            quickSort(input, pi + 1, high, direction, iterations-1);
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
     * @param attribute object attribute to sort by
     * @param iterations how many iterations of the sort to run
     * @return input subarray sorted using quick sort algorithm
     */
    static ArrayList<Object> quickSort(ArrayList<Object> input, int low, int high, String direction, String attribute, int iterations) {
        int pi;
        if (low < high && (iterations != 0)) {
            pi = partition(input, low, high, direction, attribute);
            quickSort(input, low, pi - 1, direction, attribute, iterations-1);
            quickSort(input, pi + 1, high, direction, attribute, iterations-1);
        }
        return input;
    }

    /**
     * Function invoking quick sort algorithm.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param iterations how many iterations of the sort to run
     * @return input array sorted using quick sort algorithm
     */
    public int[] sort(int[] input, String direction, int iterations) {
        return quickSort(input, 0, input.length - 1, direction, ((iterations == 0) ? -1 : iterations));
    }

    /**
     * Function invoking quick sort algorithm.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @param iterations how many iterations of the sort to run
     * @return input array sorted using quick sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute, int iterations) {
        return quickSort(input, 0, input.size() - 1, direction, attribute, ((iterations == 0) ? -1 : iterations));
    }

    public String getName() {
        return "QuickSort";
    }
}
