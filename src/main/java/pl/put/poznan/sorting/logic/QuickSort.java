package pl.put.poznan.sorting.logic;

public class QuickSort implements Sorter {
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++)
        {
            if (arr[j] < pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static int[] quickSort(int[] input, int low, int high) {
        int pi;
        if (low < high) {
            pi = partition(input, low, high);
            quickSort(input, low, pi - 1);
            quickSort(input, pi + 1, high);
        }
        return input;
    }

    public int[] sort(int[] input) {
        input = quickSort(input, 0, input.length - 1);
        return input;
    }
}
