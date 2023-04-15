package server_comm;

public class RequestHandler {
	
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

}
