package com.gdit.ediscovery.foia.vango.quote;

import com.gdit.ediscovery.foia.vango.language_processor.NaturalLanguageProcessor;
import com.gdit.ediscovery.foia.vango.quote.configuration.PosCategoryConfiguration;
import com.gdit.ediscovery.foia.vango.quote.configuration.RequiredWordCountsConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class QuoteAnalyserTest {

  private QuoteAnalyser quoteAnalyser;

  @BeforeEach
  public void setUp() {
    quoteAnalyser = new QuoteAnalyser(
      new NaturalLanguageProcessor(),
      new RequiredWordCountsConfiguration(),
      new PosCategoryConfiguration()
    );
  }

  @Test
  public void counts_required_tokens() {
    Iterable<Quote> results = quoteAnalyser.analyse(toItr("YOU you THAT that THING thing THEY they."));
    Map<String, Integer> harperLeeWordCounts = results.iterator().next().getWordCounts();
    assertEquals(2, harperLeeWordCounts.get("you"));
    assertEquals(2, harperLeeWordCounts.get("that"));
    assertEquals(2, harperLeeWordCounts.get("thing"));
    assertEquals(2, harperLeeWordCounts.get("they"));
    assertEquals(1, harperLeeWordCounts.get("."));
  }

  @Test
  public void removes_third_and_fifth_sentences() {
    Iterable<Quote> results = quoteAnalyser.analyse(
      toItr("John Wick is awesome. So is Batman. Wolverine too. And Neo, for sure. But what about SuperMan?"));
    String quoteMissingSentence3and5 = results.iterator().next().getQuoteMissingSentence3and5();
    assertFalse(quoteMissingSentence3and5.contains("Wolverine too."));
    assertFalse(quoteMissingSentence3and5.contains("But what about SuperMan?"));
  }

  @Test
  public void removes_third_sentence() {
    Iterable<Quote> results = quoteAnalyser.analyse(
      toItr("John Wick is awesome. So is Batman. Wolverine too."));
    String quoteMissingSentence3and5 = results.iterator().next().getQuoteMissingSentence3and5();
    assertFalse(quoteMissingSentence3and5.contains("Wolverine too."));
  }

  @Test
  public void removes_no_sentences() {
    Iterable<Quote> results = quoteAnalyser.analyse(
      toItr("John Wick is awesome. So is Batman."));
    String quoteMissingSentence3and5 = results.iterator().next().getQuoteMissingSentence3and5();
    assertTrue(quoteMissingSentence3and5.contains("John Wick is awesome. So is Batman."));
  }

  @Test
  public void counts_number_of_nouns() {
    Iterable<Quote> results = quoteAnalyser.analyse(toItr("A book and cars."));
    Map<String, PosCount> posCounts = results.iterator().next().getPosCounts();
    assertEquals(2, posCounts.get("noun").getWords().size());
  }

  @Test
  public void counts_number_of_verbs() {
    Iterable<Quote> results = quoteAnalyser.analyse(toItr("Batman jumps. Superman flies. Neo plugs in."));
    Map<String, PosCount> posCounts = results.iterator().next().getPosCounts();
    assertEquals(3, posCounts.get("verb").getWords().size());
  }

  Iterable<Quote> toItr(String quote) {
    return Collections.singletonList(new Quote(quote));
  }

}
