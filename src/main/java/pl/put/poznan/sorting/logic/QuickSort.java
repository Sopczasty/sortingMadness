package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.app.SortingMadness;

public class QuickSort implements Sorter {
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static int partition(int[] arr, int low, int high, String direction) {
        int pivot = arr[high];
        int i = (low - 1);

        // Sorting for ascending order
        if(direction == "asc"){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j] < pivot)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }

        // Sorting for descending order
        if(direction == "desc"){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j] > pivot)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }
        return (i + 1);
    }

    static int[] quickSort(int[] input, int low, int high, String direction) {
        int pi;
        if (low < high) {
            pi = partition(input, low, high, direction);
            quickSort(input, low, pi - 1, direction);
            quickSort(input, pi + 1, high, direction);
        }
        return input;
    }

    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            logger.debug("Input data is empty. Throwing exception");
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(direction != "asc" && direction != "desc"){
            logger.debug("Input order is incorrect. Throwing exception");
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        logger.debug("Sorting for " + direction + "ending order");
        input = quickSort(input, 0, input.length - 1, direction);
        return input;
    }

    public int[] sort(int[] input) {
        logger.info("Direction undefined - assumed ascending");
        input = sort(input, "asc");
        return input;
    }
}
