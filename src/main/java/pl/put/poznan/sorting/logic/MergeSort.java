package pl.put.poznan.sorting.logic;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Returns data sorted using merge sort algorithm.
 */
public class MergeSort implements Sorter {

    // Logger
    static Logger logger = LoggerFactory.getLogger(MergeSort.class);

    /**
     * Function merging two subarrays in merge sort algorithm.
     * @param input merged array
     * @param l_arr first (left) subarray
     * @param r_arr second (right) subarray
     * @param left last index of first (left) subarray
     * @param right last index of second (right) subarray
     * @param direction direction of the sort (ascending or descending)
     */
    static void merge(int[] input, int[] l_arr, int[] r_arr, int left, int right, String direction) {

        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (direction.equals("asc")) {
                if (l_arr[i] <= r_arr[j]) input[k++] = l_arr[i++];
                else input[k++] = r_arr[j++];
            }
            else if (direction.equals("desc")) {
                if (l_arr[i] >= r_arr[j]) input[k++] = l_arr[i++];
                else input[k++] = r_arr[j++];
            }
        }

        while (i < left) input[k++] = l_arr[i++];
        while (j < right) input[k++] = r_arr[j++];
    }

     /**
     * Function merging two subarrays of objects in merge sort algorithm.
     * @param input merged array
     * @param l_arr first (left) subarray
     * @param r_arr second (right) subarray
     * @param left last index of first (left) subarray
     * @param right last index of second (right) subarray
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     */
    static void merge(ArrayList<Object> input, ArrayList<Object> l_arr, ArrayList<Object> r_arr,
                      int left, int right, String direction, String attribute) {

        int i = 0, j = 0, k = 0;

        ObjectComparator objectComparator = new ObjectComparator(attribute);

        while (i < left && j < right) {
            if (direction.equals("asc")) {
                if (objectComparator.compare(l_arr.get(i), r_arr.get(j)) <= 0) {
                    input.set(k++, l_arr.get(i++));
                }
                else input.set(k++, r_arr.get(j++));
            }
            else if (direction.equals("desc")) {
                if (objectComparator.compare(l_arr.get(i), r_arr.get(j)) >= 0) {
                    input.set(k++, l_arr.get(i++));
                }
                else input.set(k++, r_arr.get(j++));
            }
        }
        while (i < left) input.set(k++, l_arr.get(i++));
        while (j < right) input.set(k++, r_arr.get(j++));
    }

    /**
     * Main sorting algorithm sorting using divide and conquer method.
     * @param input input array to be sorted
     * @param size size of the array to be sorted
     * @param direction direction of the sort (ascending or descending)
     */
    public static void mergeSort(int[] input, int size, String direction) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        int[] l = new int[mid];
        int[] r = new int[size - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = input[i];
        }
        for (int i = mid; i < size; i++) {
            r[i - mid] = input[i];
        }
        mergeSort(l, mid, direction);
        mergeSort(r, size - mid, direction);

        merge(input, l, r, mid, size - mid, direction);
    }

    /**
     * Main sorting algorithm sorting objects using divide and conquer method.
     * @param input input array to be sorted
     * @param size size of the array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     */
    public static void mergeSort(ArrayList<Object> input, int size, String direction, String attribute) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        ArrayList<Object> l = new ArrayList<>(mid);
        ArrayList<Object> r = new ArrayList<>(size-mid);

        for (int i = 0; i < mid; i++) {
            l.add(new Object());
            l.set(i, input.get(i));
        }
        for (int i = mid; i < size; i++) {
            r.add(new Object());
            r.set(i - mid, input.get(i));
        }
        mergeSort(l, mid, direction, attribute);
        mergeSort(r, size - mid, direction, attribute);

        merge(input, l, r, mid, size - mid, direction, attribute);
    }

    /**
     * Function invoking merge sort algorithm.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using merge sort algorithm
     */
    public int[] sort(int input[], String direction) {
        int[] temp_input = input;
        mergeSort(temp_input, temp_input.length, direction);
        return temp_input;
    }

    /**
     * Function invoking merge sort algorithm on objects.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @return input array sorted using merge sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        ArrayList<Object> temp_input = input;
        mergeSort(temp_input, temp_input.size(), direction, attribute);
        return temp_input;
    }
    
    public String getName() {
        return "MergeSort";
    }
}
