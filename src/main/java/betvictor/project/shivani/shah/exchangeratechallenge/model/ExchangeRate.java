package betvictor.project.shivani.shah.exchangeratechallenge.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity @Data 
public class ExchangeRate {

	@Id
	private String rowKey;
	private String toCur;
	private String fromCur;
	private Double rate;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getToCur() {
		return toCur;
	}
	public void setToCur(String toCur) {
		this.toCur = toCur;
	}
	public String getFromCur() {
		return fromCur;
	}
	public void setFromCur(String fromCur) {
		this.fromCur = fromCur;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
}
