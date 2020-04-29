package com.gdit.ediscovery.foia.vango;

import com.gdit.ediscovery.foia.vango.quote.QuoteProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("production")
class VangoApplicationTests {

	@MockBean
	private QuoteProcessor quoteProcessor;

	@Test
	void quote_processor_executes_on_application_initialization() {
		verify(quoteProcessor, times(1)).process();
	}

}
