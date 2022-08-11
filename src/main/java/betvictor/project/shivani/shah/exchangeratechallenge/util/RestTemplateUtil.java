package betvictor.project.shivani.shah.exchangeratechallenge.util;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("betvictor.project.shivani.shah.exchangeratechallenge")
public class RestTemplateUtil {

	private Logger log = LoggerFactory.getLogger(RestTemplateUtil.class);

	@Bean(name = "restTemplate")
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {
			//log.debug("Request body: {}", body);
			return execution.execute(request, body);
		}));

		return restTemplate;
	}

}