package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.Math;
import java.util.Hashtable;
import java.util.Map;

/**
 * Class responsible for collecting sorted array statistics:
 * median, arithmetic mean, square mean, geometric mean, harmonic mean
 */
public class Statistics {

    static Logger logger = LoggerFactory.getLogger(Statistics.class);
    // Value return as a result of statistical operation
    private Map<String, Object> result;
    // Sorted array or values
    private Object[] sortedArray;
    // size of sorted array
    private int size;

    /**
     * Class constructor with input data validation
     */
    public Statistics(){
        this.result = new Hashtable<>();
        this.sortedArray = null;
        this.size = 0;
    }

    /**
     * Median calculating function
     */
    public void median(){
        double value;
        if(this.size % 2 == 0){
            value = ( Double.parseDouble(this.sortedArray[this.size / 2 - 1].toString())  + Double.parseDouble(this.sortedArray[this.size / 2].toString())) / 2.0;
        }
        else {
            value = Double.parseDouble(this.sortedArray[this.size/2].toString());
        }
        this.result.put("Median", Math.round(value * 100.0) / 100.0);
    }

    /**
     * Function calculating arithmetic mean
     */
    public void mean(){
        double value = 0;
        for(Object element: this.sortedArray){
            value += Double.parseDouble(element.toString());
        }
        value = (value / this.size);
        this.result.put("Mean", Math.round(value * 100.0) / 100.0);
    }

    /**
     * Function calculating root square mean
     */
    public void rootSquareMean(){
        double value = 0;
        for(Object element: this.sortedArray){
            value += Double.parseDouble( element.toString()) * Double.parseDouble(element.toString());
        }
        value  = Math.sqrt(value / this.size);
        this.result.put("Root square mean", Math.round(value * 100.0) / 100.0);
    }

    /**
     * Function calculating geometric mean
     */
    public void geometricMean(){
        double value = 1;
        for (Object element: this.sortedArray){
            value *= Double.parseDouble(element.toString());
        }
        if ( value < 0){
            logger.error("NaN value. Returning.");
            throw new ArithmeticException("Value under root cannot be negative!");
        }
        value = Math.pow(value, 1.0 / this.size);
        this.result.put("Geometric mean", Math.round(value * 100.0) / 100.0);
    }

    /**
     * Function calculating harmonic mean
     */
    public void harmonicMean(){
        double value = 0;
        for (Object element: this.sortedArray){
            value += Math.pow( Double.parseDouble(element.toString()), -1.0);
        }
        value = Math.pow((value / this.size), -1.0);
        this.result.put("Harmonic mean", Math.round(value * 100.0) / 100.0);
    }

    /**
     * Function to validate input data type
     * @param input array of object from which statistics will be gathered
     * @return result of data validation 1 - data can be collected,  0 - otherwise
     */
    public boolean validateForStatistics(Object[] input){
        if ( input == null || input.length == 0 ){
            logger.error("Input data is empty. Returning.");
            throw new IllegalArgumentException("Input data is empty.");
        }

        if (input[0] instanceof Number){
            this.size = input.length;
            this.sortedArray = input;
            return true;
        }
        logger.error("Input data is not numeric. Returning.");
        return false;
    }

    /**
     * Function aggregating all types of statistics
     */
    public void calculateStatistics(){
        for (Object element: this.sortedArray){
            if( Double.parseDouble(element.toString()) < 0){
                median();
                mean();
                rootSquareMean();
                this.result.put("Geometric mean", -1.0);
                harmonicMean();
                return;
            }
        }
        median();
        mean();
        rootSquareMean();
        geometricMean();
        harmonicMean();
    }

    /**
     * Getter for statistics
     * @return Map containing statistics' names and it's values
     */
    public Map<String, Object> getStatistics(){return result; }
}
