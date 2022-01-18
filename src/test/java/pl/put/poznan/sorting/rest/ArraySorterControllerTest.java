package pl.put.poznan.sorting.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.put.poznan.sorting.app.App;
import pl.put.poznan.sorting.app.SortingMadness;
import pl.put.poznan.sorting.logic.SortingWrapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for REST API
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArraySorterControllerTest {

    // Data to be sorted
    private Object[] data;
    // Data as int
    private int[] data_int;
    // URL of the API endpoint
    private String url;
    // URI of the API endpoint
    private URI uri;
    // Request to test with
    private HttpEntity<Map<String, Object>> request;

    // Port of the API endpoint
    @LocalServerPort
    private int port;

    // REST API template
    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    public void setup() throws URISyntaxException {
        url = "http://localhost:"+port+"/api/array/sort";
        uri = new URI(url);
        data_int = new int[]{-30, 219, 3, -8, -1, 10, 30, 20, -3, -1231231, 12314, 40, 50, -12, 123, 32, 23};
        data = new Object[data_int.length];
        for(int i = 0; i < data_int.length; i++)
            data[i] = data_int[i];
    }

    /**
     * Test for input request with correct arguments
     */
    @Test
    public void restArraySorted() {
        Map<String, Object> payload = new HashMap<String, Object>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<Map<String, Object>>(payload, headers);
        payload.put("data", data);
        payload.put("algorithm", "quick");
        ResponseEntity<Map> response = restTemplate.postForEntity(
                uri,
                request,
                Map.class
        );
        System.out.println(Arrays.toString(data).replaceAll("[\\[||\\]|| ]", ""));

        String[] algorithms = {"quick"};
        SortingMadness madness = new SortingMadness.PrimitiveBuilder(algorithms).data(data).build();
        Object[] expected = madness.getResult();
        String[] array = new String[expected.length];
        assertTrue(response.hasBody());
        assertNotNull(response.getBody().get("result"));
        for(int i = 0; i < expected.length; i++)
            array[i] = expected[i].toString();
        assertArrayEquals(array, response.getBody().get("result").toString()
                                .replaceAll("[\\[||\\]|| ]", "")
                                .split(",")
        );
    }

    /**
     * Test for input request with incorrect arguments
     */
    @Test
    public void restArraySortedException() {
        Map<String, Object> payload = new HashMap<String, Object>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        payload.put("data", data);
        request = new HttpEntity<Map<String, Object>>(payload, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                uri,
                request,
                Map.class
        );
        assertTrue(response.hasBody());
        assertTrue(response.getBody().get("error").toString().contains("Wrong payload content"));
    }

}
