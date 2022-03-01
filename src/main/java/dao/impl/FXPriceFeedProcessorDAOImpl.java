package dao.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import dao.FXPriceFeedProcessorDAO;
import dto.PriceDTO;

public class FXPriceFeedProcessorDAOImpl implements FXPriceFeedProcessorDAO {

	// As per the exercise document: "just show where the endpoint will be, you do not need to implement a webserver"
	// Therefore, I will simulate the save method by persisting the prices to a file instead of a DB
	// Also, note that usually I would work with an entity instead of a DTO inside this layer.
	@Override
	public void save(List<PriceDTO> prices) {
		try {
			List<String[]> pricesInDatabase = this.getPrices();
			ArrayList<String[]> pricesToPersist = new ArrayList<>();
			CSVWriter writer = new CSVWriter(new FileWriter("/home/mef/fakeDB/prices.csv"));
			// This is section of code is simulating a simple SQL query, so I'm leaving it in the DAO layer.
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
			
			for(PriceDTO price : prices) {
				for (String[] priceDB : pricesInDatabase) {
					if(priceDB[1].equals(price.getInstrumentName())) {
						// If the instrument name is the same, and the timestamp is more recent, then I write it to the "DB"
						if (price.getTimestamp().isAfter(LocalDateTime.parse(priceDB[4], formatter))) {
							pricesToPersist.add(price.toCSVFormat());
						}
						// Otherwise I persist the previous entry on the "table"
						// With hibernate this would be a simple update or merge
						else {
							pricesToPersist.add(priceDB);
						}
					}
				}
			}
			writer.writeAll(pricesToPersist);
			writer.close();
		} catch (IOException e) {
			// Usually I would throw a DAO exception then log it
			e.printStackTrace();
		}
		
	}
	
	//In a real project I would use hibernate to recover the latest prices stored in a DB
	public List<String[]> getPrices(){
		BufferedReader reader;
		CSVReader csvReader;
		try {
			reader = new BufferedReader(new FileReader("/home/mef/fakeDB/prices.csv"));
			csvReader = new CSVReader(reader);
			List<String[]> pricesInDatabase;
			pricesInDatabase = csvReader.readAll();
		    reader.close();
		    csvReader.close();
		    return pricesInDatabase;
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.<String[]>emptyList();
		} // Here I should add a finally clause to always close the streams of the reader,
		 // but I'm short on time and its not the point of the exercise
	}

}
