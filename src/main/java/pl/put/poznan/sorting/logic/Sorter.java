package pl.put.poznan.sorting.logic;

public interface Sorter {

    public int[] sort(int[] input, String direction);
    public int[] sort(int[] input);
    public String getName();
}
