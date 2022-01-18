package pl.put.poznan.sorting.app;

import pl.put.poznan.sorting.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

/**
 * Main class responsible for sorting objects.
 */
public class SortingMadness {
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);
    // List of sorting algorithms to use
    private final String[] algorithms;
    // Input of primitive objects
    private final Object[] input;
    // Input for complex data structures
    private final ArrayList<Object> objInput;
    // Direction of the sorting
    private final String direction;
    // How many iterations of sorting to run
    private final int iterations;
    // Attribute to sort by for complex data sorting
    private String attribute = "";
    // Sorting wrapper that will get proper sorting algorithm
    private final static SortingWrapper wrapper = new SortingWrapper();
    // Sorter object
    private static Sorter sorter = null;
    // Timer for timing sorts
    private final static Timer timer = new Timer();
    // Measurements for all selected sorting algorithms
    private Map<String, Long> measurements;

    /**
     * Main constructor selecting appropriate methods based on type of data
     * @param builder primitive objects sorting madness builder
     */
    private SortingMadness(PrimitiveBuilder builder) {
        this.algorithms = builder.algorithms;
        this.input = builder.input;
        this.direction = builder.direction;
        this.iterations = builder.iterations;
        this.objInput = null;
    }

    /**
     * Main constructor selecting appropriate methods based on type of data
     * @param builder complex objects sorting madness builder
     */
    private SortingMadness(ObjectBuilder builder) {
        this.algorithms = builder.algorithms;
        this.input = null;
        this.direction = builder.direction;
        this.iterations = builder.iterations;
        this.attribute = builder.attribute;
        this.objInput = builder.objInput;
    }

    public String[] getAlgorithms() { return algorithms; }
    public Object[] getInput() { return input; }
    public ArrayList<Object> getObjInput() { return objInput; }
    public String getDirection() { return direction; }
    public int getIterations() { return iterations; }
    public Map<String, Long> getMeasurements() { return measurements; }


    /**
     * Builder for primitive data types
     */
    public static class PrimitiveBuilder {
        private final String[] algorithms;
        private final Object[] input;
        private String direction = "asc";
        private int iterations = 0;

        /**
         * Main constructor for primitive data builder
         * @param algorithms list of algorithms to sort with
         * @param input primitive data input
         */
        public PrimitiveBuilder(String[] algorithms, Object[] input) {
            this.algorithms = algorithms;
            this.input = input;
        }

        /**
         * Direction setter
         * @param direction direction of the sorting (ascending or descending)
         * @return edited builder
         */
        public PrimitiveBuilder direction(String direction) {
            this.direction = direction;
            return this;
        }

        /**
         * Iteration setter
         * @param iterations how many iterations to run program for
         * @return edited builder
         */
        public PrimitiveBuilder iterations(int iterations) {
            this.iterations = iterations;
            return this;
        }

        /**
         * Main building function
         * @return fully built sorting madness object
         */
        public SortingMadness build() {
            SortingMadness madness = new SortingMadness(this);
            validateInput(madness);
            return madness;
        }

        /**
         * Sanity check for data
         * @param madness fully built sorting madness object
         */
        private void validateInput(SortingMadness madness) {
            if (madness.input == null || madness.input.length == 0) {
                logger.error("Input data is empty. Returning.");
                throw new IllegalArgumentException("Input data is empty.");
            }
            if (!(madness.direction == null) && !(madness.direction.equals("") || madness.direction.equals("asc")
                    || madness.direction.equals("desc"))) {
                logger.error("Input order is incorrect. Throwing exception.");
                throw new IllegalArgumentException("Sorting order is incorrect.");
            }
        }


    }

    /**
     * Builder for complex data types
     */
    public static class ObjectBuilder {
        private final String[] algorithms;
        private final ArrayList<Object> objInput;
        private String direction = "asc";
        private int iterations = 0;
        private String attribute;

        /**
         * Main constructor for primitive data builder
         * @param algorithms list of algorithms to sort with
         * @param objInput complex data input
         */
        public ObjectBuilder(String[] algorithms, ArrayList<Object> objInput) {
            this.algorithms = algorithms;
            this.objInput = objInput;
        }

        /**
         * Direction setter
         * @param direction direction of the sorting (ascending or descending)
         * @return edited builder
         */
        public ObjectBuilder direction(String direction) {
            this.direction = direction;
            return this;
        }

        /**
         * Iteration setter
         * @param iterations how many iterations to run program for
         * @return edited builder
         */
        public ObjectBuilder iterations(int iterations) {
            this.iterations = iterations;
            return this;
        }

        /**
         * Attribute setter
         * @param attribute which attribute to sort by
         * @return edited builder
         */
        public ObjectBuilder attribute(String attribute) {
            this.attribute = attribute;
            return this;
        }

        /**
         * Main building function
         * @return fully built sorting madness object
         */
        public SortingMadness build() {
            SortingMadness madness = new SortingMadness(this);
            validateInput(madness);
            return madness;
        }

        /**
         * Sanity check for data
         * @param madness fully built sorting madness object
         */
        private void validateInput(SortingMadness madness) {
            if (madness.objInput == null || madness.objInput.size() == 0) {
                logger.error("Input data is empty. Returning.");
                throw new IllegalArgumentException("Input data is empty.");
            }
            if (!(madness.direction == null) && !(madness.direction.equals("") || madness.direction.equals("asc")
                    || madness.direction.equals("desc"))) {
                logger.error("Input order is incorrect. Throwing exception.");
                throw new IllegalArgumentException("Sorting order is incorrect.");
            }
        }
    }

    /**
     * Get sorted data for primitive data input
     * @return sorted data
     */
    public Object[] getResult() {
        if (input == null) throw new IllegalArgumentException("No input provided.");
        Object[] result = new Object[input.length];
        measurements = new Hashtable<>();

        for (String algorithm : algorithms) {
            sorter = wrapper.getSorter(input, algorithm);
            if (sorter == null) {
                logger.error("Incorrect sorting algorithm, skipping...");
                continue;
            }
            logger.info("Sorting using " + sorter.getName() + " sorter with " + iterations + " iterations.");
            logger.debug("Starting sorting.");
            timer.startMeasure();
            result = sorter.sort(input, direction, iterations);
            timer.stopMeasure();
            logger.info("Data sorted using" + sorter.getName() + " sorter.");
            measurements.put(sorter.getName(), timer.getTimeElapsed());
        }
        return result;
    }

    /**
     * Get timer for elapsed time
     * @return timer
     */
    public static Timer getTimer() {
        return timer;
    }

    /**
     * Get sorted data for complex data input
     * @return sorted data
     */
    public ArrayList<Object> getObjResult() {
        if (objInput == null) throw new IllegalArgumentException("No input provided.");
        if (this.attribute == null || this.attribute.equals("")) {
            logger.error("No sorting attribute provided. Quitting.");
            throw new IllegalArgumentException("Object attribute to sort by not provided!");
        }

        ArrayList<Object> result = new ArrayList<>();
        measurements = new Hashtable<>();

        for (String algorithm : algorithms) {
            sorter = wrapper.getSorter(objInput, algorithm, this.attribute);
            if (sorter == null) {
                logger.error("Incorrect sorting algorithm, skipping...");
                continue;
            }
            logger.info("Sorting using " + sorter.getName() + " sorter with " + iterations + " iterations.");
            logger.debug("Starting sorting.");
            timer.startMeasure();
            result = sorter.sort(objInput, direction, attribute, iterations);
            timer.stopMeasure();
            logger.info("Data sorted using" + sorter.getName() + " sorter.");
            measurements.put(sorter.getName(), timer.getTimeElapsed());
        }

        return result;
    }
}
