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

    public long getLastMeasure() {
        timeElapsed = finish - start;
        return timeElapsed;
    }
}
