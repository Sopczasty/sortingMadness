package pl.put.poznan.sorting.logic;

/**
 * Returns data sorted using merge sort algorithm.
 */
public class MergeSort implements Sorter {

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
     * Function invoking merge sort algorithm.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using merge sort algorithm
     */
    public int[] sort(int input[], String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int[] temp_input = input;
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
        input = sort(input, "asc");
        return input;
    }
}
