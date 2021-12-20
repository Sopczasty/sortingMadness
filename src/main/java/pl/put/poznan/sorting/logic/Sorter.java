package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

public interface Sorter {
    public int[] sort(int[] input, String direction);
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute);
    public String getName();
}
