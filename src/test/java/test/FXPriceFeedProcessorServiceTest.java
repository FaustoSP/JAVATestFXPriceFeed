package test;
import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import service.FXPriceFeedProcessorService;

class FXPriceFeedProcessorServiceTest {
	
	FXPriceFeedProcessorService service = new FXPriceFeedProcessorService();
	
	@Test
	void testGetPricesReturnsSomething()
	{
		List<String[]> prices = service.getPrices();
		for (String[] price : prices) {
			System.out.println(Arrays.toString(price));
		}
		assertFalse(prices.isEmpty() || prices == null);
	}

}
