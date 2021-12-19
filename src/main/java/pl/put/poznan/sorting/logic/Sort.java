package pl.put.poznan.sorting.logic;

public class Sort{
    private String name;
    private double average_time;
    private int optimal_size;

    public Sort(String name, double average_time, int optimal_size){
        super();
        this.name = name;
        if (average_time < 0) throw new IllegalArgumentException("Time cannot be negative");
        this.average_time = average_time;
        if (optimal_size <= 0) throw new IllegalArgumentException("Size has to be greater than 0");
        this.optimal_size = optimal_size;
    }

    public void setSortName(String name){
        this.name = name;
    }

    public String getSortName(){
        return this.name;
    }

    public void setSortAverageTime(double time) {
        if (time < 0) throw new IllegalArgumentException("Time cannot be negative");
        this.average_time = time;
    }

    public double getSortAverageTime(){
        return this.average_time;
    }

    public void setSortOptimalSize(int size){
        if (size <= 0) throw new IllegalArgumentException("Size has to be greater than 0");
        this.optimal_size = size;
    }

    public int getSortOptimalSize(){
        return this.optimal_size;
    }

    public double compareSort(Sort object, String attribute){

        if (attribute.equals("name")){
            String newName = object.getSortName();
            return this.name.compareTo(newName);
        }
        if (attribute.equals("time")){
            double newTime = object.getSortAverageTime();
            return (this.average_time - newTime);
        }
        if (attribute.equals("size")) {
            int newSize = object.getSortOptimalSize();
            return (this.optimal_size - newSize);
        }
        return 0;
    }

    public void swapObject(Sort object){
        this.name = object.getSortName();
        this.average_time = object.getSortAverageTime();
        this.optimal_size = object.getSortOptimalSize();
    }
}
