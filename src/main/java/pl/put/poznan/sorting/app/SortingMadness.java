package pl.put.poznan.sorting.app;

import pl.put.poznan.sorting.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;

/**
 * Main class responsible for sorting objects.
 * Prints out initial data and after sorting
 * prints sorted data in specified order.
 */

public class SortingMadness {
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);

    public static void main(String[] args) {
        logger.info("Application loaded.");
        App app = new App();
        // app.getResult(input);
    }
}
