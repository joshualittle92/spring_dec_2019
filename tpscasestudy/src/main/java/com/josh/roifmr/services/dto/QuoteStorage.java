package com.josh.roifmr.services.dto;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;

import java.util.List;
import java.util.Optional;

public interface QuoteStorage {
    String storeGenerateQuote(HistoricalQuotes currentQuote);
    List<String> getAvailableGeneratedQuotes();
    Optional<HistoricalQuotes> findGeneratedQuote(String quoteKey);
}
