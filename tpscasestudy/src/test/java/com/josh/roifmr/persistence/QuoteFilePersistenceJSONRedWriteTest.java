package com.josh.roifmr.persistence;

import com.josh.roifmr.domain.exhange.HistoricalQuotes;
import com.josh.roifmr.domain.exhange.MarketSymbol;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;
import com.josh.roifmr.market.SimpleQuoteAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class QuoteFilePersistenceJSONRedWriteTest {

    private final Path MOCK_DIRECTORY = Paths.get("quotefiles", "mock");
    private final String FILE_EXTENSION = ".json";

    private HistoricalQuotes currentQuote;

    @Autowired
    private QuotePersistence fileService;

    @BeforeEach
    void setUp() throws QuoteRequestDateValidationException {
        currentQuote = ((new SimpleQuoteAlgorithm()).generateQuote(LocalDate.of(2019,1,1), 30, MarketSymbol.ORCL)).get();
    }

    @AfterEach
    void tearDown() {
        currentQuote = null;
    }

    @Test
    void debugGetDirectoryForFileWrite() throws FileNotFoundException {
        Path totalPath = Paths.get(MOCK_DIRECTORY.toString(),(currentQuote.getUniqueKey()+FILE_EXTENSION));
        System.out.println(totalPath);
        File springFile = ResourceUtils.getFile("classpath:quotefiles/mock/readme.md");
        System.out.println(String.format("Is Dir: %s, Abs Path: %s, str: %s",
                springFile.isDirectory(), springFile.getAbsolutePath(), springFile.toString()));
    }

    @Test
    void saveHistoricalQuoteToMockDirectory() {
        assertTrue(fileService.save(currentQuote.getUniqueKey(), currentQuote));
    }

    @Test
    void findGeneratedHistoricalQuoteFromMockDirectory() {
        String expectedQuoteFileKey = currentQuote.getUniqueKey();
        assertTrue(fileService.save(currentQuote.getUniqueKey(),currentQuote));

        Optional<HistoricalQuotes> rtnValue = fileService.find(expectedQuoteFileKey);
        assertTrue(rtnValue.isPresent());
    }
}