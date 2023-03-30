//package server_comm;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.zeromq.ZMQ;
//import org.zeromq.ZMQ.Context;
//import org.zeromq.ZMQ.Socket;
//
//import model_classes.RequestType;
//
//public class Client extends Thread {
//
//	private RequestType type;
//	private String request;
//
//	private static Context context = ZMQ.context(1);
//	private static Socket socket = context.socket(ZMQ.REQ);
//	private boolean responseReceived;
//
//	public Client() {
//	}
//
//	public Client(String request, RequestType type) {
//		if (request == null) {
//			throw new IllegalArgumentException("Request cannot be null");
//		}
//		if (type == null) {
//			throw new IllegalArgumentException("Type cannot be null");
//		}
//
//		this.request = request;
//		this.type = type;
//	}
//
//	/// From Sample Client only
//	public void run() {
//		Context context = ZMQ.context(1);
//
//		// Socket to talk to server
//		System.out.println("Connecting to server...");
//
//		Socket socket = context.socket(ZMQ.REQ);
//		socket.connect("tcp://127.0.0.1:5555");
//
//		for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
//			String request = "Hello";
//			System.out.println("Client - Sending Hello " + requestNbr);
//			socket.send(request.getBytes(ZMQ.CHARSET), 0);
//
//			byte[] reply = socket.recv(0);
//			String response = new String(reply, ZMQ.CHARSET);
//			System.out.println("Client - Received " + response + " " + requestNbr);
//		}
//
//		String request = "exit";
//		System.out.println("Client - Sending Exit");
//		socket.send(request.getBytes(ZMQ.CHARSET), 0);
//
//		socket.close();
//		context.term();
//	}
//
//	/**
//	 * Disconnects from the Python server socket
//	 * 
//	 * @precondition none
//	 * @postcondition none
//	 * 
//	 * @param server the server to be disconnected from
//	 * @throws JSONException
//	 */
//	public static void disconnectFromSocket(Server server) {
//		if (!server.isAlive()) {
//			return;
//		}
//		//disconnectFromSocket();
//	}
//
//	/**
//	 * Disconnects from server socket
//	 * 
//	 * @precondition none
//	 * @postcondition none
//	 * 
//	 * @throws JSONException
//	 */
//	public static void disconnectFromSocket() {
//		JSONObject request = new JSONObject();
//		try {
//			request.put("request", "exit");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Client - Sending exit");
//		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);
//
//		socket.close();
//		context.term();
//	}
//
//	/**
//	 * Connects to server socket
//	 * 
//	 * @precondition none
//	 * @postcondition none
//	 * 
//	 */
//	public static void connectToSocket() {
//		context = ZMQ.context(1);
//		socket = context.socket(ZMQ.REQ);
//		System.out.println("Connecting to MighyVocab Server");
//		socket.connect("tcp://127.0.0.1:5555");
//	}
//
//	/**
//	 * Send Client's request to server
//	 * 
//	 * @precondition none
//	 * @postcondition none
//	 * 
//	 * @return the server's response
//	 * @throws Exception the exception to be thrown
//	 */
//	public String sendRequest() throws Exception {
//		JSONObject request = new JSONObject();
//		request.put("requestType", this.type.toString());
//		request.put("request", this.request);
//		socket.setReceiveTimeOut(3000);
//		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);
//
//		byte[] reply = socket.recv(0);
//		String response = new String(reply, ZMQ.CHARSET);
//		System.out.println("Client - Received " + response);
//
//		this.setResponseReceived(true);
//		return response;
//	}
//	
//	/**
//	 * Sends a request to the server and returns the response.
//	 *
//	 * @param requestType the type of the request to send
//	 * @param request     the JSON object representing the request
//	 * @return the JSON object representing the response from the server
//	 * @throws JSONException if there is an error parsing the response from the
//	 *                       server
//	 */
//	public static JSONObject sendRequest(RequestType requestType, JSONObject request) throws JSONException {
//		// Create a new socket and connect to the server
//		Socket socket = context.socket(ZMQ.REQ);
//		socket.connect("tcp://127.0.0.1:5555");
//
//		// Send the request
//		socket.send(requestType.toString().getBytes(ZMQ.CHARSET), ZMQ.SNDMORE);
//		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);
//
//		// Wait for the response
//		byte[] replyType = socket.recv(0);
//		byte[] reply = socket.recv(0);
//
//		// Parse the response
//		JSONObject response = new JSONObject(new String(reply, ZMQ.CHARSET));
//
//		// Clean up the socket and return the response
//		socket.close();
//		return response;
//	}
//
//	public boolean isResponseReceived() {
//		return responseReceived;
//	}
//
//	private void setResponseReceived(boolean responseReceived) {
//		this.responseReceived = responseReceived;
//	}
//}
package server_comm;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import model_classes.RequestType;

public class Client {
	private static final String SERVER_ADDRESS = "tcp://localhost:5555";

	private ZContext context;
	private ZMQ.Socket socket;

	public Client() {
		this.context = new ZContext();
		this.socket = context.createSocket(ZMQ.REQ);
		this.socket.connect(SERVER_ADDRESS);
	}

	public String sendRequest(RequestType type, String request) {
		JSONObject requestJson = new JSONObject();
		try {
			requestJson.put("type", type.toString());
			requestJson.put("request", request);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		this.socket.send(requestJson.toString().getBytes(ZMQ.CHARSET), 0);

		byte[] reply = this.socket.recv(0);
		return new String(reply, ZMQ.CHARSET);
	}

	public void close() {
		this.socket.close();
		this.context.close();
	}
}