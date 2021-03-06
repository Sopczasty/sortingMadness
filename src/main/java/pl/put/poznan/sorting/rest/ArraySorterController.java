package pl.put.poznan.sorting.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.app.SortingMadness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.security.InvalidParameterException;
import java.util.*;

/**
 * REST API Controller for simple array input
 */
@RestController
@RequestMapping(value="/api/array")
public class ArraySorterController {

    // Logger
    static Logger logger = LoggerFactory.getLogger(ArraySorterController.class);

    /*
    curl localhost:8080/api/array/sort -X POST -d '{"algorithm": ["merge", "quick"],"direction": "asc","iterations": 0,"data": [1,3,4,2,8,3,2,3,54,565,4,2,1,3,4,5,3,2,3,5,1,23,3456,455,234,3456,234,345,456,345,234,123,3245,4567,456,234,346,234234,73245]}' -H "Content-type: application/json"
    */
    /**
     * API endpoint returning sorted array using specified method in specified order.
     * @param payload user data
     * @return object with sorted array and execution time
     * @throws InvalidParameterException if the input is in incorrect format
     */
    @PostMapping(path = "/sort", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Map<String, Object> getSortedArray(@RequestBody Map<String, Object> payload)
            throws InvalidParameterException {

        logger.info("Received new request.");

        Object[] input;
        String direction = "asc";
        String[] algorithms;
        int iterations = 0;

        logger.debug("Initializing sorter.");
        if (!payload.containsKey("algorithm")) {
            logger.error("Sorting algorithm not provided. Quitting.");
            throw new InvalidParameterException("Sorting algorithm not provided!");
        }
        algorithms = payload.get("algorithm").toString()
                .replaceAll("[\\[||\\]|| ]", "")
                .split(",");
        if (payload.containsKey("direction")) direction = payload.get("direction").toString();
        if (payload.containsKey("iterations")) iterations = (Integer) payload.get("iterations");

        SortingMadness madness = new SortingMadness.PrimitiveBuilder(algorithms)
                .convertData(payload.get("data").toString()
                        .replaceAll("[\\[||\\]|| ]", ""))
                .direction(direction)
                .iterations(iterations)
                .build();

        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("result", madness.getResult());
        output.put("elapsed", madness.getMeasurements());
        Map<String, Object> stat = madness.getStatistics();
        if(stat != null)
            output.put("statistics", stat);
        return output;
    }

    /**
     * Returns error information if the input format is incorrect.
     * @return response object containing error
     */
    @ExceptionHandler({InvalidParameterException.class})
    public ResponseEntity invalidParameterExceptionHandler() {
        Map<String, Object> response = new HashMap<String, Object>();
        logger.debug("Wrong payload content. Returning response entity.");
        response.put("error", "Wrong payload content");
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }
}
