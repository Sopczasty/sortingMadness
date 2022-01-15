package pl.put.poznan.sorting.logic;

/**
 * Object that contains variables for sorting
 */
public class SortObject {
    //for int variables
    private int intVariable;
    //for string variables
    private String stringVariable;
    //for double variables
    private double doubleVariable;

    /**
     * Class constructor with integer data
     * @param variable int variable from input
     */
    public SortObject(int variable){
        this.intVariable = variable;
    }

    /**
     * Class constructor with String data
     * @param variable String variable from input
     */
    public SortObject(String variable){
        this.stringVariable = variable;
    }

    /**
     * Class constructor with double data
     * @param variable double variable from input
     */
    public SortObject(double variable){
        this.doubleVariable = variable;
    }

    public void setIntVariable(int intVariable) {
        this.intVariable = intVariable;
    }

    public int getIntVariable(){
        return this.intVariable;
    }

    public void setDoubleVariable(double doubleVariable) {
        this.doubleVariable = doubleVariable;
    }

    public double getDoubleVariable() {
        return this.doubleVariable;
    }

    public void setStringVariable(String stringVariable) {
        this.stringVariable = stringVariable;
    }

    public String getStringVariable() {
        return this.stringVariable;
    }
}