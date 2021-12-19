package pl.put.poznan.sorting.logic;

/**
 * Returns data sorted using selection sort algorithm.
 */
public class SelectionSort implements Sorter {

    /**
     * Function sorting input array using selection sort algorithm.
     * @param input input array to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @return input array sorted using selection sort algorithm
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

    /**
     * Function invoking selection sort algorithm if the user did not
     * provide sort direction (assuming ascending).
     * @param input input data to be sorted
     * @return input data sorted using selection sort algorithm
     */
    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }
}
