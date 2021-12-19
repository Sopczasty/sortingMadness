package pl.put.poznan.sorting.logic;

import java.util.ArrayList;

public interface Sorter {

    public int[] sort(int[] input, String direction);
    public int[] sort(int[] input);
    public ArrayList<Object> sort(ArrayList<Object> input, String direction, String attribute);
    public ArrayList<Object> sort(ArrayList<Object> input);
    public String getName();
}
