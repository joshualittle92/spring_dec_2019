package com.josh.roifmr.persistence;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;

import java.util.Optional;

public interface QuotePersistence {
    boolean save(String storageKey, HistoricalQuotes quoteValue);
    Optional<HistoricalQuotes> find(String storageKey);
}
