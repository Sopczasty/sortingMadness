package pl.put.poznan.sorting.logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Class for detecting optimal algorithm for the specific dataset
 */
public class AutoDetector {
    private int[] array = null;
    private ArrayList<Object> datastructure = null;
    private int size;
    private String type;
    private String attribute;

    /**
     * Constructor for array
     * @param input array of integers
     * @throws NullPointerException
     */
    public AutoDetector(int[] input) {
        array = input;
        size = input.length;
        type = "Array";
    }
    public AutoDetector(ArrayList<Object> input, String attribute) {
        datastructure = input;
        size = input.size();
        type = "DataStructure";
        this.attribute = attribute;
    }

    /**
     *
     * @return a type of algorithm dependent on size and percent sorted
     */
    public String getType() {
        float percent;
        if(type.equals("Array")) percent = percentSortedArray();
        else percent = percentSortedDataStructure();
        if(percent > 0.8 && size < 100) return "bubble";
        if(size < 100) return "selection";
        if(percent > 0.8) return "insertion";
        if(size < 500) return "merge";
        if(percent > 0.5) return "quick";
        return "heap";
    }

    /**
     *
     * @return percentage <0..1> of how much is the array sorted
     */
    private float percentSortedArray() {
        int sortedElements = 0;
        for(int i = 1; i < size; i++) {
            if(array[i] > array[i-1]) {
                sortedElements++;
                if(i == (int) size/2 + 1)
                    if((float) sortedElements/i < 0.5) return (float) sortedElements/i;
            }
        }
        return (float) sortedElements/size;
    }

    /**
     *
     * @return percentage <0..1> of how much is the data structure sorted
     */
    private float percentSortedDataStructure() {
        int sortedElements = 0;
        for(int i = 1; i < size; i++) {
            if(new ObjectComparator(attribute).compare(datastructure.get(i-1), datastructure.get(i)) > 0) {
                sortedElements++;
                if(i == (int) size/2 + 1)
                    if((float) sortedElements/i < 0.5) return (float) sortedElements/i;
            }
        }
        return (float) sortedElements/size;
    }
}
