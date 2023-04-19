package server_comm;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import model_classes.RequestType;
import model_classes.User;

public class Client extends Thread {

	private String type;
	private String request;

	private static Context context = ZMQ.context(1);
	private static Socket socket = context.socket(ZMQ.REQ);
	private boolean responseReceived;

	/**
	 * Default constructor for Client
	 */
	public Client() {

	}

	public Client(String request, String type) {
		if (request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		if (type == null) {
			throw new IllegalArgumentException("Type cannot be null");
		}

		this.request = request;
		this.type = type;
	}

	public void clientConnection() {
		try (ZContext context = new ZContext()) {
			// Create a ZMQ socket
			ZMQ.Socket socket = context.createSocket(ZMQ.REQ);
			socket.connect("tcp://127.0.0.1:5555");
			
			// Create an object to send to the server
			User user = new User("Michael", "lee");
			
			// Send the object to the server
			socket.send("GET".getBytes(ZMQ.CHARSET));
			
			//socket.send(user.toString().getBytes(ZMQ.CHARSET));
			
			//socket.send("exit".getBytes(ZMQ.CHARSET));
			
			// Receive the response from the server
			String response = socket.recvStr();
			System.out.println("Received response: " + response);
			
			// Close the socket
			socket.close();
		}
	}

	/// From Sample Client only
	public void run() {
		this.clientConnection();
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
//
//			if (response.equals("World")) {
//				System.out.println("Theres a match");
//			}
//
//			System.out.println("Client - Received " + response + " " + requestNbr); 
//		}
//
//		String request = "exit";
//		System.out.println("Client - Sending Exit");
//		socket.send(request.getBytes(ZMQ.CHARSET), 0);
//
//		socket.close();
//		context.term();
	}

	/**
	 * Disconnects from the Python server socket
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param server the server to be disconnected from
	 * @throws JSONException
	 */
	public static void disconnectFromSocket(Server server) {
		if (!server.isAlive()) {
			return;
		}
		disconnectFromSocket();
	}

	/**
	 * Disconnects from server socket
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @throws JSONException
	 */
	public static void disconnectFromSocket() {
		JSONObject request = new JSONObject();
		try {
			request.put("request", "exit");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("Client - Sending exit");
		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);

		socket.close();
		context.term();
	}

	/**
	 * Connects to server socket
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public static void connectToSocket() {
		context = ZMQ.context(1);
		socket = context.socket(ZMQ.REQ);
		System.out.println("Connecting to MighyVocab Server"); 
		socket.connect("tcp://127.0.0.1:5555");
	}

	/**
	 * Send Client's request to server
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the server's response
	 * @throws Exception the exception to be thrown
	 */
	public String sendRequest() throws Exception {
		JSONObject request = new JSONObject();
		request.put("requestType", this.type.toString());
		request.put("request", this.request);
		socket.setReceiveTimeOut(3000);
		socket.send(request.toString().getBytes(ZMQ.CHARSET), 0);

		byte[] reply = socket.recv(0);
		String response = new String(reply, ZMQ.CHARSET);
		System.out.println("Client - Received " + response);

		this.setResponseReceived(true);
		return response;
	}

	public boolean isResponseReceived() {
		return responseReceived;
	}

	private void setResponseReceived(boolean responseReceived) {
		this.responseReceived = responseReceived;
	}
}
