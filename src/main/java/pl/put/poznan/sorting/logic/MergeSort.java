package pl.put.poznan.sorting.logic;

public class MergeSort implements Sorter {
    static void merge(
            int[] a, int[] l, int[] r, int left, int right, String direction) {

        int i = 0, j = 0, k = 0;

        // Sorting for ascending order
        if(direction == "asc"){
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
        if(direction == "desc"){

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

    public int[] sort(int input[], String direction) {
        int[] temp_input = input;
        mergeSort(temp_input, temp_input.length, direction);
        return temp_input;
    }
}
