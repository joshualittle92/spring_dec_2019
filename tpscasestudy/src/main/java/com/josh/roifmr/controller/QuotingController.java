package com.josh.roifmr.controller;

import com.josh.roifmr.domain.exhange.QuoteGenerationException;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;
import com.josh.roifmr.dto.QuoteRequest;
import com.josh.roifmr.services.dto.QuoteRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/exchange/quoting")
public class QuotingController {

    private final QuoteRequestService quoteRequestService;

    Logger log = LoggerFactory.getLogger(QuotingController.class);

    public QuotingController(QuoteRequestService qSvc){
        this.quoteRequestService = qSvc;
    }

    @PostMapping(path = "mock", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getGeneratedQuotes(@Valid @RequestBody QuoteRequest request) throws QuoteRequestDateValidationException, QuoteGenerationException {
        log.info("Quote request", request);
        return new ResponseEntity(quoteRequestService.getRequestedQuote(request), HttpStatus.CREATED);
    }

    public ResponseEntity getAvailableGeneratedQuoteFiles() {
        return new ResponseEntity(HttpStatus.OK);
    }
}

