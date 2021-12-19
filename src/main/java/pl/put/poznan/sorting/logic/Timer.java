package pl.put.poznan.sorting.logic;

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

    /**
     * Function calculating time before sorting.
     */
    public void startMeasure() {
        start = System.currentTimeMillis();
    }

    /**
     * Function calculating time after sorting.
     */
    public void stopMeasure() {
        finish = System.currentTimeMillis();
    }

    /**
     * Function returning time elapsed since the last time
     * user started the measure and ended it.
     * @return latest time elapsed between measure start and end
     */
    public long getLastMeasure() {
        timeElapsed = finish - start;
        return timeElapsed;
    }
}
