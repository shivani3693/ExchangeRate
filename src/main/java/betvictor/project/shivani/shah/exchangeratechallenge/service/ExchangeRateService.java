package betvictor.project.shivani.shah.exchangeratechallenge.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import betvictor.project.shivani.shah.exchangeratechallenge.dto.ExchangeRateAPIDTO;
import betvictor.project.shivani.shah.exchangeratechallenge.dto.TransferObject;
import betvictor.project.shivani.shah.exchangeratechallenge.model.Currency;
import betvictor.project.shivani.shah.exchangeratechallenge.model.CurrencyList;
import betvictor.project.shivani.shah.exchangeratechallenge.model.ExchangeRate;
import betvictor.project.shivani.shah.exchangeratechallenge.repository.ExchangeRateRepository;
import betvictor.project.shivani.shah.exchangeratechallenge.util.AppConstants;
import betvictor.project.shivani.shah.exchangeratechallenge.util.ModelUtil;

@Service(value = "exchangeRateService")
public class ExchangeRateService {

	private Logger log = LoggerFactory.getLogger(ExchangeRateService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.host.baseurl}")
	String baseURL;
	
	@Value("${config.api.delay.allowed.in.minutes}")
	int delayInMinutesAllowed;

	@Autowired
	ExchangeRateRepository exchangeRateRepository;

	public TransferObject fetchExchangeRateForCurrencyB(String currencyA, String currencyB) {
		log.debug("Fetching exchange rate for {} -> {}", currencyA, currencyB);
		TransferObject rate = new TransferObject();
		rate.setToCode(currencyB);
		rate.setFromCode(currencyA);
		Double excRate = fetchCurrencyRate(currencyA, currencyB);
		rate.setStatus(AppConstants.ERROR);
		if(excRate != null) {
			rate.setStatus(AppConstants.SUCCESS);
			rate.setToRate(excRate);
		}
		return rate;
	}

	public TransferObject fetchAllExchangeRatesForCurrencyB(String currency) {
		log.debug("Fetching all exchange rates for {}", currency);
		TransferObject response = new TransferObject();
		List<Currency> list = CurrencyList.getAll();
		if (list.isEmpty()) {
			return null;
		}
		List<TransferObject> rateList = new ArrayList<>();
		list.stream().forEach(x -> {
			if (!currency.equals(x.getCode())) {
				TransferObject rate = new TransferObject();
				rate.setToCode(x.getCode());
				Double excRate = fetchCurrencyRate(currency, x.getCode());
				if(excRate != null) {
					rate.setToRate(excRate);
					rateList.add(rate);
				}
			}
		});
		response.setStatus(AppConstants.SUCCESS);
		response.setFromCode(currency);
		response.setListOfRates(rateList);
		return response;
	}

	
	public TransferObject calculateValueForCurrencyB(String currencyA, String currencyB, Double fromValue) {
		log.debug("Fetching value for curruncy {} -> {}", currencyA, currencyB);
		Double excRate = fetchCurrencyRate(currencyA, currencyB);
		if(excRate!=null) {
			TransferObject rate = new TransferObject();
			rate.setToRate(excRate);
			rate.setToCode(currencyB);
			rate.setFromCode(currencyA);
			rate.setFromValue(fromValue);
			rate.setToValue(excRate * fromValue);
			return rate;
		} 
		return null;
	}
	
	public TransferObject calculateValueForAllCurrencies(String currency, Double fromValue) {
		log.debug("Fetching value for all curruncy {}", currency);
		TransferObject response = new TransferObject();
		List<TransferObject> rateList = new ArrayList<>();
		List<Currency> list = CurrencyList.getAll();
		list.stream().forEach(x -> {
			if (!currency.equals(x.getCode())) {
				TransferObject rate = new TransferObject();
				rate.setToCode(x.getCode());
				Double excRate = fetchCurrencyRate(currency, x.getCode());
				if(excRate != null) {
					rate.setToRate(excRate);
					rate.setToValue(excRate * fromValue);
					rateList.add(rate);
				}
			}
		});
		response.setStatus(AppConstants.SUCCESS);
		response.setFromCode(currency);
		response.setFromValue(fromValue);
		response.setListOfRates(rateList);
		return response;
	} 

	public Double fetchCurrencyRate(String currencyA, String currencyB) {
		Optional<ExchangeRate> resp = exchangeRateRepository.findById(currencyA + "-" + currencyB);
		if (resp.isEmpty() || resp.get().getEndTime().isBefore(LocalDateTime.now())) {
			String url = UriComponentsBuilder.fromUriString(baseURL + "/convert").queryParam("from", currencyA)
					.queryParam("to", currencyB).build().toUriString();
			ExchangeRateAPIDTO dto = this.restTemplate.getForObject(url, ExchangeRateAPIDTO.class);
			if (dto != null) {
				ExchangeRate rate = new ExchangeRate();
				rate.setRowKey(dto.getQuery().getFrom()+"-"+dto.getQuery().getTo());
				rate.setRate(dto.getInfo().getRate());
				rate.setToCur(dto.getQuery().getTo());
				rate.setFromCur(dto.getQuery().getFrom());
				rate.setStartTime(LocalDateTime.now());
				rate.setEndTime(LocalDateTime.now().plusMinutes(delayInMinutesAllowed));
				exchangeRateRepository.save(rate);
				return dto.getInfo().getRate();
			}
		} else {
			log.info("Object found from database for currency {} -> {}!", currencyA, currencyB);
			return resp.get().getRate();
		}
		return null;
	}

}
