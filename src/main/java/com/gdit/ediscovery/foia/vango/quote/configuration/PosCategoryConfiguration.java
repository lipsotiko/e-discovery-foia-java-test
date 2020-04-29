package com.gdit.ediscovery.foia.vango.quote.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.TreeMap;

@Data
@Configuration
public class PosCategoryConfiguration {

  Map<String, String> posCategories;

  public PosCategoryConfiguration() {
    posCategories = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    posCategories.put("nn", "noun");
    posCategories.put("nns", "noun");
    posCategories.put("nnp", "noun");
    posCategories.put("nnps", "noun");
    posCategories.put("vb", "verb");
    posCategories.put("vbd", "verb");
    posCategories.put("vbg", "verb");
    posCategories.put("vbn", "verb");
    posCategories.put("vbp", "verb");
    posCategories.put("vbz", "verb");
  }

}
