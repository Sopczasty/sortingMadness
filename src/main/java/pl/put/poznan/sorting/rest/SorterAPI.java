package pl.put.poznan.sorting.rest;

//Rest imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main API endpoint used for sortingMadness app.
 */
@SpringBootApplication
public class SorterAPI {
    /**
     * Function starting the REST API
     * @param args arguments to pass to spring app
     */
    public static void main(String[] args) { SpringApplication.run(SorterAPI.class, args); }
}
