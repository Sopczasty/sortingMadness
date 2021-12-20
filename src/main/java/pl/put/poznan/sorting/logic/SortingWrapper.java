package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Wrapper over sorter that returns requested sorting algorithm object.
 */
public class SortingWrapper {

    // Logger
    static Logger logger = LoggerFactory.getLogger(SortingWrapper.class);

    /**
     * Instantiate requested sorting algorithm and return it.
     * @param input input data
     * @param type type of the algorithm to return
     * @return object of requested sorting algorithm (or null if not found)
     */
    public Sorter getSorter(ArrayList<Object> input, String type) {
        if (input.size() == 0) throw new NullPointerException("Input array is empty");
        if (type.equals("bubble")) return new BubbleSort();
        if (type.equals("heap")) return new HeapSort();
        if (type.equals("insertion")) return new InsertionSort();
        if (type.equals("merge")) return new MergeSort();
        if (type.equals("quick")) return new QuickSort();
        if (type.equals("selection")) return new SelectionSort();
        // TODO : AutoDetector for objects
        logger.debug("Returning NULL");
        return null;
    }

    /**
     * Instantiate requested sorting algorithm and return it.
     * @param input input data
     * @param type type of the algorithm to return
     * @return object of requested sorting algorithm (or null if not found)
     */
    public Sorter getSorter(int input[], String type) {
        if (input.length == 0) throw new NullPointerException("Input array is empty");
        if (type.equals("bubble")) return new BubbleSort();
        if (type.equals("heap")) return new HeapSort();
        if (type.equals("insertion")) return new InsertionSort();
        if (type.equals("merge")) return new MergeSort();
        if (type.equals("quick")) return new QuickSort();
        if (type.equals("selection")) return new SelectionSort();
        if (type.equals("auto")) {
            logger.debug("Detecting sorting algorithm automatically.");
            AutoDetector detector = new AutoDetector(input);
            return getSorter(input, detector.getType());
        }
        logger.debug("Returning NULL");

        return null;
    }
}
