package pl.put.poznan.sorting.logic;

public class HeapSort implements Sorter {
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i, String direction)
    {
        // Sorting for ascending order
        if(direction.equals("asc")){
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < n && arr[l] > arr[largest])
                largest = l;

            // If right child is larger than largest so far
            if (r < n && arr[r] > arr[largest])
                largest = r;

            // If largest is not root
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, largest, direction);
            }
        }

        // Sorting for descending order
        if(direction.equals("desc")){
            int smallest = i; // Initialize smalles as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is smaller than root
            if (l < n && arr[l] < arr[smallest])
                smallest = l;

            // If right child is smaller than smallest so far
            if (r < n && arr[r] < arr[smallest])
                smallest = r;

            // If smallest is not root
            if (smallest != i) {
                int temp = arr[i];
                arr[i] = arr[smallest];
                arr[smallest] = temp;

                // Recursively heapify the affected sub-tree
                heapify(arr, n, smallest, direction);
            }
        }
    }

    public void heapSort(int arr[], String direction)
    {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i, direction);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0, direction);
        }
    }

    public int[] sort(int[] input, String direction) {

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        int[] temp_arr = input;
        heapSort(temp_arr, direction);
        return temp_arr;
    }

    public int[] sort(int[] input) {
        input = sort(input, "asc");
        return input;
    }
}
