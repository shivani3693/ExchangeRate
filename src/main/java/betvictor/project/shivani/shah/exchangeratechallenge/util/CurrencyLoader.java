package betvictor.project.shivani.shah.exchangeratechallenge.util;

import java.util.Iterator;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import betvictor.project.shivani.shah.exchangeratechallenge.model.Currency;
import betvictor.project.shivani.shah.exchangeratechallenge.model.CurrencyList;

@Component
public class CurrencyLoader {

	private Logger log = LoggerFactory.getLogger(CurrencyLoader.class);

	@Autowired
	RestTemplate restTemplate;

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		if (CurrencyList.getAll().isEmpty()) {
			log.info("Loading currencies in cache!");
			String url = UriComponentsBuilder.fromUriString("https://api.exchangerate.host/symbols").build()
					.toUriString();
			String response = this.restTemplate.getForObject(url, String.class);
			if (response != null) {
				JSONObject json = new JSONObject(response);
				if ("true".equals(json.get("success").toString())) {

					JSONObject symbols = (JSONObject) json.get("symbols");
					Iterator<String> keys = symbols.keys();
					while (keys.hasNext()) {
						Currency currency = new Currency();
						String key = keys.next();
						JSONObject cur = (JSONObject) symbols.get(key);

						currency.setCode(cur.get("code").toString());
						currency.setDescription(cur.get("description").toString());
						CurrencyList.addCurrency(currency);
					}
				}
			}
		}
	}

}