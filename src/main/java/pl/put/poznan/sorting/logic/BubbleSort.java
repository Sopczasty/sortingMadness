package pl.put.poznan.sorting.logic;

import java.util.Objects;

/**
 * Bubble sorting algorithm
 */
public class BubbleSort implements Sorter {
    public int[] sort(int[] input, String direction, int iterations){

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

        if( iterations == 0) {
            return input;
        }

        int temp;
        int ctr = 0;
        //Sorting for ascending order
        if(direction.equals("asc")){
            for (int i = 0; i < input.length - 1; i++) {
                for (int j = 0; j < input.length - i - 1; j++) {
                    if (input[j] > input[j+1]) {
                        temp = input[j];
                        input[j] = input[j+1];
                        input[j+1] = temp;
                    }
                }
                if (++ctr >= iterations) return input;
            }
        }

        //Sorting for descending order
        if(direction.equals("desc")){
            for (int i = 0; i < input.length - 1; i++) {
                for (int j = 0; j < input.length - i - 1; j++) {
                    if (input[j] < input[j + 1]) {
                        temp = input[j];
                        input[j] = input[j + 1];
                        input[j + 1] = temp;

                    }
                }
                if (++ctr >= iterations) return input;
            }
        }
        return input;
    }


    public int[] sort(int[] input, String direction){

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!Objects.equals(direction, "asc") && !Objects.equals(direction, "desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int temp;
        //Sorting for ascending order
        if(direction.equals("asc")){
            for (int i = 0; i < input.length - 1; i++) {
                for (int j = 0; j < input.length - i - 1; j++) {
                    if (input[j] > input[j+1]) {
                        temp = input[j];
                        input[j] = input[j+1];
                        input[j+1] = temp;
                    }
                }
            }
        }

        //Sorting for descending order
        if(direction.equals("desc")){
            for (int i = 0; i < input.length - 1; i++) {
                for (int j = 0; j < input.length - i - 1; j++) {
                    if (input[j] < input[j+1]) {
                        temp = input[j];
                        input[j] = input[j+1];
                        input[j+1] = temp;
                    }
                }
            }
        }
        return input;
    }

    public int[] sort(int[] input, int iterations) {
        input = sort(input, "asc", iterations);
        return input;
    }

    public int[] sort(int[] input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc");
        return input;
    }
}
