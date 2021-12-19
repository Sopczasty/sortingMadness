package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

public class MergeSort implements Sorter {
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

    public int[] sort(int input[], String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(direction != "asc" && direction != "desc"){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int[] temp_input = input;
        mergeSort(temp_input, temp_input.length, direction);
        return temp_input;
    }

    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }

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

    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "asc", "time");
        return input;
    }
}
