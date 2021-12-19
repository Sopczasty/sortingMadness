package pl.put.poznan.sorting.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.Sorter;
import pl.put.poznan.sorting.logic.SortingWrapper;
import pl.put.poznan.sorting.logic.Timer;


import java.security.InvalidParameterException;
import java.util.*;

/**
 * REST API Controller for simple array input
 */
@RestController
@RequestMapping("/api")
public class ArraySorterController {

    // Algorithm type
    private static String algorithm;
    // Timer instance measuring sorting time
    private static Timer timer;
    // Input array to be sorted
    private static int[] input;
    // Output array
    private static HashMap<String, Object> output;
    // Wrapper returning correct sorting algorithm
    private static SortingWrapper wrapper;
    // Sorting algorithm instance
    private static Sorter sorter;

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
    @RequestMapping(value = "/array", method=RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSortedArray(@RequestBody Map<String, Object> payload)
    throws InvalidParameterException {
        if (!payload.isEmpty()) {
            String direction;
            if(payload.containsKey("sort"))
                algorithm = payload.get("sort").toString();
            else throw new InvalidParameterException();
            if(payload.containsKey("array")) {
                input = Arrays.stream(
                        payload.get("array").toString()
                                .replace("[", "")
                                .replace("]", "")
                                .replace(" ", "")
                                .split(",")
                ).mapToInt(Integer::parseInt).toArray();
            } else throw new InvalidParameterException();
            if(payload.containsKey("order"))
                direction = payload.get("order").toString();
            else direction = "asc";
            timer = new Timer();
            wrapper = new SortingWrapper();
            sorter = wrapper.getSorter(algorithm);

            timer.startMeasure();
            input = sorter.sort(input, direction);
            timer.stopMeasure();

            output = new HashMap<String, Object>();
            output.put("array", input);
            output.put("sort", algorithm);
            output.put("time", timer.getLastMeasure());
            return output;
        } else throw new InvalidParameterException();
    }

    /**
     * Returns error information if the input format is incorrect.
     * @return response object containing error
     */
    @ExceptionHandler({InvalidParameterException.class})
    public ResponseEntity invalidParameterExceptionHandler() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("error", "Wrong payload content");
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }
}
