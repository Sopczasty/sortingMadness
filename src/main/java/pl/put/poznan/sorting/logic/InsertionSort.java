package pl.put.poznan.sorting.logic;

/**
 * Returns data sorted using insertion sort algorithm.
 */
public class InsertionSort implements Sorter {

    /**
     * Main insertion sort algorithm.
     * @param input input data to be sorted
     * @param direction direction of the sort (descending or ascending)
     * @return input array sorted using insertion sort
     */
    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
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

    /**
     * Function invoking insertion sort if the user did not provide sort
     * direction (assuming ascending order).
     * @param input input array to be sorted
     * @return input array sorted using insertion sort algorithm
     */
    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }
}
