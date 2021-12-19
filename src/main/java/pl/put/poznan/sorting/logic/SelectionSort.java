package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

public class SelectionSort implements Sorter {
    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
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

    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }

    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute) {
        // Exception for empty input data
        if (input.size() == 0) {
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if (!direction.equals("asc") && !direction.equals("desc")) {
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int min_idx;
        Object temp;
        ObjectComparator objectComparator = new ObjectComparator(attribute);

        if (direction.equals("asc")) {
            for (int i = 0; i < input.size() - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.size(); j++)
                    if (objectComparator.compare(input.get(j), input.get(min_idx)) < 0)
                        min_idx = j;

                temp = input.get(min_idx);
                input.set(min_idx, input.get(i));
                input.set(i, temp);
            }
        }

        if (direction.equals("desc")) {
            for (int i = 0; i < input.size() - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.size(); j++)
                    if (objectComparator.compare(input.get(j), input.get(min_idx)) > 0)
                        min_idx = j;

                temp = input.get(min_idx);
                input.set(min_idx, input.get(i));
                input.set(i, temp);
            }
        }
        return input;
    }

    public ArrayList<Object> sort(ArrayList<Object> input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc", "time");
        return input;
    }
}
