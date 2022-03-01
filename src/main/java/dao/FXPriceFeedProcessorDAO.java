package dao;

import java.util.List;

import dto.PriceDTO;

public interface FXPriceFeedProcessorDAO {
	
	//In a real project, this methods would likely be generically implemented in the super class
	public void save(List<PriceDTO> price);
	public List<String[]> getPrices();

}
