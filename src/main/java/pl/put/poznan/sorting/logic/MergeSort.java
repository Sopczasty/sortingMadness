package pl.put.poznan.sorting.logic;

import java.util.Objects;

public class MergeSort implements Sorter {
    static void merge(
            int[] a, int[] l, int[] r, int left, int right, String direction) {

        int i = 0, j = 0, k = 0;

        // Sorting for ascending order
        if(Objects.equals(direction, "asc")){
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
        if(Objects.equals(direction, "desc")){

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

    public static void mergeSort(int[] a, int n, String direction, int iterations) {
        if (n < 2 || iterations == 0) {
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

        mergeSort(l, mid, direction, iterations-1);
        mergeSort(r, n - mid, direction, iterations-1);



        merge(a, l, r, mid, n - mid, direction);
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

    public int[] sort(int input[], String direction, int iterations) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!Objects.equals(direction, "asc") && !Objects.equals(direction, "desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        if( iterations < 0) {
            throw new IllegalArgumentException("Number of iterations must be a positive number.");
        }

        if(iterations == 0) {
            return input;
        }

        int[] temp_input = input;
        mergeSort(temp_input, temp_input.length, direction, iterations);
        return temp_input;
    }

    public int[] sort(int input[], String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!Objects.equals(direction, "asc") && !Objects.equals(direction, "desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int[] temp_input = input;
        mergeSort(temp_input, temp_input.length, direction);
        return temp_input;
    }

    public int[] sort(int[] input, int iterations) {
        input = sort(input, "asc", iterations);
        return input;
    }

    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }
}
