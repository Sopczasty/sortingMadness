package pl.put.poznan.sorting.logic;

/**
 * Returns data sorted by bubble sort algorithm.
 */
public class BubbleSort implements Sorter {

    /**
     * Main sorting function, returns sorted array using
     * the algorithm.
     * @param input Array of elements to be sorted.
     * @param direction Direction of the sort (descending or ascending)
     * @return Input array sorted using bubble sort.
     */
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

    /**
     * Function invoking bubble sort algorithm if the user did not provide
     * sorting direction (using ascending by default).
     * @param input input array to be sorted
     * @return input array sorted using bubble sort algorithm
     */
    public int[] sort(int[] input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc");
        return input;
    }
}
