package com.gdit.ediscovery.foia.vango.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
public class PosCount {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long id;

  @ElementCollection
  public Map<String, Integer> words;

  public PosCount(String word, Integer count) {
    words = new HashMap<>();
    words.put(word, count);
  }

  public void increment(String word) {
    Integer count = words.get(word);
    if (count == null) {
      words.put(word, 1);
    } else {
      count += 1;
      words.put(word, count);
    }
  }

  @JsonProperty
  public int count() {
    return words.values().stream().reduce(0, Integer::sum);
  }
}
