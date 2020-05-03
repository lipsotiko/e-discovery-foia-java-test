package com.gdit.ediscovery.foia.vango.quote;

import com.gdit.ediscovery.foia.vango.language_processor.LanguageProcessor;
import com.gdit.ediscovery.foia.vango.quote.configuration.PosCategoryConfiguration;
import com.gdit.ediscovery.foia.vango.quote.configuration.RequiredWordCountsConfiguration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Resources:
 * https://rednoise.org/rita/reference/PennTags.html
 */

@Service
public class QuoteAnalyser {

  private final LanguageProcessor languageProcessor;
  private final RequiredWordCountsConfiguration requiredWordCountsConfiguration;
  private final PosCategoryConfiguration posCategoryConfiguration;

  public QuoteAnalyser(LanguageProcessor languageProcessor,
                       RequiredWordCountsConfiguration requiredWordCountsConfiguration,
                       PosCategoryConfiguration posCategoryConfiguration) {
    this.languageProcessor = languageProcessor;
    this.requiredWordCountsConfiguration = requiredWordCountsConfiguration;
    this.posCategoryConfiguration = posCategoryConfiguration;
  }

  public Iterable<Quote> analyse(Iterable<Quote> quotes) {
    quotes.forEach(this::analyse);
    return quotes;
  }

  public Quote analyse(Quote quote) {
    String[] sentences = languageProcessor.getSentences(quote.getQuoteText());
    String[] tokens = languageProcessor.getTokens(quote.getQuoteText());
    String[] taggedPos = languageProcessor.tagPartsOfSpeech(tokens, tokens);

    quote.setWordCounts(countTokens(tokens, requiredWordCountsConfiguration.getWords()));
    quote.setQuoteMissingSentence3and5(removeSentencesAtIndex(sentences, 2, 4));
    quote.setPosCounts(countPartsOfSpeech(tokens, taggedPos));
    return quote;
  }

  private String removeSentencesAtIndex(String[] sentences, int... indices) {
    for (int i : indices) if (sentences.length > i) sentences[i] = null;
    StringBuilder stringBuilder = new StringBuilder();
    for (String s : sentences) if (s != null) stringBuilder.append(s).append(" ");
    return stringBuilder.toString();
  }

  private Map<String, Integer> countTokens(String[] quote, String... tokens) {
    Map<String, Integer> tokenCountMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    for (String t : tokens) tokenCountMap.put(t, 0);
    for (String t : quote) {
      Integer count = tokenCountMap.get(t);
      if (count != null) {
        count += 1;
        tokenCountMap.put(t, count);
      }
    }

    return tokenCountMap;
  }

  private Map<String, PosCount> countPartsOfSpeech(String[] quote, String[] taggedPos) {
    Map<String, PosCount> posCounts = new HashMap<>();
    for (int i = 0; i < quote.length; i++) {
      String category = posCategoryConfiguration.getPosCategories().get(taggedPos[i]);
      if (category != null) {
        String word = quote[i];
        if (posCounts.get(category) == null) {
          posCounts.put(category, new PosCount(word, 1));
        } else {
          PosCount posCount = posCounts.get(category);
          posCount.increment(word);
        }
      }

    }
    return posCounts;
  }

}
