package pl.put.poznan.sorting.logic;

public class SelectionSort implements Sorter {
    public int[] sort(int[] input) {
        int min_idx;
        int temp;

        for (int i = 0; i < input.length-1; i++)
        {
            min_idx = i;
            for (int j = i+1; j < input.length; j++)
                if (input[j] < input[min_idx])
                    min_idx = j;

            temp = input[min_idx];
            input[min_idx] = input[i];
            input[i] = temp;
        }

        return input;
    }
}
