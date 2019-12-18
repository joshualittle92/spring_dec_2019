package com.josh.roifmr.configuration;

import com.josh.roifmr.persistence.QuoteFilePersistence;
import com.josh.roifmr.persistence.QuotePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;

@Configuration
public class FileSystemConfiguration {

    @Bean
    public QuotePersistence buildQuotePersistenceBean() throws FileNotFoundException {
        return new QuoteFilePersistence();
    }
}
