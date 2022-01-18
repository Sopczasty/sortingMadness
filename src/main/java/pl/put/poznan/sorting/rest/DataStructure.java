package pl.put.poznan.sorting.rest;

import java.util.HashMap;

/**
 * Class for Data Structure sorting | REST API
 */
public class DataStructure {
    private Object object;
    private HashMap<String, Object> data;

    public void setObject(Object object) {
        this.object = object;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public Object getObject() {
        return object;
    }

    public HashMap<String, Object> getData() {
        return data;
    }
}
