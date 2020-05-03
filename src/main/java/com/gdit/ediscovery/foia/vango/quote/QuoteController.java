package com.gdit.ediscovery.foia.vango.quote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {
  
  @Autowired
  private QuoteProcessor processor;

  @PutMapping("/quote/add")
  public Quote addQuote(@RequestBody Quote quote) {
    return processor.process(quote);
  }

}
