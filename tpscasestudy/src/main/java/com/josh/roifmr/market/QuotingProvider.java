package com.josh.roifmr.market;

import com.josh.roifmr.domain.exhange.Generator;

public class QuotingProvider {

    public static QuoteGenerator getQuoteGenerator(Generator generatorType){

        switch(generatorType){
            case RANDOMIZED:
                return new PseudoRandomQuoteAlgorithm();
            case PERCENTAGE:
                return new MarketPercentageQuoteAlgorithm();
        }
        return new SimpleQuoteAlgorithm();
    }
}
