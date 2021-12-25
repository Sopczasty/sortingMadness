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
 * Main application class with GUI.
 */
public class App {
    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        int[] data = {1, 10, 4, 3, 51, 21, 123, 54, 32, 11};
        String[] algorithms = {"auto"};
        logger.info("Application loaded.");
        SortingMadness madness = new SortingMadness.PrimitiveBuilder(algorithms, data).direction("desc").build();
        data = madness.getResult();
        System.out.println("Sorted data:");
        for(int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println("");
    }
}
