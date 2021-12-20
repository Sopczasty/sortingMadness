package pl.put.poznan.sorting.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.logic.Sorter;
import pl.put.poznan.sorting.logic.SortingWrapper;
import pl.put.poznan.sorting.logic.Timer;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class. Invokes all sorters and returns sorted data.
 */
public class App {
    private static SortingWrapper wrapper = new SortingWrapper();
    private static Sorter sorter = null;
    private static Timer timer = new Timer();
    static Logger logger = LoggerFactory.getLogger(App.class);

    /**
     * Check if input data for simple array is correct.
     * It should not have undefined direction and not be empty.
     * @param input input data to check
     * @param direction direction to check
     */
    private static void sanityCheck(int []input, String direction) {
        // Error handling
        if (input.length == 0) {
            logger.error("Input data is empty. Returning.");
            throw new IllegalArgumentException("Input data is empty.");
        }

        if (!(direction.equals("") || direction.equals("asc") || direction.equals("desc"))) {
            logger.error("Input order is incorrect. Throwing exception.");
            throw new IllegalArgumentException("Sorting order is incorrect.");
        }
    }

    /**
     * Check if input data for objects is correct.
     * It should not have undefined direction and not be empty.
     * @param input input data to check
     * @param direction direction to check
     */
    private static void sanityCheck(ArrayList<Object> input, String direction) {
        // Error handling
        if (input.size() == 0) {
            logger.error("Input data is empty. Returning.");
            throw new IllegalArgumentException("Input data is empty.");
        }

        if (!(direction.equals("") || direction.equals("asc") || direction.equals("desc"))) {
            logger.error("Input order is incorrect. Throwing exception.");
            throw new IllegalArgumentException("Sorting order is incorrect.");
        }
    }

    /**
     * Check if input data for REST API is correct.
     * It should not have undefined direction and not be empty.
     * @param payload REST API request
     */
    private static void sanityCheck(Map<String, Object> payload) {
        if (payload.isEmpty()) {
            logger.error("Payload is empty, throwing exception.");
            throw new InvalidParameterException("Payload is empty!");
        }
        if (!payload.containsKey("input")) {
            logger.error("Payload does not have input data.");
            throw new InvalidParameterException("Invalid input data!");
        }
        if (!payload.containsKey("algorithm")) {
            logger.error("No algorithm specified, throwing exception.");
            throw new InvalidParameterException("Invalid sorting algorithm!");
        }
    }

    /**
     * Get sorted array of simple ints.
     * @param input input data to be sorted
     * @param direction direction of the sort (ascending or descending)
     * @param algorithm algorithm name to use
     * @return sorted data
     */
    public int[] getResult(int[] input, String algorithm, String direction) {
        sanityCheck(input, direction);
        logger.debug("Initializing sorter.");
        sorter = wrapper.getSorter(input, algorithm);

        if (sorter == null) {
            logger.error("Incorrect sorting algorithm");
            throw new IllegalArgumentException("Incorrect sorting algorithm!");
        }

        logger.info("Sorting using " + sorter.getName() + " sorter.");

        logger.debug("Starting sorting.");
        timer.startMeasure();
        input = sorter.sort(input, direction);
        timer.stopMeasure();
        logger.info("Data sorted using" + sorter.getName() + " sorter in " + timer.getTimeElapsed() + " ms.");

        return input;
    }

    /**
     * Get sorted array of simple ints if the user has not provided all attributes.
     * @param input input data to sort
     * @param algorithm sorting algorithm
     * @return sorted data
     */
    public int[] getResult(int[] input, String algorithm) {
        logger.debug("Initializing sorter.");
        sorter = wrapper.getSorter(input, algorithm);
        if (sorter == null) {
            logger.error("Incorrect sorting algorithm");
            throw new IllegalArgumentException("Incorrect sorting algorithm!");
        }
        logger.info("Sorting using " + sorter.getName() + " sorter.");
        logger.debug("Starting sorting.");
        timer.startMeasure();
        input = sorter.sort(input, "asc");
        timer.stopMeasure();
        logger.info("Data sorted using" + sorter.getName() + " sorter in " + timer.getTimeElapsed() + " ms.");

        return input;
    }

    /**
     * Get sorted array of simple ints without providing direction nor type.
     * @param input input data to sort
     * @return sorted data
     */
    public int[] getResult(int[] input) {
        logger.debug("Direction and algorithm not provided assuming ascending order. Detecting algorithm automatically.");
        return getResult(input, "auto", "asc");
    }

    /**
     * Get sorted array of objects.
     * @param input input data to sort
     * @param algorithm name of the sorting algorithm to use
     * @param direction direction of the sort
     * @param attribute attribute to sort by
     * @return sorted data
     */
    public ArrayList<Object> getResult(ArrayList<Object> input, String algorithm, String direction, String attribute) {
        sanityCheck(input, direction);
        logger.debug("Initializing sorter.");
        sorter = wrapper.getSorter(input, algorithm);
        logger.info("Sorting using " + sorter.getName() + " sorter.");

        logger.debug("Starting sorting.");
        timer.startMeasure();
        input = sorter.sort(input, direction, attribute);
        timer.stopMeasure();
        logger.info("Data sorted using" + sorter.getName() + " sorter in " + timer.getTimeElapsed() + " ms.");

        return input;
    }

    /**
     * Get sorted array of objects without direction nor type.
     * @param input input data to sort
     * @param algorithm algorithm to use when sorting
     * @param attribute attribute to sort by
     * @return sorted data
     */
    public ArrayList<Object> getResult(ArrayList<Object> input, String algorithm, String attribute) {
        logger.debug("Direction not provided assuming ascending order");
        return getResult(input, algorithm, "asc", attribute);
    }

    /**
     * If no direction and attribute is provided, throw an exception.
     * @param input input data to sort
     * @return exception
     */
    public ArrayList<Object> getResult(ArrayList<Object> input) {
        logger.error("Object attribute to sort by not provided. Throwing exception.");
        throw new IllegalArgumentException("Object attribute type not provided.");
    }

    /**
     * Get sorted data from REST API request
     * @param payload REST API payload
     * @return sorted data
     */
    public HashMap<String, Object> getResult(Map<String, Object> payload) {
        sanityCheck(payload);

        int[] input;
        String order = "asc";
        String algorithm;

        input = Arrays.stream(
                payload.get("input").toString()
                        .replace("[", "")
                        .replace("]", "")
                        .replace(" ", "")
                        .split(",")
        ).mapToInt(Integer::parseInt).toArray();

        logger.debug("Initializing sorter.");
        algorithm = payload.get("algorithm").toString();
        if (payload.containsKey("order")) order = payload.get("order").toString();
        else logger.debug("No sorting order provided. Assuming ascending order.");
        sorter = wrapper.getSorter(input, payload.get("algorithm").toString());

        if (sorter == null) {
            logger.error("No sorter of name " + algorithm + " exists.");
            throw new IllegalArgumentException("Invalid sorting algorithm name!");
        }

        logger.info("Sorting using " + sorter.getName() + " sorter.");
        logger.debug("Starting sorting.");
        timer.startMeasure();
        input = sorter.sort(input, order);
        timer.stopMeasure();
        logger.info("Data sorted using" + sorter.getName() + " sorter in " + timer.getTimeElapsed() + " ms.");

        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("result", input);
        output.put("algorithm", algorithm);
        output.put("time", timer.getTimeElapsed());
        return output;
    }
}
