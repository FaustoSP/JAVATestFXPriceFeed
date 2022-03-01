package bo.impl;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import bo.FXPriceFeedProcessorBO;
import dao.impl.FXPriceFeedProcessorDAOImpl;
import dto.PriceDTO;

public class FXPriceFeedProcessorBOImpl implements FXPriceFeedProcessorBO{
	
	// Usually I would inject the repository as a bean and use the interface, but given that I only have a short amount of time, and that "the focus is clear and concise code",
	// I'll omit it and instantiate the implemented class instead. This is how I would do it normally:
	// @Inject
	// private FXPriceFeedProcessorDAO dao;
	FXPriceFeedProcessorDAOImpl dao = new FXPriceFeedProcessorDAOImpl();
	
	//Assumption: the columns of the CSV have a constant and consistent format. If that wasn't the case, a  possible solution
	//would be to dynamically fetch said format from a configuration table in a database.
	@Override
	public void onMessage(String message) {
		CSVReader csvReader = new CSVReader(new StringReader(message));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
		ArrayList<PriceDTO> pricesDTO = new ArrayList<PriceDTO>();
		try {
			List<String[]> prices = csvReader.readAll();
			//.replaceAll("\\s+","") removes all white spaces and non visible characters
			for (String[] price : prices) {
				PriceDTO priceDTO = new PriceDTO(Long.parseLong(price[0]),
						price[1],
						new BigDecimal(price[2].replaceAll("\\s+","")),
						new BigDecimal(price[3].replaceAll("\\s+","")),
						LocalDateTime.parse(price[4], formatter));
				
				this.addCommissions(priceDTO);
				
				pricesDTO.add(priceDTO);
			}
			dao.save(pricesDTO);
		} catch (IOException e) {
			// Usually I would throw a BO exception and log it.
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String[]> getPrices() {
		return dao.getPrices();
	}
	
	// "With an incoming price, process each with a margin (add commission) function, assume it is
	// simply -0.1% on bid, +0.1% on ask (subtract from bid, add to ask)."
	// As requested by the exercise:
	private void addCommissions(PriceDTO price) {
		price.setBid(price.getBid().multiply(BigDecimal.valueOf(0.999)));
		price.setAsk(price.getAsk().multiply(BigDecimal.valueOf(1.001)));
	}

}
