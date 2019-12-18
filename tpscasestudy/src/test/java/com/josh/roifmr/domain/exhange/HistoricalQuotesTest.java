package com.josh.roifmr.domain.exhange;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HistoricalQuotesTest {

    private HistoricalQuotes testFixture;
    private UUID currentId;

    @BeforeEach
    void setUp() {
        currentId = UUID.randomUUID();
        testFixture = new HistoricalQuotes();
        testFixture.setId(currentId);
    }

    @Test
    void getUniqueKeyFormatConfirmation(){
        MarketSymbol security = MarketSymbol.ORCL;
        int duration = 10;

        testFixture.setExchangeSymbol(security);
        testFixture.setDuration(duration);

        String expectedFormat = String.format("generated_%s_%s_%s", currentId, security, duration);
        assertEquals(expectedFormat, testFixture.getUniqueKey());
    }

    @Test
    void demoPathJoiningResult(){
        Path dir = Paths.get("quotefiles","mock");
        System.out.println(Paths.get(dir.toString(), "some_file_name",".json"));
    }
}