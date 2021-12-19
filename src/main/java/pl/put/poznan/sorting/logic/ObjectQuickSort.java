package pl.put.poznan.sorting.logic;

public class ObjectQuickSort implements ObjectSorter{
    static void swap(Sort[] arr, int i, int j) {
        Sort temp = new Sort("a", 1, 2);
        temp.swapObject(arr[i]);
        arr[i].swapObject(arr[j]);
        arr[j].swapObject(temp);
    }

    static int partition(Sort[] arr, int low, int high, String type, String direction) {
        Sort pivot = new Sort("a", 1, 2);
        pivot.swapObject(arr[high]);
        int i = (low - 1);

        // Sorting for ascending order
        if(direction.equals("asc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j].compareSort(pivot, type) < 0)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            for(int j = low; j <= high - 1; j++)
            {
                if (arr[j].compareSort(pivot, type) > 0)
                {
                    i++;
                    swap(arr, i, j);
                }
            }
            swap(arr, i + 1, high);
        }
        return (i + 1);
    }

    static Sort[] quickSort(Sort[] input, int low, int high,String type, String direction) {
        int pi;
        if (low < high) {
            pi = partition(input, low, high, type, direction);
            quickSort(input, low, pi - 1, type, direction);
            quickSort(input, pi + 1, high, type, direction);
        }
        return input;
    }

    public Sort[] sort(Sort[] input, String type, String direction) {
        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if (!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        // Exception for incorrect sorting attribute
        if(!type.equals("name") && !type.equals("time") && !type.equals("size")){
            throw new IllegalArgumentException("Input sorting argument is incorrect.");
        }
        input = quickSort(input, 0, input.length - 1, type, direction);
        return input;
    }


    public Sort[] sort(Sort[] input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "time", "asc");
        return input;
    }
}
