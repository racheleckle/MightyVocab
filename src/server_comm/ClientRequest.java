package server_comm;

import model_classes.RequestType;

public class ClientRequest {
	private RequestType type;
	
	public ClientRequest() {
		this.type = RequestType.NONE;
	}
	
	public RequestType getRequestType() {
		return this.type;
	}
	
	public void setRequestType(RequestType type) {
		this.type = type;
	}

}
