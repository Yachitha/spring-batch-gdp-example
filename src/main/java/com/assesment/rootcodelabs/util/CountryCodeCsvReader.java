package com.assesment.rootcodelabs.util;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Log
public class CountryCodeCsvReader implements GenericCsvReader {
    @PostConstruct
    public void postConstructInit() {
        readFile();
    }

    @Override
    public void readFile() {
        log.info("reading file");
    }
}
