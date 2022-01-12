package pl.put.poznan.sorting.rest;

import org.hamcrest.MatcherAssert;
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
import pl.put.poznan.sorting.app.SortingMadness;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DataStructureControllerTest {
    // Values for data
    private String[][] values;
    // Data to be sorted
    private HashMap<String, Object>[] data;
    // Key for data to be sorted with
    private String key;
    // Direction of sorting
    private String direction;
    // Amount of iterations
    private int iterations;
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
        values = new String[3][];
        values[0] = new String[]{"va", "val2", "vaeee", "val2","eva123123", "val2", "dasa", "aaal2"};
        values[1] = new String[]{"12323", "12323", "3123", "23", "123", "3", "13", "12"};
        values[2] = new String[]{"13.3", "12.3", "13.33", "122.3", "19.3", "10.3", "12.4", "15.7"};
        url = "http://localhost:"+port+"/api/datastructure/sort";
        uri = new URI(url);
        data = new HashMap[8];
        for(int i = 0; i < 8; i++) {
            data[i] = new HashMap<>();
            data[i].put("some_name", values[0][i]);
            data[i].put("val", values[1][i]);
            data[i].put("fl", values[2][i]);
        }
        key = "val";
        direction = "desc";
        iterations = 0;
    }

    /**
     * Test for input request with correct arguments
     */
    @Test
    public void restDataStructureSorted() {
        String realResult = "[{val=3, fl=10.3, some_name=val2}, {val=12, fl=15.7, some_name=aaal2}, {val=13, fl=12.4, some_name=dasa}, {val=23, fl=122.3, some_name=val2}, {val=123, fl=19.3, some_name=eva123123}, {val=3123, fl=13.33, some_name=vaeee}, {val=12323, fl=12.3, some_name=val2}, {val=12323, fl=13.3, some_name=va}]";
        Map<String, Object> payload = new HashMap<String, Object>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        request = new HttpEntity<Map<String, Object>>(payload, headers);
        payload.put("data", data);
        payload.put("key", key);
        payload.put("algorithm", "quick");
        ResponseEntity<Map> response = restTemplate.postForEntity(
                uri,
                request,
                Map.class
        );

        assertTrue(response.hasBody());
        assertNotNull(response.getBody().get("result"));
        MatcherAssert.assertThat(response.getBody().get("result").toString(), is(realResult));
    }

    /**
     * Test for input request with incorrect arguments
     */
    @Test
    public void restDataStructureSortedException() {
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