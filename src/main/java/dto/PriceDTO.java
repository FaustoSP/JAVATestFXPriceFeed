package dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceDTO {
	
	private Long id;
	//The instrument name can be EUR/USD, GBP/USD or EUR/JPY
	private String instrumentName;
	private BigDecimal bid;
	private BigDecimal ask;
	//The example CSV does not include a time zone, so I'm using LocalDateTime instead
	private LocalDateTime timestamp;
	
	public PriceDTO(Long id, String instrumentName, BigDecimal bid, BigDecimal ask, LocalDateTime timestamp) {
		super();
		this.id = id;
		this.instrumentName = instrumentName;
		this.bid = bid;
		this.ask = ask;
		this.timestamp = timestamp;
	}
	
	// Used only for the purposes of simulating the persistence to a database by writting it to a CSV file.
	// In a real project, a framework like Hibernate would simply persist the DTO.
	public String[] toCSVFormat() {
		String[] csvLine = {id.toString(), instrumentName, bid.toString(), ask.toString(), timestamp.toString()};
		return csvLine;
	}
	
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public BigDecimal getBid() {
		return bid;
	}
	public void setBid(BigDecimal bid) {
		this.bid = bid;
	}
	public BigDecimal getAsk() {
		return ask;
	}
	public void setAsk(BigDecimal ask) {
		this.ask = ask;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Long getId() {
		return this.id;
	}
}
