package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

public class QuickSort implements Sorter {
    static void swap(ArrayList<Object> arr, int i, int j) {
        Object temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high, String direction) {
        int pivot = arr[high];
        int i = (low - 1);

        // Sorting for ascending order
        if(direction.equals("asc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j] < pivot)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j] > pivot)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }
        return (i + 1);
    }

    static int partition(ArrayList<Object> arr, int low, int high, String direction, String attribute) {
        Object pivot = arr.get(high);
        ObjectComparator objectComparator = new ObjectComparator(attribute);
        int i = (low - 1);

        // Sorting for ascending order
        if(direction.equals("asc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (objectComparator.compare(arr.get(j), pivot) < 0)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (objectComparator.compare(arr.get(j), pivot) > 0)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }
        return (i + 1);
    }

    static int[] quickSort(int[] input, int low, int high, String direction) {
        int pi;
        if (low < high) {
            pi = partition(input, low, high, direction);
            quickSort(input, low, pi - 1, direction);
            quickSort(input, pi + 1, high, direction);
        }
        return input;
    }

    static ArrayList<Object> quickSort(ArrayList<Object> input, int low, int high, String direction, String attribute) {
        int pi;
        if (low < high) {
            pi = partition(input, low, high, direction, attribute);
            quickSort(input, low, pi - 1, direction, attribute);
            quickSort(input, pi + 1, high, direction, attribute);
        }
        return input;
    }

    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        input = quickSort(input, 0, input.length - 1, direction);
        return input;
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
        input = quickSort(input, 0, input.size() - 1, direction, attribute);
        return input;
    }

    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "asc", "time");
        return input;
    }
}
