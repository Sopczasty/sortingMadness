package pl.put.poznan.sorting.logic;

import java.util.Objects;

public class SelectionSort implements Sorter {
    public int[] sort(int[] input, String direction, int iterations) {

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

        int min_idx;
        int temp;
        int ctr = 0;
        // Sorting for ascending order
        if(direction.equals("asc")) {
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j] < input[min_idx])
                        min_idx = j;

                temp = input[min_idx];
                input[min_idx] = input[i];
                input[i] = temp;
                if(++ctr >= iterations) break;
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")) {
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j] > input[min_idx])
                        min_idx = j;

                temp = input[min_idx];
                input[min_idx] = input[i];
                input[i] = temp;
                if(++ctr >= iterations) break;
            }
        }

        return input;
    }

    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!Objects.equals(direction, "asc") && !Objects.equals(direction, "desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int min_idx;
        int temp;

        // Sorting for ascending order
        if(direction.equals("asc")) {
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
        if(direction.equals("desc")) {
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

    public int[] sort(int[] input, int iterations) {
        input = sort(input, "asc", iterations);
        return input;
    }

    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }
}
