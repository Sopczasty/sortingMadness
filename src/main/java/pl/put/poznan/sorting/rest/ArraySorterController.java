package pl.put.poznan.sorting.rest;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.Sorter;
import pl.put.poznan.sorting.logic.SortingWrapper;

import java.util.Arrays;

@RestController
@RequestMapping("/api/array")
public class ArraySorterController {
    @RequestMapping(method = RequestMethod.GET, value="/{sort}/{values}")
    public @ResponseBody int[] getSortedArray(@PathVariable String sort, @PathVariable String values) {
        int[] input = Arrays.stream(values.split(",")).mapToInt(Integer::parseInt).toArray();
        return new SortingWrapper().getSorter(sort).sort(input);
    }
}
