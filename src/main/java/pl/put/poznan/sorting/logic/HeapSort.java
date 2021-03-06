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
    private Object[] heapify(Object [] input, int size, int root, String direction)
    {
        PrimitiveComparator comp = new PrimitiveComparator();
        int main = root; // Initialize largest as root
        int left = 2 * root + 1; // left = 2*i + 1
        int right = 2 * root + 2; // right = 2*i + 2

        if (direction.equals("asc")) {
            // If left child is larger than root
            if (left < size && (comp.compare(input[left], input[main]) >= 0)) main = left;
            // If right child is larger than largest so far
            if (right < size && (comp.compare(input[right], input[main]) >= 0)) main = right;
        }

        else if (direction.equals("desc")) {
            // If left child is smaller than root
            if (left < size && (comp.compare(input[left], input[main]) <= 0)) main = left;
            // If right child is smaller than smallest so far
            if (right < size && (comp.compare(input[right], input[main]) <= 0)) main = right;
        }

        // If main is not root
        if (main != root) {
            Object swap = input[root];
            input[root] = input[main];
            input[main] = swap;

            // Recursively heapify the affected sub-tree
            heapify(input, size, main, direction);
        }
        return input;
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
     * @param iterations how many iterations of the sort to run
     */
    public void heapSort(Object [] input, String direction, int iterations)
    {
        int n = input.length;
        int depth = 0;
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            input = heapify(input, n, i, direction);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            Object temp = input[0];
            input[0] = input[i];
            input[i] = temp;

            if(iterations > 0 && ( ++depth >= iterations)) return;

            // call max heapify on the reduced heap
            heapify(input, i, 0, direction);
        }
    }

    /**
     * Main heap sort sorting function for objects.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @param iterations how many iterations of the sort to run
     */
    public void heapSort(ArrayList<Object> input, String direction, String attribute, int iterations)
    {

        int n = input.size();
        int depth = 0;
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

            if(iterations > 0 && ( ++depth >= iterations)) return;

            // call max heapify on the reduced heap
            heapify(input, i, 0, direction, attribute);
        }
    }

    /**
     * Function invoking heap sort.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param iterations how many iterations of the sort to run
     * @return input array sorted using heap sort
     */
    public Object[] sort(Object[] input, String direction, int iterations) {
        Object[] temp_arr = input;
        heapSort(temp_arr, direction, iterations);
        return temp_arr;
    }

    /**
     * Function invoking heap sort for objects.
     * @param input input array of objects to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @param iterations how many iterations of the sort to run
     * @return input array of objects sorted using heap sort
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute, int iterations) {
        ArrayList<Object> temp_arr = input;
        heapSort(temp_arr, direction, attribute, iterations);
        return temp_arr;
    }
  
    public String getName() {
        return "HeapSort";
    }
}
