package com.gdit.ediscovery.foia.vango.language_processor;

import org.springframework.stereotype.Service;

@Service
public interface LanguageProcessor {
  String[] getSentences(String words);

  String[] getTokens(String words);

  String[] tagPartsOfSpeech(String[] words, String[] tokens);
}
