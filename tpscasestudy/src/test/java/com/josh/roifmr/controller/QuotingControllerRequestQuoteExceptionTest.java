package com.josh.roifmr.controller;

import com.josh.roifmr.domain.exhange.Generator;
import com.josh.roifmr.domain.exhange.MarketSymbol;
import com.josh.roifmr.domain.exhange.QuoteGenerationException;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;
import com.josh.roifmr.dto.QuoteRequest;
import com.josh.roifmr.dto.RequestError;
import com.josh.roifmr.services.dto.QuoteRequestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuotingControllerRequestQuoteExceptionTest {

    private final String BASE_URL = "api/exchange/quoting";
    private final String QUOTE_URL = String.format("%s/%s", BASE_URL, "mock");
    private final String BAD_DATE_VALIDATION_EXCEPTION_MESSAGE = "Requested quote date range invalid";
    private final String FAILED_TO_FETCH_QUOTE = "Quote request failed date validation. Date range must be in past.";
    private QuoteRequest currentQuote;

    @MockBean
    private QuoteRequestService qService;

    @BeforeEach
    void setUp() throws QuoteRequestDateValidationException, QuoteGenerationException {

    }

    @AfterEach
    void tearDown() {
        currentQuote = null;
    }

    @Test
    void badQuoteRequestWithMockedServiceThrowsException() throws QuoteRequestDateValidationException, QuoteGenerationException {
        currentQuote = new QuoteRequest(LocalDate.now().minusDays(10), 2, MarketSymbol.ORCL, Generator.DEFAULT);

        BDDMockito.given(qService.getRequestedQuote(any(QuoteRequest.class))).willThrow(QuoteGenerationException.class);

        RequestError rtnMessage =
        given().accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(currentQuote)
                .when().post(QUOTE_URL)
                .then().statusCode(HttpStatus.I_AM_A_TEAPOT.value())
                .and().extract().as(RequestError.class);
        assertAll(()->assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), rtnMessage.getErrorCode())
                 ,()->assertEquals(FAILED_TO_FETCH_QUOTE, rtnMessage.getSummary()));
    }
    @Test
    void badQuoteRequestWithDateRangeValidationException(){
        currentQuote = new QuoteRequest(LocalDate.now().minusDays(2), 2, MarketSymbol.ORCL, Generator.DEFAULT);

        RequestError rtnMessage =
        given().accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(currentQuote)
                .when().post(QUOTE_URL)
                .then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .and().extract().as(RequestError.class);
        assertAll(()->assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), rtnMessage.getErrorCode()),
                ()->assertEquals(BAD_DATE_VALIDATION_EXCEPTION_MESSAGE, rtnMessage.getSummary()));
    }

    @Test
    void getQuoteRequestFailureException(){
        currentQuote = new QuoteRequest(LocalDate.now().minusDays(7), 5, MarketSymbol.ORCL, Generator.RANDOMIZED);
        RequestError rtnMessage =
        given().accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(currentQuote)
                .when().post(QUOTE_URL)
                .then().statusCode(HttpStatus.BAD_REQUEST.value())
                .and().extract().as(RequestError.class);
        assertAll(()->assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), rtnMessage.getErrorCode()),
                ()->assertEquals(FAILED_TO_FETCH_QUOTE, rtnMessage.getSummary()));
    }
}