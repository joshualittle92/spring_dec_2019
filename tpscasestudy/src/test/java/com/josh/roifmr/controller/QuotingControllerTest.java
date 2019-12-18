package com.josh.roifmr.controller;

import com.josh.roifmr.domain.exhange.Generator;
import com.josh.roifmr.domain.exhange.MarketSymbol;
import com.josh.roifmr.dto.QuoteRequest;
import com.josh.roifmr.dto.RequestError;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class QuotingControllerTest {
    private final String BASE_URL = "api/exchange/quoting";
    private final String QUOTE_URL = String.format("%s/%s", BASE_URL, "mock");
    private final String REQ_VALIDATION_MSG = "Request validation failure";

    @Autowired
    private QuotingController quoteController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void badQuoteRequestMissingExchangeSymbol(){
        QuoteRequest qRequest = new QuoteRequest(LocalDate.of(2019, 1, 1), 1, null, Generator.DEFAULT);
        RequestError rtnMessage =
        RestAssured.given().accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(qRequest)
                .log().all()
                .when().post(QUOTE_URL)
                .then().log().all().and().statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .and().extract().as(RequestError.class);
        assertAll(
                ()->assertEquals(HttpStatus.BAD_REQUEST.value(), rtnMessage.getErrorCode())
                ,()->assertEquals(REQ_VALIDATION_MSG, rtnMessage.getSummary())
                ,()->rtnMessage.getDetails().forEach(s -> assertTrue(s.contains("exchangeSymbol")))
        );
    }

    @ParameterizedTest
    @MethodSource
    void badQuoteRequestbindingValidation(QuoteRequest currentQuote){
        RestAssured.given().accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(currentQuote)
                .log().all()
                .when().post(QUOTE_URL)
                .then().log().all().and().statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                .and().extract().as(RequestError.class);
    }
    static Stream<QuoteRequest>badQuoteRequestbindingValidation(){
        return Stream.of(new QuoteRequest(null, 1, MarketSymbol.ORCL, Generator.DEFAULT),
                new QuoteRequest(LocalDate.of(2019,1,1), 0, MarketSymbol.ORCL, Generator.DEFAULT),
                new QuoteRequest(LocalDate.of(2019,1,1), 1, null, Generator.DEFAULT),
                new QuoteRequest(LocalDate.of(2019,1,1), 1, MarketSymbol.ORCL, null));
    }
}