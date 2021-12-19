package pl.put.poznan.sorting.logic;

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
        if(direction != "asc" && direction != "desc"){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int temp;

        //Sorting for ascending order
        if(direction == "asc"){
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
        if(direction == "desc"){
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

    public String getName() {
        return "BubbleSort";
    }
}
