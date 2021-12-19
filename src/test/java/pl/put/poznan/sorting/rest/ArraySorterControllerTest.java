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
import pl.put.poznan.sorting.logic.SortingWrapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
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
    private int[] data;
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
        url = "http://localhost:"+port+"/api/array";
        uri = new URI(url);
        data = new int[]{-30, 219, 3, -8, -1, 10, 30, 20, -3, -1231231, 12314, 40, 50, -12, 123, 32, 23};
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
        payload.put("array", data);
        payload.put("sort", "quick");
        ResponseEntity<Map> response = restTemplate.postForEntity(
                uri,
                request,
                Map.class
        );
        assertTrue(response.hasBody());
        assertNotNull(response.getBody().get("array"));
        assertArrayEquals(
                Arrays.stream(
                        response.getBody().get("array").toString()
                                .replace("[", "")
                                .replace("]", "")
                                .replace(" ", "")
                                .split(",")
                ).mapToInt(Integer::parseInt).toArray(),
                new SortingWrapper().getSorter("quick").sort(data)
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
        payload.put("array", data);
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
