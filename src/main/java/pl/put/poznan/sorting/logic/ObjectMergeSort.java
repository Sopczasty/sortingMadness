package pl.put.poznan.sorting.logic;

public class ObjectMergeSort implements ObjectSorter{
    static void merge(
            Sort[] a, Sort[] l, Sort[] r, int left, int right, String type, String direction) {

        int i = 0, j = 0, k = 0;

        // Sorting for ascending order
        if(direction.equals("asc")){
            while (i < left && j < right) {
                if (l[i].compareSort(r[j], type) <= 0) {
                    a[k++].swapObject(l[i++]);
                }
                else {
                    a[k++].swapObject(r[j++]);
                }
            }
            while (i < left) {
                a[k++].swapObject(l[i++]);
            }
            while (j < right) {
                a[k++].swapObject(r[j++]);
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")){

            while (i < left && j < right) {
                if (l[i].compareSort(r[j], type) >= 0) {
                    a[k++].swapObject(l[i++]);
                }
                else {
                    a[k++].swapObject(r[j++]);
                }
            }
            while (i < left) {
                a[k++].swapObject(l[i++]);
            }
            while (j < right) {
                a[k++].swapObject(r[j++]);
            }
        }
    }

    public static void mergeSort(Sort[] a, int n, String type, String direction) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Sort[] l = new Sort[mid];
        Sort[] r = new Sort[n - mid];



        for (int i = 0; i < mid; i++) {
            l[i] = new Sort("a", 1, 2);
            l[i].swapObject(a[i]);
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = new Sort("a", 1, 2);
            r[i - mid].swapObject(a[i]);
        }
        mergeSort(l, mid, type, direction);
        mergeSort(r, n - mid, type, direction);

        merge(a, l, r, mid, n - mid, type, direction);
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

        Sort[] temp_input = input;
        mergeSort(temp_input, temp_input.length, type, direction);
        return temp_input;
    }


    public Sort[] sort(Sort[] input) {
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "time", "asc");
        return input;
    }
}
