package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns data sorted using heap sort algorithm.
 */
public class HeapSort implements Sorter {

    // Logger
    static Logger logger = LoggerFactory.getLogger(HeapSort.class);

    /**
     * Function converting input array into a heap.
     * @param arr input array to be converted into a heap
     * @param n size of the heap
     * @param i root of the heap
     * @param direction direction of the heap (descending or ascending)
     */
    void heapify(int arr[], int n, int i, String direction)
    {
        // Sorting for ascending order
        if(direction.equals("asc")){
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // If right child is larger than largest so far
            if (r < n && arr[r] > arr[largest])
                largest = r;

            // If largest is not root
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, largest, direction);
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            int smallest = i; // Initialize smalles as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is smaller than root
            if (l < n && arr[l] < arr[smallest])
                smallest = l;

            // If right child is smaller than smallest so far
            if (r < n && arr[r] < arr[smallest])
                smallest = r;

            // If smallest is not root
            if (smallest != i) {
                int temp = arr[i];
                arr[i] = arr[smallest];
                arr[smallest] = temp;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, smallest, direction);
            }
        }
    }

    /**
     * Function converting input array of objects into a heap.
     * @param arr input array to be converted into a heap
     * @param n size of the heap
     * @param i root of the heap
     * @param direction direction of the heap (descending or ascending)
     * @param attribute object attribute to sort by
     */
    void heapify(ArrayList<Object> temp_arr, int n, int i, String direction, String attribute)
    {
        ObjectComparator objectComparator = new ObjectComparator(attribute);

        // Sorting for ascending order
        if(direction.equals("asc")){
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < n && objectComparator.compare(temp_arr.get(l), temp_arr.get(largest)) > 0)
                largest = l;

            // If right child is larger than largest so far
            if (r < n && objectComparator.compare(temp_arr.get(r), temp_arr.get(largest)) > 0)
                largest = r;

            // If largest is not root
            if (largest != i) {
                Object temp = temp_arr.get(i);
                temp_arr.set(i, temp_arr.get(largest));
                temp_arr.set(largest, temp);

                // Recursively heapify the affected sub-tree
                heapify(temp_arr, n, largest, direction, attribute);
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            int smallest = i; // Initialize smalles as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is smaller than root
            if (l < n && objectComparator.compare(temp_arr.get(l), temp_arr.get(smallest)) < 0)
                smallest = l;

            // If right child is smaller than smallest so far
            if (r < n && objectComparator.compare(temp_arr.get(r), temp_arr.get(smallest)) < 0)
                smallest = r;

            // If smallest is not root
            if (smallest != i) {
                Object temp = temp_arr.get(i);
                temp_arr.set(i, temp_arr.get(smallest));
                temp_arr.set(smallest, temp);

                // Recursively heapify the affected sub-tree
                heapify(temp_arr, n, smallest, direction, attribute);
            }
        }
    }

    /**
     * Main heap sort sorting function.
     * @param arr input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     */
    public void heapSort(int arr[], String direction)
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, direction);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0, direction);
        }
    }

    /**
     * Main heap sort sorting function for objects.
     * @param arr input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     */
    public void heapSort(ArrayList<Object> temp_arr, String direction, String attribute)
    {

        int n = temp_arr.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(temp_arr, n, i, direction, attribute);

        Object temp;
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            temp = temp_arr.get(0);
            temp_arr.set(0, temp_arr.get(i));
            temp_arr.set(i, temp);

            // call max heapify on the reduced heap
            heapify(temp_arr, i, 0, direction, attribute);
        }
    }

    /**
     * Function invoking heap sort.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using heap sort
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

        logger.debug("Sorting for " + direction + "ending order.");
        int[] temp_arr = input;
        heapSort(temp_arr, direction);
        return temp_arr;
    }

    /**
     * Function invoking heap sort if the user did not provide sort direction
     * (assuming ascending order).
     * @param input input array to be sorted
     * @return input array sorted using heap sort algorithm
     */
    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending.");
        input = sort(input, "asc");
        return input;
    }

    /**
     * Function invoking heap sort for objects.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @return input array of objects sorted using heap sort
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

        ArrayList<Object> temp_arr = input;
        heapSort(temp_arr, direction, attribute);
        return temp_arr;

    }

    /**
     * Function invoking heap sort if the user did not provide sort direction
     * (assuming ascending order).
     * @param input input array of objects to be sorted
     * @return input array of objects sorted using heap sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc", "time");
        return input;
    }
  
    public String getName() {
        return "HeapSort";
    }
}
