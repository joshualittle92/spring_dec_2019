package com.josh.roifmr.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class QuotingControllerIntegrationTests {

    @Autowired
    private QuotingController quoteController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAvailableGeneratedQuoteFilesServable(){
        ResponseEntity rtnValue = quoteController.getAvailableGeneratedQuoteFiles();
        assertAll(
                ()-> assertSame(rtnValue.getStatusCode(), HttpStatus.OK)
        );
    }

    //@ParameterizedTest(name = "Non nullable properties for quote request")

}
