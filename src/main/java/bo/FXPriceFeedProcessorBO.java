package bo;

import java.util.List;

/*
 * The exercise did not specify any structural pattern. I chose the Data Access Object pattern simply because its the one I'm most familiar with.
 */

public interface FXPriceFeedProcessorBO {
	
	public void onMessage(String message);
	public List<String[]> getPrices();
}
