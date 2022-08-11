package betvictor.project.shivani.shah.exchangeratechallenge.util;

import java.time.LocalDateTime;

import betvictor.project.shivani.shah.exchangeratechallenge.dto.TransferObject;
import betvictor.project.shivani.shah.exchangeratechallenge.model.ExchangeRate;

public class ModelUtil {
	
	public static ExchangeRate constructExhangeRateFromTransferObj(TransferObject obj) {
		ExchangeRate exchangeRateObj = new ExchangeRate();
		exchangeRateObj.setRowKey(obj.getFromCode()+"-"+obj.getToCode());
		exchangeRateObj.setFromCur(obj.getFromCode());
		exchangeRateObj.setToCur(obj.getToCode());
		exchangeRateObj.setRate(obj.getToRate());
		exchangeRateObj.setStartTime(LocalDateTime.now());
		exchangeRateObj.setEndTime(LocalDateTime.now().plusMinutes(1));
		return exchangeRateObj;
	}
	
	
}