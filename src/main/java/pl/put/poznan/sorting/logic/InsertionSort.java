package pl.put.poznan.sorting.logic;

public class InsertionSort implements Sorter {
    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(direction != "asc" && direction != "desc"){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int temp;
        int j;

        // Sorting for ascending order
        if(direction == "asc") {
            for (int i = 1; i < input.length; i++) {
                temp = input[i];
                j = i - 1;

                while (j >= 0 && input[j] > temp) {
                    input[j + 1] = input[j];
                    j--;
                }
                input[j + 1] = temp;
            }
        }

        // Sorting for descending order
        if(direction == "desc") {
            for (int i = 1; i < input.length; i++) {
                temp = input[i];
                j = i - 1;

                while (j >= 0 && input[j] < temp) {
                    input[j + 1] = input[j];
                    j--;
                }
                input[j + 1] = temp;
            }
        }

        return input;
    }

    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }

    public String getName() {
        return "InsertionSort";
    }
}
