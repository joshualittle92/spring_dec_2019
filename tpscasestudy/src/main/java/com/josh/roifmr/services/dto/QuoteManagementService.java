package com.josh.roifmr.services.dto;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.persistence.QuotePersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteManagementService implements QuoteStorage{

    private final QuotePersistence qStore;

    public QuoteManagementService(QuotePersistence bRef){
        qStore = bRef;
    }

    @Override
    public String storeGenerateQuote(HistoricalQuotes currentQuote) {
        if(qStore.save(currentQuote.getUniqueKey(), currentQuote)){
            return currentQuote.getUniqueKey();
        }
        return null;
    }

    @Override
    public List<String> getAvailableGeneratedQuotes() {
        return null;
    }

    @Override
    public Optional<HistoricalQuotes> findGeneratedQuote(String quoteKey) {
        return qStore.find(quoteKey);
    }
}
