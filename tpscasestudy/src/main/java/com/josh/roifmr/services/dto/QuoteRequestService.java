package com.josh.roifmr.services.dto;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.domain.exhange.QuoteGenerationException;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;
import com.josh.roifmr.dto.QuoteRequest;

public interface QuoteRequestService {

    HistoricalQuotes getRequestedQuote(QuoteRequest quoteRequest) throws QuoteRequestDateValidationException, QuoteGenerationException;
}
