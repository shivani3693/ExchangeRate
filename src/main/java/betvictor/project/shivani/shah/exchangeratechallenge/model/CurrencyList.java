package betvictor.project.shivani.shah.exchangeratechallenge.model;

import java.util.ArrayList;
import java.util.List;

public class CurrencyList {

	private CurrencyList() {
	}

	private static List<Currency> list = new ArrayList<>();

	public static List<Currency> getAll() {
		return list;
	}

	public static void addCurrencies(List<Currency> currencies) {
		list.addAll(currencies);
	}

	public static void addCurrency(Currency currency) {
		list.add(currency);
	}

}
