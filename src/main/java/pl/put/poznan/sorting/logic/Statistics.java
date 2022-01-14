package pl.put.poznan.sorting.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.Math;
import pl.put.poznan.sorting.app.SortingMadness;

/**
 * Class responsible for collecting sorted array statistics:
 * median, arithmetic mean, square mean, geometric mean, harmonic mean
 */
public class Statistics {

    static Logger logger = LoggerFactory.getLogger(Statistics.class);
    // Value return as a result of statistical operation
    private double result;
    // Sorted array or values
    private int[] sortedArray;
    // size of sorted array
    private  int size;

    /**
     * Class constructor with input data validation
     * @param input array containing data sorted by numeric values
     */
    public Statistics(int[] input){
        this.result = 0;
        if ( input == null || input.length == 0 ){
            logger.error("Input data is empty. Returning.");
            throw new IllegalArgumentException("Input data is empty.");
        }
        this.sortedArray = input;
        this.size = input.length;
    }

    /**
     * Median calculating function
     * @return median value
     */
    public double median(){
        if(this.size % 2 == 0){
            this.result = (this.sortedArray[(this.size / 2) - 1] + this.sortedArray[this.size / 2]) / 2.0;
        }
        else {
            this.result = this.sortedArray[this.size/2];
        }
        return this.result;
    }

    /**
     * Function calculating arithmetic mean
     * @return value of arithmetic mean
     */
    public double mean(){
        this.result = 0;
        for(int element: this.sortedArray){
            this.result += element;
        }
        return (this.result / this.size);
    }

    /**
     * Function calculating root square mean
     * @return value of root square mean
     */
    public double rootSquareMean(){
        this.result = 0;
        for(int element: this.sortedArray){
            this.result += element * element;
        }
        return  Math.sqrt(this.result / this.size);
    }

    /**
     * Function calculating geometric mean
     * @return value of geometric mean
     */
    public double geometricMean(){
        this.result = 1;
        for (int element: this.sortedArray){
            this.result *= element;
        }
        if ( this.result < 0){
            logger.error("NaN value. Returning.");
            throw new ArithmeticException("Value under root cannot be negative!");
        }
        this.result = Math.pow(this.result, 1.0 / this.size);
        return this.result;
    }

    /**
     * Function calculating harmonic mean
     * @return value of harmonic mean
     */
    public double harmonicMean(){
        this.result = 0;
        for (int element: this.sortedArray){
            this.result += Math.pow(element, -1.0);
        }
        this.result = Math.pow((this.result / this.size), -1.0);
        return  this.result;
    }
}
