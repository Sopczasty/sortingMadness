package pl.put.poznan.sorting.rest;

//Rest imports
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@SpringBootApplication
public class SorterAPI {
    public static void main(String[] args) {
        SpringApplication.run(SorterAPI.class, args);
    }
}





