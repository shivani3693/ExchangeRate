package betvictor.project.shivani.shah.ExchangeRateChallenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import betvictor.project.shivani.shah.exchangeratechallenge.service.ExchangeRateService;
import org.junit.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExchangeRateServiceTest {

	Logger log = LoggerFactory.getLogger(ExchangeRateServiceTest.class);

	@Autowired
	private ExchangeRateService exchangeRateService;

	@BeforeEach
	public void setup() {
		log.info("Loading setup");
	}

	@Test
	public void fetchExchangeRate() {
		Assert.assertTrue(exchangeRateService.fetchCurrencyRate("INR", "GBP")>0);
	}
	
	@Test
	public void fetchExchangeValue() {
		Assert.assertTrue(exchangeRateService.calculateValueForCurrencyB("INR", "GBP", 1000d).getToRate()>0);
	}

}
