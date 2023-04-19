package server_comm;

import manager.RequestManager;

public class RequestHandler {
	
	private RequestManager requestManager;
	
	/**
	 * Handles a request
	 * 
	 * @precondition requestType != null && !
	 * @param requestType
	 * @param requestData
	 * @return
	 */
	public String handleRequest(String requestType) {
		if (requestType.equals("GET")) {
			return "Response to GET request";
		} else if (requestType.equals("POST")) {
			return "Response to POST request with data: " + requestType; 
		} else {
			return "Unknown request type";
		}
	}
	
	// Testing requests to the server.

}
