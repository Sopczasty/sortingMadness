package pl.put.poznan.sorting.logic;

public class SortingWrapper {
    public Sorter getSorter(String type) {
        if (type == "bubble") return new BubbleSort();
        if (type == "heap") return new HeapSort();
        if (type == "insertion") return new InsertionSort();
        if (type == "merge") return new MergeSort();
        if (type == "quick") return new QuickSort();
        if (type == "selection") return new SelectionSort();
        return null;
    }

    public ObjectSorter getObjectSorter(String type){
        if (type == "bubble") return new ObjectBubbleSort();
        if (type == "heap") return new ObjectHeapSort();
        if (type == "insertion") return new ObjectInsertionSort();
        if (type == "merge") return new ObjectMergeSort();
        if (type == "quick") return new ObjectQuickSort();
        if (type == "selection") return new ObjectSelectionSort();
        return null;
    }
}
