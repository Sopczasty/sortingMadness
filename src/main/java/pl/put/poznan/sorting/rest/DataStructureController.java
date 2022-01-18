package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.app.App;
import pl.put.poznan.sorting.app.SortingMadness;
import pl.put.poznan.sorting.logic.Sorter;
import pl.put.poznan.sorting.logic.SortingWrapper;
import pl.put.poznan.sorting.logic.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * REST API Controller for simple array input
 */
@RestController
@RequestMapping(value="/api/datastructure")
public class DataStructureController {

    // Logger
    static Logger logger = LoggerFactory.getLogger(DataStructureController.class);
    // Data Structure object
    private ArrayList<Object> ds;

    /*
        curl localhost:8080/api/datastructure/sort -X POST -d '{"algorithm": ["merge", "heap"],"direction": "asc","iterations": 0,"key":"n3","data": [{"some_name": "va","name2": "123","n3": "13.3"},{"some_name": "val2","name2": "123","n3": "12.3"}]}' -H "Content-type: application/json"
    */
    /**
     * API endpoint returning sorted data structure using specified method in specified order.
     * @param payload user data
     * @return object with sorted data structure and execution time
     * @throws InvalidParameterException if the input is in incorrect format
     */
    @PostMapping(path = "/sort", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Map<String, Object> getSortedDataStructure(@RequestBody Map<String, Object> payload)
            throws InvalidParameterException, JsonProcessingException {
        String direction = "asc";
        String[] algorithms;
        String key;
        String attribute;
        int iterations = 0;

        logger.debug("Initializing sorter.");
        if (!payload.containsKey("algorithm")) {
            logger.error("Sorting algorithm not provided. Quitting.");
            throw new InvalidParameterException("Sorting algorithm not provided!");
        }

        if (!payload.containsKey("key")) {
            logger.error("Key to sort with not provided. Quitting.");
            throw new InvalidParameterException("Key not provided!");
        }

        algorithms = payload.get("algorithm").toString()
                .replaceAll("[\\[||\\]|| ]", "")
                .split(",");
        key = payload.get("key").toString();
        if (payload.containsKey("direction")) direction = payload.get("direction").toString();
        if (payload.containsKey("iterations")) iterations = (Integer) payload.get("iterations");

        ArrayList<String> jsonStrings = Arrays.stream(
                payload.get("data").toString()
                        .replaceAll("[\\[||\\]]", "")
                        .split(",")
        ).collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < jsonStrings.size(); i++)
            jsonStrings.set(i, jsonStrings.get(i).trim());

        jsonStrings = reformatToJson(jsonStrings);

        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        ArrayList<HashMap<String, Object>> data = new ArrayList<>();
        for (String json : jsonStrings)
            data.add(new ObjectMapper().readValue(json, typeRef));

        ArrayList<String> types = new ArrayList<>();
        for (HashMap<String, Object> j : data) {
            if (j.containsKey(key))
                types.add(detectType(j.get(key)));
        }

        if (Collections.frequency(types, "String") > 0) ds = createDataStructure(data, key, "String");
        else if (Collections.frequency(types, "Float") > 0) ds = createDataStructure(data, key, "Float");
        else ds = createDataStructure(data, key, "Integer");

        SortingMadness madness = new SortingMadness.ObjectBuilder(algorithms, (ArrayList<Object>) ds)
                .direction(direction)
                .iterations(iterations)
                .attribute("Object")
                .build();

        ArrayList<Object> result = madness.getObjResult();
        ArrayList<DataStructure> processed = new ArrayList<>();
        for (Object o : result) {
            processed.add((DataStructure) o);
        }

        HashMap<String, Object> output = new HashMap<>();
        output.put("result", stringify(processed));
        output.put("elapsed", madness.getMeasurements());

        return output;
    }

    /**
     * creates JSON array from input data.
     * @param input list of DataStructure class
     * @return JSONArray
     */
    private String stringify(ArrayList<DataStructure> input) {
        StringBuilder output = new StringBuilder("[");
        for (DataStructure ds : input) {
            output.append(ds.getData().toString()).append(", ");
        }
        return output.substring(0, output.length() - 2) + "]";
    }

    /**
     * Detects the type of var
     * @param var key object
     * @return string with the type name
     */
    private String detectType(Object var) {
        try {
            Integer.parseInt(var.toString());
        } catch (NumberFormatException ex) {
            try {
                Float.parseFloat(var.toString());
            } catch (NumberFormatException _ex) {
                return "String";
            }
            return "Float";
        }
        return "Integer";
    }

    /**
     * Splits input string with = and replaces it with :. Before it adds quotation marks on strings.
     * @param input HashMap string
     * @return JsonString
     */
    private static String splitAndJsonate(String input) {
        System.out.println(input);
        String[] split = input.split("=", 2);
        if(split[0].length() > 1)
            if(split[0].charAt(0) == '{')
                split[0] = split[0].charAt(0) + "\"" + split[0].substring(1) + "\"";
        else {
            split[0] = "\"" + split[0] + "\"";
        }
        if (split[1].length() > 1)
            if (split[1].charAt(split[1].length() - 1) == '}')
            split[1] = "\"" + split[1].substring(0, split[1].length() - 1) + "\"}";
        else {
            split[1] = "\"" + split[1] + "\"";
        }
        return split[0] + ":" + split[1];
    }

    /**
     * Reformats the ArrayList of Strings into ArrayList of JSON strings.
     * @param input list of hashmap strings
     * @return ArrayList of JSON strings
     * @throws InvalidParameterException when the next element in data structure is not a data structure.
     */
    private static ArrayList<String> reformatToJson(ArrayList<String> input)
        throws InvalidParameterException {
        ArrayList<String> output = new ArrayList<>();
        StringBuilder data = new StringBuilder();
        for (String s : input) {
            s = splitAndJsonate(s);

            if (s.charAt(s.length() - 1) == '}') {
                data.append(s);
                output.add(data.toString());
                data = new StringBuilder();
                continue;
            }
            if(data.toString().equals("") && s.charAt(0) != '{') throw new InvalidParameterException("Invalid input");
            data.append(s);
            data.append(",");
            if (s.charAt(s.length() - 1) == '}') {
                output.add(data.toString());
                data = new StringBuilder();
            }
        }
        return output;
    }

    /**
     * Creates array of DataStructure objects with given data
     * @param data: array of HashMap with data needed to be sorted.
     * @param key: to be sorted with
     * @param type: type of key
     * @return Array of Objects (DataStructure)
     */
    private ArrayList<Object> createDataStructure(ArrayList<HashMap<String, Object>> data, String key, String type) {
        ArrayList<Object> ds = new ArrayList<>();
        for (HashMap<String, Object> json : data) {
            DataStructure ds_element = new DataStructure();
            ds_element.setData(json);
            switch(type) {
                case "Integer":
                    ds_element.setObject(Integer.parseInt(json.get(key).toString()));
                    break;
                case "Float":
                    ds_element.setObject(Float.parseFloat(json.get(key).toString()));
                    break;
                case "String":
                    ds_element.setObject(json.get(key).toString());
                    break;
            }
            ds.add(ds_element);
        }
        return ds;
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
