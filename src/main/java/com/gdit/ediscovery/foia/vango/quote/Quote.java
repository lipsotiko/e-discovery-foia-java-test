package com.gdit.ediscovery.foia.vango.quote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long id;

  @Column(length = 100000)
  public String quoteText;

  @ElementCollection
  public Map<String, Integer> wordCounts;

  @Column(length = 100000)
  public String quoteMissingSentence3and5;

  @ElementCollection
  @OneToMany(cascade = CascadeType.ALL)
  public Map<String, PosCount> posCounts;

  public Quote(String quoteText) {
    this.quoteText = quoteText;
  }

}
