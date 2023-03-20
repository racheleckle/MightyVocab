package server_comm;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

import model_classes.RequestType;

import org.zeromq.ZMQ.Context;

public class Client extends Thread {

	private RequestType type;
	private String request;

	private static Context context = ZMQ.context(1);
	private static Socket socket = context.socket(ZMQ.REQ);
	private boolean responseReceived;

	public Client(String request, RequestType type) {
		if (request == null) {
			throw new IllegalArgumentException("Request cannot be null");
		}
		if (type == null) {
			throw new IllegalArgumentException("Type cannot be null");
		}

		this.request = request;
		this.type = type;
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
	public static void disconnectFromSocket(Server server) throws JSONException {
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
	public static void disconnectFromSocket() throws JSONException {
		JSONObject request = new JSONObject();
		request.put("request", "exit");
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

		this.responseReceived = true;
		return response;
	}

}
