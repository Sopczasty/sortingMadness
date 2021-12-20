package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.app.SortingMadness;

/**
 * Timer used for measuring performance of every sorting algorithm.
 */
public class Timer {

    // Time before sorting
    private static long start;
    // Time after sorting
    private static long finish;
    // Time elapsed during sorting
    private static long timeElapsed;
    // Logger
    static Logger logger = LoggerFactory.getLogger(Timer.class);

    /**
     * Function calculating time before sorting.
     */
    public void startMeasure() {
        logger.debug("Starting timer.");
        start = System.currentTimeMillis();
    }

    /**
     * Function calculating time after sorting.
     */
    public void stopMeasure() {
        logger.debug("Ending timer.");
        finish = System.currentTimeMillis();
    }

    /**
     * Function returning time elapsed since the last time
     * user started the measure and ended it.
     * @return latest time elapsed between measure start and end
     */
    public long getTimeElapsed() {
        timeElapsed = finish - start;
        return timeElapsed;
    }
}
