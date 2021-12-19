package pl.put.poznan.sorting.logic;

public class AutoDetector {
    private int[] data = null;
    private int size;
    public AutoDetector(int[] input) throws NullPointerException {
        data = input;
        size = data.length;
    }

    public Sorter detectSorter() {
        float percent = percentSorted();
        if(percent > 0.8 && size < 100) return new BubbleSort();
        if(size < 100) return new SelectionSort();
        if(percent > 0.8) return new InsertionSort();
        if(size < 500) return new MergeSort();
        if(percent > 0.5) return new QuickSort();
        return new HeapSort();
    }

    public float percentSorted() {
        int sortedElements = 0;
        for(int i = 1; i < size; i++) {
            if(data[i] > data[i-1]) {
                sortedElements++;
                if(i == (int) size/2 + 1)
                    if((float) sortedElements/i < 0.5) return (float) sortedElements/i;
            }
        }
        System.out.println(sortedElements);
        return (float) sortedElements/size;
    }
}
