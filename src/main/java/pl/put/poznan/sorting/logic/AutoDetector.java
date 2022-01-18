package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

/**
 * Class for detecting optimal algorithm for the specific dataset
 */
public class AutoDetector {
    private final String type;
    private final int size;
    
    /**
     * Constructor for array
     * @param input array of primitive objects
     */
    public AutoDetector(Object[] input) {
        size = input.length;
        type = selectSort(input);
    }

    /**
     * Constructor for data structure
     * @param input array of primitive objects
     * @param attribute variable name to sort with
     */
    public AutoDetector(ArrayList<Object> input, String attribute) {
        size = input.size();
        type = selectSort(input, attribute);
    }

    /**
     * Method for getting the name of algorithm for array
     * @param input array of primitive objects
     * @return a type of algorithm dependent on size and percent sorted of array
     */
    public String selectSort(Object[] input) {
        float percent = percentSorted(input);
        if(percent > 0.8 && size < 100) return "bubble";
        if(size < 100) return "selection";
        if(percent > 0.8) return "insertion";
        if(size < 500) return "merge";
        if(percent > 0.5) return "quick";
        return "heap";
    }
    /**
     * Method for getting the name of algorithm for data structures
     * @param input array of objects
     * @param attribute attribute to sort by
     * @return a type of algorithm dependent on size and percent sorted of data structure
     */
    public String selectSort(ArrayList<Object> input, String attribute) {
        float percent = percentSorted(input, attribute);
        if(percent > 0.8 && size < 100) return "bubble";
        if(size < 100) return "selection";
        if(percent > 0.8) return "insertion";
        if(size < 500) return "merge";
        if(percent > 0.5) return "quick";
        return "heap";
    }

    /**
     * Calculate estimate of how sorted is the array
     * @param array array of primitive objects
     * @return percentage <0..1> of how sorted is the array
     */
    private float percentSorted(Object[] array) {
        int sortedElements = 0;
        PrimitiveComparator comp = new PrimitiveComparator();
        for(int i = 1; i < size; i++) {
            if(comp.compare(array[i], array[i-1]) > 0) {
                sortedElements++;
                if(i == (int) size/2 + 1)
                    if((float) sortedElements/i < 0.5) return (float) sortedElements/i;
            }
        }
        return (float) sortedElements/size;
    }

    /**
     * Calculate estimate of how sorted is the data structure
     * @param input array of objects
     * @param attribute attribute to sort by
     * @return percentage <0..1> of how sorted is the data structure
     */
    private float percentSorted(ArrayList<Object> input, String attribute) {
        int sortedElements = 0;
        for(int i = 1; i < size; i++) {
            if(new ObjectComparator(attribute).compare(input.get(i-1), input.get(i)) > 0) {
                sortedElements++;
                if(i == (int) size/2 + 1)
                    if((float) sortedElements/i < 0.5) return (float) sortedElements/i;
            }
        }
        return (float) sortedElements/size;
    }

    public String getType() {
        return type;
    }
}
