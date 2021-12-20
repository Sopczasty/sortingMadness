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
     * @param input input array to be converted into a heap
     * @param size size of the heap
     * @param root root of the heap
     * @param direction direction of the heap (descending or ascending)
     */
    private void heapify(int input[], int size, int root, String direction)
    {
        int main = root; // Initialize largest as root
        int left = 2 * root + 1; // left = 2*i + 1
        int right = 2 * root + 2; // right = 2*i + 2

        if (direction.equals("asc")) {
            // If left child is larger than root
            if (left < size && input[left] > input[main]) main = left;
            // If right child is larger than largest so far
            if (right < size && input[right] > input[main]) main = right;
        }

        else if (direction.equals("desc")) {
            // If left child is smaller than root
            if (left < size && input[left] < input[main]) main = left;
            // If right child is smaller than smallest so far
            if (right < size && input[right] < input[main]) main = right;
        }

        // If main is not root
        if (main != root) {
            int swap = input[root];
            input[root] = input[main];
            input[main] = swap;

            // Recursively heapify the affected sub-tree
            heapify(input, size, main, direction);
        }
    }

    /**
     * Function converting input array of objects into a heap.
     * @param input input array to be converted into a heap
     * @param size size of the heap
     * @param root root of the heap
     * @param direction direction of the heap (descending or ascending)
     * @param attribute object attribute to sort by
     */
    void heapify(ArrayList<Object> input, int size, int root, String direction, String attribute)
    {
        ObjectComparator objectComparator = new ObjectComparator(attribute);

        int main = root; // Initialize largest as root
        int left = 2 * root + 1; // left = 2*i + 1
        int right = 2 * root + 2; // right = 2*i + 2

        if (direction.equals("asc")) {
            // If left child is larger than root
            if (left < size && objectComparator.compare(input.get(left), input.get(main)) > 0) main = left;
            // If right child is larger than largest so far
            if (right < size && objectComparator.compare(input.get(right), input.get(main)) > 0) main = right;
        }
        else if (direction.equals("desc")) {
            // If left child is smaller than root
            if (left < size && objectComparator.compare(input.get(left), input.get(main)) < 0) main = left;
            // If right child is smaller than largest so far
            if (right < size && objectComparator.compare(input.get(right), input.get(main)) < 0) main = right;
        }

        // If largest is not root
        if (main != root) {
            Object temp = input.get(root);
            input.set(root, input.get(main));
            input.set(main, temp);

            // Recursively heapify the affected sub-tree
            heapify(input, size, main, direction, attribute);
        }
    }

    /**
     * Main heap sort sorting function.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     */
    public void heapSort(int input[], String direction)
    {
        int n = input.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(input, n, i, direction);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = input[0];
            input[0] = input[i];
            input[i] = temp;

            // call max heapify on the reduced heap
            heapify(input, i, 0, direction);
        }
    }

    /**
     * Main heap sort sorting function for objects.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     */
    public void heapSort(ArrayList<Object> input, String direction, String attribute)
    {

        int n = input.size();

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(input, n, i, direction, attribute);

        Object temp;
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            temp = input.get(0);
            input.set(0, input.get(i));
            input.set(i, temp);

            // call max heapify on the reduced heap
            heapify(input, i, 0, direction, attribute);
        }
    }

    /**
     * Function invoking heap sort.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using heap sort
     */
    public int[] sort(int[] input, String direction) {
        int[] temp_arr = input;
        heapSort(temp_arr, direction);
        return temp_arr;
    }

    /**
     * Function invoking heap sort for objects.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @return input array of objects sorted using heap sort
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        ArrayList<Object> temp_arr = input;
        heapSort(temp_arr, direction, attribute);
        return temp_arr;
    }
  
    public String getName() {
        return "HeapSort";
    }
}
