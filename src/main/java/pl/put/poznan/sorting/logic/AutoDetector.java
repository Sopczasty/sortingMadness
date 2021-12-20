package pl.put.poznan.sorting.logic;

public class AutoDetector {
    private int[] data = null;
    private int size;
    public AutoDetector(int[] input) throws NullPointerException {
        data = input;
        size = data.length;
    }

    public String getType() {
        float percent = percentSorted();
        if(percent > 0.8 && size < 100) return "bubble";
        if(size < 100) return "selection";
        if(percent > 0.8) return "insertion";
        if(size < 500) return "merge";
        if(percent > 0.5) return "quick";
        return "heap";
    }

    private float percentSorted() {
        int sortedElements = 0;
        for(int i = 1; i < size; i++) {
            if(data[i] > data[i-1]) {
                sortedElements++;
                if(i == (int) size/2 + 1)
                    if((float) sortedElements/i < 0.5) return (float) sortedElements/i;
            }
        }
        return (float) sortedElements/size;
    }
}
