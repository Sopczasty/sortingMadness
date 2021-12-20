package pl.put.poznan.sorting.logic;

public class Timer {
    private static long start;
    private static long finish;
    private static long timeElapsed;

    public void startMeasure() {
        start = System.currentTimeMillis();
    }

    public void stopMeasure() {
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
