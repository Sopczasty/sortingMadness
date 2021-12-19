package pl.put.poznan.sorting.logic;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

/**
 * Returns data sorted by bubble sort algorithm.
 */

public class BubbleSort implements Sorter {

    // Logger
    static Logger logger = LoggerFactory.getLogger(BubbleSort.class);

    /**
     * Main sorting function, returns sorted array using
     * the algorithm.
     * @param input Array of elements to be sorted.
     * @param direction Direction of the sort (descending or ascending)
     * @return Input array sorted using bubble sort.
     */
    public int[] sort(int[] input, String direction, int iterations){

        // Exception for empty input data
        if(input.length == 0){
            logger.debug("Input data is empty. Throwing exception.");
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            logger.debug("Input order is incorrect. Throwing exception.");
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        if( iterations < 0) {
            logger.debug("Input iterations is incorrect. Throwing exception.");
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

    public int[] sort(int[] input, int iterations) {
        logger.info("Direction undefined - assumed ascending.");
        input = sort(input, "asc", iterations);
        return input;
    }

    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending.");
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "asc");
        return input;
    }
    public String getName() {
        return "BubbleSort";
    }
}
