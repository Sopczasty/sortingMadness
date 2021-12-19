package pl.put.poznan.sorting.logic;

import java.util.Comparator;

/**
* Class used for comparing objects between each other.
*/
public class ObjectComparator implements Comparator<Object> {

    private String getter;

    /**
    * Function returning function field getter.
    * @param field field of class to fetch
    */
    public ObjectComparator(String field) {
        this.getter = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    /**
    * Function comparing two objects using compareTo() method.
    * @param o1 first object
    * @param o2 second object
    * @return metric how first object is different from the second
    */
    public int compare(Object o1, Object o2) {
        try {
            if (o1 != null && o2 != null) {
                o1 = o1.getClass().getMethod(getter, new Class[0]).invoke(o1, new Object[0]);
                o2 = o2.getClass().getMethod(getter, new Class[0]).invoke(o2, new Object[0]);
            }
        } catch (Exception e) {
            // If this exception occurs, then it is usually a fault of the developer.
            throw new RuntimeException("Cannot compare objects - getter do not exist", e);
        }

        return (o1 == null) ? -1 : ((o2 == null) ? 1 : ((Comparable<Object>) o1).compareTo(o2));
    }
}
