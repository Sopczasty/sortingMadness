package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper over sorter that returns requested sorting algorithm object.
 */
public class SortingWrapper {

    // Logger
    static Logger logger = LoggerFactory.getLogger(SortingWrapper.class);

    /**
     * Instantiate requested sorting algorithm and return it.
     * @param type type of the algorithm to return
     * @return object of requested sorting algorithm (or null if not found)
     */
    public Sorter getSorter(String type) {
        logger.debug("Getting sorter type.");
        if (type.equals("bubble")) return new BubbleSort();
        if (type.equals("heap")) return new HeapSort();
        if (type.equals("insertion")) return new InsertionSort();
        if (type.equals("merge")) return new MergeSort();
        if (type.equals("quick")) return new QuickSort();
        if (type.equals("selection")) return new SelectionSort();
        System.out.println("Returning NULL");
        return null;
    }
}
