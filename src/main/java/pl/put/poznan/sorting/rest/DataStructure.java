package pl.put.poznan.sorting.rest;

import java.util.HashMap;

public class DataStructure {
    private int data_int;
    private float data_float;
    private String data_string;
    private HashMap<String, Object> data;

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void setData_float(float data_float) {
        this.data_float = data_float;
    }

    public void setData_int(int data_int) {
        this.data_int = data_int;
    }

    public void setData_string(String data_string) {
        this.data_string = data_string;
    }

    public float getData_float() {
        return data_float;
    }

    public int getData_int() {
        return data_int;
    }

    public String getData_string() {
        return data_string;
    }

    public HashMap<String, Object> getData() {
        return data;
    }
}
