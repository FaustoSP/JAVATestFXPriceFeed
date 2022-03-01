package service;

import java.util.List;

import bo.impl.FXPriceFeedProcessorBOImpl;

public class FXPriceFeedProcessorService {
	// In a real project I would inject the interface as a bean, and I would never instantiate the implemented class, like so
	//@Inject
	//private FXPriceFeedProcessorBO bo;
	FXPriceFeedProcessorBOImpl bo = new FXPriceFeedProcessorBOImpl();
	
	// In a real project, the return type would be Response from javax.ws.rs.core.Response, and the annotations would be something like this:
	// @GET
	// @Path("/prices")
	// @Produces(MediaType.APPLICATION_JSON)
	
	public List<String[]> getPrices() {
		return bo.getPrices();
	}

}
