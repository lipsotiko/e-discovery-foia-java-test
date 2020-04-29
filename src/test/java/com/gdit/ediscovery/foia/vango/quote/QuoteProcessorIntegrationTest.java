package com.gdit.ediscovery.foia.vango.quote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class QuoteProcessorIntegrationTest {

  @MockBean QuoteRepository quoteRepository;

  @MockBean QuoteAnalyser quoteAnalyser;

  @Autowired QuoteProcessor quoteProcessor;

  @Test
  public void passes_quotes_analyzer_and_saves_results() {
    List<Quote> someQuotes = Collections.singletonList(new Quote("Some quote"));
    List<Quote> someModifiedQuotes = Collections.singletonList(new Quote("Some modified quote"));

    when(quoteRepository.findAll()).thenReturn(someQuotes);
    when(quoteAnalyser.analyse(someQuotes)).thenReturn(someModifiedQuotes);

    quoteProcessor.process();

    verify(quoteRepository, times(1)).findAll();
    verify(quoteAnalyser, times(1)).analyse(someQuotes);
    verify(quoteRepository, times(1)).saveAll(someModifiedQuotes);
  }

}