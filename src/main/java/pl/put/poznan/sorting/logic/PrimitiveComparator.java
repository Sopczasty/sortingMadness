package pl.put.poznan.sorting.logic;

/**
 * Utility class for comparing primitive objects between each other.
 */
public class PrimitiveComparator {

    /**
     * Compare two objects between each other
     * @param o1 first object
     * @param o2 second object
     * @return less than 0 if smaller, 0 if equal, more than 0 if greater
     */
    public int compare (Object o1, Object o2) {
        if (o1 instanceof String) {
            return ((String)o1).compareTo((String)o2);
        }
        else if (o1 instanceof Integer) {
            return ((Integer)o1).compareTo((Integer)o2);
        }
        else if (o1 instanceof  Float) {
            return ((Float)o1).compareTo((Float)o2);
        }
        else return 0;
    }
}
