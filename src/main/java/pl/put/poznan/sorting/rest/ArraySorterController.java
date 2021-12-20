package pl.put.poznan.sorting.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.app.App;
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

    // Application object
    App app = new App();
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
        return app.getResult(payload);
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
