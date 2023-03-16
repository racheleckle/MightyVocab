package server_comm;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQ.Context;

public class Client extends Thread {
	
	//private RequestType type;
	private String request;
	
	private static Context context = ZMQ.context(1);
	private static Socket socket = context.socket(ZMQ.REQ);
	private boolean responseReceived;
	
	public Client(String request) {
		if (request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		
		this.request = request;
	}
	
	public static void disconnectFromSocket(Server server) throws JSONException {
		if (!server.isAlive()) {
			return;
		}
		disconnectFromSocket();
	}
	
	public static void disconnectFromSocket() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("request", "exit");
		System.out.println("Client - Sending exit");
		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);
		
		socket.close();
		context.term();
	}
	
	// Connect to socket ---->>>>>
	
}
