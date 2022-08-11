package betvictor.project.shivani.shah.exchangeratechallenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import betvictor.project.shivani.shah.exchangeratechallenge.dto.TransferObject;
import betvictor.project.shivani.shah.exchangeratechallenge.service.ExchangeRateService;
import betvictor.project.shivani.shah.exchangeratechallenge.util.AppConstants;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/exchange")
public class ExchangeRateController {

	private Logger log = LoggerFactory.getLogger(ExchangeRateController.class);

	@Autowired
	ExchangeRateService exchangeRateService;

	@ApiOperation(value = "This method is used to fetch exchange rate from currency A to currency B")
	@GetMapping(value = "/rate/{currencyA}/{currencyB}")
	public TransferObject getExchangeRateForCurrency(@PathVariable String currencyA, @PathVariable String currencyB) {
		log.debug("getExchangeRateForCurrency {} and {}", currencyA, currencyB);
		TransferObject response = exchangeRateService.fetchExchangeRateForCurrencyB(currencyA, currencyB);
		if (response == null) {
			response = new TransferObject();
			response.setStatus(AppConstants.ERROR);
		}
		return response;
	}

	@GetMapping(value = "/rate/{currencyA}")
	public TransferObject getExchangeRateForAllCurrencies(@PathVariable String currencyA) {
		log.debug("getExchangeRateForAllCurrencies {}", currencyA);
		TransferObject response = exchangeRateService.fetchAllExchangeRatesForCurrencyB(currencyA);
		if (response == null) {
			response = new TransferObject();
			response.setStatus(AppConstants.ERROR);
		}
		return response;
	}

	@PostMapping(value = "/value/{currencyA}/{currencyB}")
	public TransferObject getExchangeValueForCurrency(@PathVariable String currencyA, @PathVariable String currencyB,
			@RequestBody TransferObject request) {
		log.debug("getExchangeValueForCurrency {}, {} ", currencyA, currencyB);
		TransferObject response = exchangeRateService.calculateValueForCurrencyB(currencyA, currencyB, request.getFromValue());
		if (response == null) {
			response = new TransferObject();
			response.setStatus(AppConstants.ERROR);
		}
		return response;
	}

	@PostMapping(value = "/value/{currencyA}")
	public TransferObject getExchangeValueForAllCurrencies(@PathVariable String currencyA,
			@RequestBody TransferObject request) {
		log.debug("getExchangeValueForAllCurrencies {}", currencyA);
		TransferObject response = exchangeRateService.calculateValueForAllCurrencies(currencyA, request.getFromValue());
		if (response == null) {
			response = new TransferObject();
			response.setStatus(AppConstants.ERROR);
		}
		return response;
	}

}
