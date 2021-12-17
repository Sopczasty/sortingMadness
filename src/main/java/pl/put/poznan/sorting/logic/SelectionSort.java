package pl.put.poznan.sorting.logic;

public class SelectionSort implements Sorter {
    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(direction != "asc" && direction != "desc"){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int min_idx;
        int temp;

        // Sorting for ascending order
        if(direction == "asc") {
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j] < input[min_idx])
                        min_idx = j;

                temp = input[min_idx];
                input[min_idx] = input[i];
                input[i] = temp;
            }
        }

        // Sorting for descending order
        if(direction == "desc") {
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j] > input[min_idx])
                        min_idx = j;

                temp = input[min_idx];
                input[min_idx] = input[i];
                input[i] = temp;
            }
        }

        return input;
    }

    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }
}
