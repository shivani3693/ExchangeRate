package betvictor.project.shivani.shah.exchangeratechallenge.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity @Data 
public class Currency {

	@Id
	private String id;
	private String code;
	private String description;
	private String symbol;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
}
