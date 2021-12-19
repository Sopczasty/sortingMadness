package pl.put.poznan.sorting.logic;

import java.util.Objects;

public class InsertionSort implements Sorter {
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

        int temp;
        int j;
        int ctr = 0;
        // Sorting for ascending order
        if(direction.equals("asc")) {
            for (int i = 1; i < input.length; i++) {
                temp = input[i];
                j = i - 1;

                while (j >= 0 && input[j] > temp) {
                    input[j + 1] = input[j];
                    j--;
                }
                input[j + 1] = temp;

                if(++ctr >= iterations) {
                    break;
                }
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")) {
            for (int i = 1; i < input.length; i++) {
                temp = input[i];
                j = i - 1;

                while (j >= 0 && input[j] < temp) {
                    input[j + 1] = input[j];
                    j--;
                }
                input[j + 1] = temp;

                ctr++;
                if(ctr >= iterations) {
                    break;
                }
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

        int temp;
        int j;

        // Sorting for ascending order
        if(direction.equals("asc")) {
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
        if(direction.equals("desc")) {
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

    public int[] sort(int[] input, int iterations) {
        input = sort(input, "asc", iterations);
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
