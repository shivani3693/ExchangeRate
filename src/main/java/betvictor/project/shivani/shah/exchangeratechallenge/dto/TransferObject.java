package betvictor.project.shivani.shah.exchangeratechallenge.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferObject {

	@JsonProperty("fromCode")
	private String fromCode;
	@JsonProperty("toCode")
	private String toCode;
	@JsonProperty("fromRate")
	private Double fromRate;
	@JsonProperty("toRate")
	private Double toRate;
	@JsonProperty("fromValue")
	private Double fromValue;
	@JsonProperty("toValue")
	private Double toValue;
	@JsonProperty("list")
	private List<TransferObject> listOfRates;
	@JsonProperty("listOfCurrencies")
	private List<String> listOfCurrencies;
	@JsonProperty("status")
	private String status;
	
	public String getFromCode() {
		return fromCode;
	}
	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}
	public String getToCode() {
		return toCode;
	}
	public void setToCode(String toCode) {
		this.toCode = toCode;
	}
	public Double getFromRate() {
		return fromRate;
	}
	public void setFromRate(Double fromRate) {
		this.fromRate = fromRate;
	}
	public Double getToRate() {
		return toRate;
	}
	public void setToRate(Double toRate) {
		this.toRate = toRate;
	}
	public Double getFromValue() {
		return fromValue;
	}
	public void setFromValue(Double fromValue) {
		this.fromValue = fromValue;
	}
	public Double getToValue() {
		return toValue;
	}
	public void setToValue(Double toValue) {
		this.toValue = toValue;
	}
	public List<TransferObject> getListOfRates() {
		return listOfRates;
	}
	public void setListOfRates(List<TransferObject> listOfRates) {
		this.listOfRates = listOfRates;
	}
	public List<String> getListOfCurrencies() {
		return listOfCurrencies;
	}
	public void setListOfCurrencies(List<String> listOfCurrencies) {
		this.listOfCurrencies = listOfCurrencies;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}