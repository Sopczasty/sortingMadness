package pl.put.poznan.sorting.logic;

public class ObjectHeapSort implements ObjectSorter{
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(Sort arr[], int n, int i,String type, String direction)
    {
        Sort temp = new Sort("a", 1, 2);
        // Sorting for ascending order
        if(direction.equals("asc")){
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < n && arr[l].compareSort(arr[largest], type) > 0)
                largest = l;

            // If right child is larger than largest so far
            if (r < n && arr[r].compareSort(arr[largest],type) > 0)
                largest = r;

            // If largest is not root
            if (largest != i) {
                temp.swapObject(arr[i]);
                arr[i].swapObject(arr[largest]);
                arr[largest].swapObject(temp);

                // Recursively heapify the affected sub-tree
                heapify(arr, n, largest, type, direction);
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            int smallest = i; // Initialize smalles as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is smaller than root
            if (l < n && arr[l].compareSort(arr[smallest], type) < 0 )
                smallest = l;

            // If right child is smaller than smallest so far
            if (r < n && arr[r].compareSort(arr[smallest], type) < 0)
                smallest = r;

            // If smallest is not root
            if (smallest != i) {
                temp.swapObject(arr[i]);
                arr[i].swapObject(arr[smallest]);
                arr[smallest].swapObject(temp);

                // Recursively heapify the affected sub-tree
                heapify(arr, n, smallest, type, direction);
            }
        }
    }

    public void heapSort(Sort arr[], String type, String direction)
    {

        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, type, direction);

        Sort temp = new Sort("a", 1, 2);
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            temp.swapObject(arr[0]);
            arr[0].swapObject(arr[i]);
            arr[i].swapObject(temp);

            // call max heapify on the reduced heap
            heapify(arr, i, 0, type, direction);
        }
    }

    public Sort[] sort(Sort[] input, String type, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        // Exception for incorrect sorting attribute
        if (!type.equals("name") && !type.equals("time") && !type.equals("size")) {
            throw new IllegalArgumentException("Input sorting argument is incorrect.");
        }

        Sort[] temp_arr = input;
        heapSort(temp_arr, type, direction);
        return temp_arr;
    }

    public Sort[] sort(Sort[] input) {
        System.out.println("Direction undefined - assumed ascending order.");
        input = sort(input, "time", "asc");
        return input;
    }
}
