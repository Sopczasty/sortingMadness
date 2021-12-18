package pl.put.poznan.sorting.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.Sorter;
import pl.put.poznan.sorting.logic.SortingWrapper;
import pl.put.poznan.sorting.logic.Timer;


import java.security.InvalidParameterException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ArraySorterController {
    private static String algorithm;
    private static Timer timer;
    private static int[] input;
    private static HashMap<String, Object> output;
    private static SortingWrapper wrapper;
    private static Sorter sorter;

    /*
    curl
        -H "Accept: application/json"
        -H "Content-type: application/json" \ -X POST
        -d '{"sort":"merge", "array":[1,3,4,2,8,3,2,3,54,565,4,2,1,3,4,5,3,2,3,5,1,23,3456,455,234,3456,234,345,456,345,234,123,3245,4567,456,234,346,234234,73245]}'
        localhost:8080/api/array
    */
    @RequestMapping(value = "/array", method=RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> getSortedArray(@RequestBody Map<String, Object> payload)
    throws InvalidParameterException {
        if (!payload.isEmpty()) {
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
            timer = new Timer();
            wrapper = new SortingWrapper();
            sorter = wrapper.getSorter(algorithm);

            timer.startMeasure();
            input = sorter.sort(input);
            timer.stopMeasure();

            output = new HashMap<String, Object>();
            output.put("array", input);
            output.put("sort", algorithm);
            output.put("time", timer.getLastMeasure());
            return output;
        } else throw new InvalidParameterException();
    }

    @ExceptionHandler({InvalidParameterException.class})
    public ResponseEntity invalidParameterExceptionHandler() {
        return new ResponseEntity<>("Wrong payload content\n", HttpStatus.EXPECTATION_FAILED);
    }
}

