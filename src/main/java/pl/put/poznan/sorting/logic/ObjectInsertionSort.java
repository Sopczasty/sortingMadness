package pl.put.poznan.sorting.logic;

public class ObjectInsertionSort implements ObjectSorter {
    public Sort[] sort(Sort[] input, String type, String direction){

        // Exception for empty input data
        if(input.length == 0){
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Exception for incorrect order
        if(!direction.equals("asc") && !direction.equals("desc")){
            throw new IllegalArgumentException("Input order is incorrect.");
        }

        // Exception for incorrect sorting attribute
        if(!type.equals("name") && !type.equals("time") && !type.equals("size")){
            throw new IllegalArgumentException("Input sorting argument is incorrect.");
        }

        Sort temp = new Sort("a", 1, 2);
        int j;

        //Sorting for ascending order
        if(direction.equals("asc")){
            for(int i = 1; i < input.length; i++){
                temp.swapObject(input[i]);
                j = i - 1;

                while (j >= 0 && input[j].compareSort(temp, type) > 0){
                    input[j + 1].swapObject(input[j]);
                    j--;
                }
                input[j + 1].swapObject(temp);

            }
        }

        //Sorting for descending order
        if(direction.equals("desc")){
            for(int i = 1; i < input.length; i++){
                temp.swapObject(input[i]);
                j = i - 1;

                while (j >= 0 && input[j].compareSort(temp, type) < 0){
                    input[j + 1].swapObject(input[j]);
                    j--;
                }
                input[j + 1].swapObject(temp);

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

