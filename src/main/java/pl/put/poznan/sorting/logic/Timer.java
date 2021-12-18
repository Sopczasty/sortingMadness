package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.app.SortingMadness;

public class Timer {
    private static long start;
    private static long finish;
    private static long timeElapsed;
    static Logger logger = LoggerFactory.getLogger(SortingMadness.class);

    public void startMeasure() {
        logger.debug("Starting timer");
        start = System.currentTimeMillis();
    }

    public void stopMeasure() {
        logger.debug("Ending timer");
        finish = System.currentTimeMillis();
    }

    public long getLastMeasure() {
        timeElapsed = finish - start;
        return timeElapsed;
    }
}
