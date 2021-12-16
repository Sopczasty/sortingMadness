package pl.put.poznan.sorting.logic;

public class InsertionSort implements Sorter {
    public int[] sort(int[] input) {
        int temp;
        int j;

        for (int i = 1; i < input.length; i++) {
            temp = input[i];
            j = i - 1;

            while (j >= 0 && input[j] > temp) {
                input[j + 1] = input[j];
                j--;
            }
            input[j + 1] = temp;
        }

        return input;
    }
}
