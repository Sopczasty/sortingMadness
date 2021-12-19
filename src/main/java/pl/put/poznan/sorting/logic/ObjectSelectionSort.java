package pl.put.poznan.sorting.logic;

public class ObjectSelectionSort implements ObjectSorter {
    public Sort[] sort(Sort[] input, String type, String direction) {

        // Exception for empty input data
        if (input.length == 0) {
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if (!direction.equals("asc") && !direction.equals("desc")) {
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        // Exception for incorrect sorting attribute
        if (!type.equals("name") && !type.equals("time") && !type.equals("size")) {
            throw new IllegalArgumentException("Input sorting argument is incorrect.");
        }

        int min_idx;
        Sort temp = new Sort("a", 1, 2);

        if (direction.equals("asc")) {
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j].compareSort(input[min_idx], type) < 0)
                        min_idx = j;

                temp.swapObject(input[min_idx]);
                input[min_idx].swapObject(input[i]);
                input[i].swapObject(temp);
            }
        }

        if (direction.equals("desc")) {
            for (int i = 0; i < input.length - 1; i++) {
                min_idx = i;
                for (int j = i + 1; j < input.length; j++)
                    if (input[j].compareSort(input[min_idx], type) > 0)
                        min_idx = j;

                temp.swapObject(input[min_idx]);
                input[min_idx].swapObject(input[i]);
                input[i].swapObject(temp);
            }
        }
        return input;
    }

    public Sort[] sort(Sort[] input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "time", "asc");
        return input;
    }
}
