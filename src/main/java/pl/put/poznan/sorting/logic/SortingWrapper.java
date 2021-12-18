package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.app.SortingMadness;

public class SortingWrapper {
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);
    public Sorter getSorter(String type) {
        logger.debug("Getting sorter type");
        if (type == "bubble") return new BubbleSort();
        if (type == "heap") return new HeapSort();
        if (type == "insertion") return new InsertionSort();
        if (type == "merge") return new MergeSort();
        if (type == "quick") return new QuickSort();
        if (type == "selection") return new SelectionSort();
        return null;
    }
}
