package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

public interface Sorter {
    public int[] sort(int[] input, String direction, int iterations);
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute, int iterations);
    public String getName();
}

