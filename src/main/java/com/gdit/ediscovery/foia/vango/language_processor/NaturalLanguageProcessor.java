package com.gdit.ediscovery.foia.vango.language_processor;

import com.gdit.ediscovery.foia.vango.VangoApplication;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resources:
 * http://opennlp.sourceforge.net/models-1.5/
 * https://www.programcreek.com/2012/05/opennlp-tutorial/
 * https://rednoise.org/rita/reference/PennTags.html
 */

@Service
public class NaturalLanguageProcessor implements LanguageProcessor {

  private SentenceDetectorME sentenceDetectorME;
  private Tokenizer tokenizer;
  private POSTaggerME posTaggerME;

  public NaturalLanguageProcessor() {
    InputStream englishSentenceBin = VangoApplication.class.getResourceAsStream("/fixtures/en-sent.bin");
    InputStream englishTokenBin = VangoApplication.class.getResourceAsStream("/fixtures/en-token.bin");
    InputStream englishPosMaxentBin = VangoApplication.class.getResourceAsStream("/fixtures/en-pos-maxent.bin");
    try {
      SentenceModel sentenceModel = new SentenceModel(englishSentenceBin);
      sentenceDetectorME = new SentenceDetectorME(sentenceModel);

      TokenizerModel tokenizerModel = new TokenizerModel(englishTokenBin);
      tokenizer = new TokenizerME(tokenizerModel);

      POSModel posModel = new POSModel(englishPosMaxentBin);
      posTaggerME = new POSTaggerME(posModel);

      englishSentenceBin.close();
      englishTokenBin.close();
      englishPosMaxentBin.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String[] getSentences(String words) {
    return sentenceDetectorME.sentDetect(words);
  }

  @Override
  public String[] getTokens(String words) {
    return tokenizer.tokenize(words);
  }

  @Override
  public String[] tagPartsOfSpeech(String[] words, String[] tokens) {
    return posTaggerME.tag(words, tokens);
  }

}
