package pl.put.poznan.sorting.logic;

public class SortingWrapper {
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
