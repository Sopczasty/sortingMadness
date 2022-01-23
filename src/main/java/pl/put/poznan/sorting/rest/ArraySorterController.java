package pl.put.poznan.sorting.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.app.App;
import pl.put.poznan.sorting.app.SortingMadness;
import pl.put.poznan.sorting.logic.Sorter;
import pl.put.poznan.sorting.logic.SortingWrapper;
import pl.put.poznan.sorting.logic.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.security.InvalidParameterException;
import java.util.*;

/**
 * REST API Controller for simple array input
 */
@RestController
@RequestMapping("/api")
public class ArraySorterController {

    // Logger
    static Logger logger = LoggerFactory.getLogger(ArraySorterController.class);

    /*
    curl
        -H "Accept: application/json"
        -H "Content-type: application/json" \ -X POST
        -d '{"sort":"merge", "array":[1,3,4,2,8,3,2,3,54,565,4,2,1,3,4,5,3,2,3,5,1,23,3456,455,234,3456,234,345,456,345,234,123,3245,4567,456,234,346,234234,73245]}'
        localhost:8080/api/array
    */
    /**
     * API endpoint returning sorted array using specified method in specified order.
     * @param payload user data
     * @return object with sorted array and execution time
     * @throws InvalidParameterException if the input is in incorrect format
     */
    @RequestMapping(value = "/sort", method=RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSortedArray(@RequestBody Map<String, Object> payload)
            throws InvalidParameterException {

        logger.info("Received new request.");

        int[] input;
        String direction = "asc";
        String algorithm;
        int iterations = 0;

        input = Arrays.stream(
                payload.get("input").toString()
                        .replaceAll("[\\[||\\]|| ]", "")
                        .split(",")
        ).mapToInt(Integer::parseInt).toArray();

        logger.debug("Initializing sorter.");
        if (!payload.containsKey("algorithm")) {
            logger.error("Sorting algorithm not provided. Quitting.");
            throw new InvalidParameterException("Sorting algorithm not provided!");
        }
        algorithm = payload.get("algorithm").toString();
        if (payload.containsKey("order")) direction = payload.get("order").toString();

        String[] algorithms = {algorithm};
        /*SortingMadness madness = new SortingMadness.PrimitiveBuilder(algorithms, input)
                .direction(direction)
                .iterations(iterations)
                .build();*/
        int[] result = new int[0];

        HashMap<String, Object> output = new HashMap<String, Object>();
        output.put("result", result);
        output.put("algorithm", algorithm);
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
