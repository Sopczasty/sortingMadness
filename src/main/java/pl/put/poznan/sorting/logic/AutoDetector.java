package pl.put.poznan.sorting.logic;

import static java.lang.Math.*;

public class AutoDetector {
    int[] data = null;
    int size;
    public AutoDetector(int[] input) {
        data = input;
        size = data.length;
    }

    public Sorter detectSorter() {
        float percent = percentSorted();
        System.out.println(percent);
        if(percent > 0.8 && size < 100) return new BubbleSort();
        if(size < 100) return new SelectionSort();
        if(percent > 0.8) return new InsertionSort();
        if(size < 500) return new MergeSort();
        if(percent > 0.5) return new QuickSort();
        return new HeapSort();
    }

    private int standardDeviation() {
        float mean = 0, sd = 0;
        for (int i : data) mean += i;
        mean = mean/data.length;
        for (int i : data) sd += pow((mean - i), 2);
        return (int) round(sqrt(sd/data.length));

    }

    public float percentSorted() {
        int sortedElements = 0;
        int lastSortedIdx = 0;
        for(int i = 1; i < data.length; i++) {
            if(data[i] > data[lastSortedIdx] /*&& data[i] - standardDeviation() < data[lastSortedIdx]*/ ) {
                sortedElements++;
                lastSortedIdx = i;
            }
        }
        System.out.println(sortedElements);
        return (float) sortedElements/data.length;
    }
}
