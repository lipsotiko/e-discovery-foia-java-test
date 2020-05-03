package com.gdit.ediscovery.foia.vango.quote;

import org.springframework.stereotype.Service;

@Service
public class QuoteProcessor {

  private final QuoteRepository quoteRepository;
  private final QuoteAnalyser quoteAnalyser;

  public QuoteProcessor(QuoteRepository quoteRepository, QuoteAnalyser quoteAnalyser) {
    this.quoteRepository = quoteRepository;
    this.quoteAnalyser = quoteAnalyser;
  }

  public void process() {
    Iterable<Quote> quotes = quoteRepository.findAll();
    quoteRepository.saveAll(quoteAnalyser.analyse(quotes));
  }

  public Quote process(Quote quote) {
    return quoteRepository.save(quoteAnalyser.analyse(quote));
  }

}
