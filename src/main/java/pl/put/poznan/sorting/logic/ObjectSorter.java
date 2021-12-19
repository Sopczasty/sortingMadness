package pl.put.poznan.sorting.logic;

public interface ObjectSorter {
    public Sort[] sort(Sort[] input, String type, String direction);

    Sort[] sort(Sort[] input);
}
