package pl.put.poznan.sorting.logic;

/**
 * Wrapper over sorter that returns requested sorting algorithm object.
 */
public class SortingWrapper {

    /**
     * Instantiate requested sorting algorithm and return it.
     * @param type type of the algorithm to return
     * @return object of requested sorting algorithm (or null if not found)
     */
    public Sorter getSorter(String type) {
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
