package com.gdit.ediscovery.foia.vango.quote.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class RequiredWordCountsConfiguration {

  String[] words = {"you", "that", "thing", "they", "."};

}
