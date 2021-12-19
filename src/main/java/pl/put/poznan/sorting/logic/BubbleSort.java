package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

/**
 * Bubble sorting algorithm
 */
public class BubbleSort implements Sorter {

    public int[] sort(int[] input, String direction){

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
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

    public int[] sort(int[] input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc");
        return input;
    }

    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        // Exception for empty input data
        if(input.size() == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        Object temp;
        ObjectComparator objectComparator = new ObjectComparator(attribute);

        //Sorting for ascending order
        if(direction.equals("asc")){
            for (int i = 0; i < input.size() - 1; i++) {
                for (int j = 0; j < input.size() - i - 1; j++) {
                    if (objectComparator.compare(input.get(j), input.get(j+1)) > 0) {
                        temp = input.get(j);
                        input.set(j, input.get(j+1));
                        input.set(j + 1, temp);
                    }
                }
            }
        }

        //Sorting for descending order
        if(direction.equals("desc")){
            for (int i = 0; i < input.size() - 1; i++) {
                for (int j = 0; j < input.size() - i - 1; j++) {
                    if (objectComparator.compare(input.get(j), input.get(j+1)) < 0) {
                        temp = input.get(j);
                        input.set(j, input.get(j+1));
                        input.set(j + 1, temp);
                    }
                }
            }
        }
        return input;
    }

    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "asc", "time");
        return input;
    }
}
