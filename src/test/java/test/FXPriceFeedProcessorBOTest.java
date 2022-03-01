package test;

import org.junit.jupiter.api.Test;

import bo.impl.FXPriceFeedProcessorBOImpl;

class FXPriceFeedProcessorBOTest {
	
FXPriceFeedProcessorBOImpl bo = new FXPriceFeedProcessorBOImpl();
	
	// A proper test would use LocalDateTime.now() in and assert whether the DB in the testing environment updated. But I ran out of time. 
	// Right now it merely tests that no exceptions are thrown
	@Test
	void testOnMessage()
	{
		bo.onMessage("106,EUR/USD,1.1000,1.2000,01-06-2033 12:01:01:001\n"
				+ "107,EUR/JPY,119.60,119.90,01-06-2017 12:01:02:002\n"
				+ "108,GBP/USD,1.2500,1.2560,01-06-2035 12:01:02:002\n");
	}

}
