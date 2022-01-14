package pl.put.poznan.testing.sorting;

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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setTime(double time) {
        if (time < 0) throw new IllegalArgumentException("Time cannot be negative");
        this.average_time = time;
    }

    public double getTime(){
        return this.average_time;
    }

    public void setSize(int size){
        if (size <= 0) throw new IllegalArgumentException("Size has to be greater than 0");
        this.optimal_size = size;
    }

    public int getSize(){
        return this.optimal_size;
    }
}
