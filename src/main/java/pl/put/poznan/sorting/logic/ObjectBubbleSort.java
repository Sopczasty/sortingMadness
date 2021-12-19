package pl.put.poznan.sorting.logic;

public class ObjectBubbleSort implements ObjectSorter {

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

        Sort temp = new Sort("a", 1, 2);

        if (direction.equals("asc")){
            for(int i = 0; i < input.length - 1; i++) {
                for(int j = 0; j < input.length - i-1; j++ ){
                    if(input[j].compareSort(input[j+1], type) > 0){
                        temp.swapObject(input[j]);
                        input[j].swapObject(input[j+1]);
                        input[j + 1].swapObject(temp);
                    }
                }
            }
        }

        if (direction.equals("desc")){
            for(int i = 0; i < input.length - 1; i++) {
                for(int j = 0; j < input.length - i-1; j++ ){
                    if(input[j].compareSort(input[j+1], type) < 0){
                        temp.swapObject(input[j]);
                        input[j].swapObject(input[j+1]);
                        input[j + 1].swapObject(temp);
                    }
                }
            }
        }

        return input;
    }

    public Sort[] sort(Sort[] input){
        System.out.println("Direction and parameter undefined - assumed ascending order and average time as parameter.");
        input = sort(input, "time", "asc");
        return input;
    }
}
