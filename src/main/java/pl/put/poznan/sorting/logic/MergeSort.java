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
     * @param a merged array
     * @param l first (left) subarray
     * @param r second (right) subarray
     * @param left last index of first (left) subarray
     * @param right last index of second (right) subarray
     * @param direction direction of the sort (ascending or descending)
     */
    static void merge(
            int[] a, int[] l, int[] r, int left, int right, String direction) {

        int i = 0, j = 0, k = 0;

        // Sorting for ascending order
        if(direction.equals("asc")){
            while (i < left && j < right) {
                if (l[i] <= r[j]) {
                    a[k++] = l[i++];
                }
                else {
                    a[k++] = r[j++];
                }
            }
            while (i < left) {
                a[k++] = l[i++];
            }
            while (j < right) {
                a[k++] = r[j++];
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")){

            while (i < left && j < right) {
                if (l[i] >= r[j]) {
                    a[k++] = l[i++];
                }
                else {
                    a[k++] = r[j++];
                }
            }
            while (i < left) {
                a[k++] = l[i++];
            }
            while (j < right) {
                a[k++] = r[j++];
            }
        }
    }

     /**
     * Function merging two subarrays of objects in merge sort algorithm.
     * @param a merged array
     * @param l first (left) subarray
     * @param r second (right) subarray
     * @param left last index of first (left) subarray
     * @param right last index of second (right) subarray
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     */
    static void merge(
            ArrayList<Object> a, ArrayList<Object> l, ArrayList<Object> r, int left, int right, String direction, String attribute) {

        int i = 0, j = 0, k = 0;

        ObjectComparator objectComparator = new ObjectComparator(attribute);
        // Sorting for ascending order
        if(direction.equals("asc")){
            while (i < left && j < right) {
                if (objectComparator.compare(l.get(i), r.get(j)) <= 0) {
                    a.set(k++, l.get(i++));
                }
                else {
                    a.set(k++, r.get(j++));
                }
            }
            while (i < left) {
                a.set(k++, l.get(i++));
            }
            while (j < right) {
                a.set(k++, r.get(j++));
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")){

            while (i < left && j < right) {
                if (objectComparator.compare(l.get(i), r.get(j)) >= 0) {
                    a.set(k++, l.get(i++));
                }
                else {
                    a.set(k++, r.get(j++));
                }
            }
            while (i < left) {
                a.set(k++, l.get(i++));
            }
            while (j < right) {
                a.set(k++, r.get(j++));
            }
        }
    }

    /**
     * Main sorting algorithm sorting using divide and conquer method.
     * @param a input array to be sorted
     * @param n size of the array to be sorted
     * @param direction direction of the sort (ascending or descending)
     */
    public static void mergeSort(int[] a, int n, String direction) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid, direction);
        mergeSort(r, n - mid, direction);

        merge(a, l, r, mid, n - mid, direction);
    }

    /**
     * Main sorting algorithm sorting objects using divide and conquer method.
     * @param a input array to be sorted
     * @param n size of the array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     */
    public static void mergeSort(ArrayList<Object> a, int n, String direction, String attribute) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        ArrayList<Object> l = new ArrayList<>(mid);
        ArrayList<Object> r = new ArrayList<>(n-mid);

        for (int i = 0; i < mid; i++) {
            l.add(new Object());
            l.set(i, a.get(i));
        }
        for (int i = mid; i < n; i++) {
            r.add(new Object());
            r.set(i - mid, a.get(i));
        }
        mergeSort(l, mid, direction, attribute);
        mergeSort(r, n - mid, direction, attribute);

        merge(a, l, r, mid, n - mid, direction, attribute);
    }

    /**
     * Function invoking merge sort algorithm.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using merge sort algorithm
     */
    public int[] sort(int input[], String direction) {

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

        int[] temp_input = input;
        logger.debug("Sorting for " + direction + "ending order.");
        mergeSort(temp_input, temp_input.length, direction);
        return temp_input;
    }

    /**
     * Function invoking merge sort algorithm if the user did not
     * provide sort direction (assuming ascending order).
     * @param input input array to be sorted
     * @return input array sorted using merge sort algorithm
     */
    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending.");
        input = sort(input, "asc");
        return input;
    }


    /**
     * Function invoking merge sort algorithm on objects.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param attribute object attribute to sort by
     * @return input array sorted using merge sort algorithm
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

        ArrayList<Object> temp_input = input;
        mergeSort(temp_input, temp_input.size(), direction, attribute);
        return temp_input;
    }

    /**
     * Function invoking merge sort algorithm on objects if the user did not
     * provide sort direction (assuming ascending order).
     * @param input input array of objects to be sorted
     * @return input array of objects sorted using merge sort algorithm
     */
    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "asc", "time");
        return input;
    }
    
    public String getName() {
        return "MergeSort";
    }
}
