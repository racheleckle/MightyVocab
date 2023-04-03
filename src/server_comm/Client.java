package server_comm;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import model_classes.RequestType;

public class Client extends Thread {
	private final RequestType type;
	private final String request;
	private static final String SERVER_ADDRESS = "tcp://127.0.0.1:5555";
	private static final int TIMEOUT = 3000;
	private static final ZContext context = new ZContext(1);
	private static final ZMQ.Socket socket = context.createSocket(ZMQ.REQ);

	static {
		socket.connect(SERVER_ADDRESS);
		System.out.println("Connecting to Mighty Vocab Server");
	}

	public Client(RequestType type, String request) {
		this.type = Objects.requireNonNull(type, "Type cannot be null");
		this.request = Objects.requireNonNull(request, "Request cannot be null");
	}

	@Override
	public void run() {
		try {
			String response = sendRequest();
			System.out.println("Client - Received " + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendRequest() {
		JSONObject requestObj = new JSONObject();
		try {
			requestObj.put("requestType", type.toString());
			requestObj.put("request", request);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		socket.setReceiveTimeOut(TIMEOUT);
		socket.send(requestObj.toString().getBytes(ZMQ.CHARSET), 0);
		byte[] reply = socket.recv(0);
		String response = new String(reply, ZMQ.CHARSET);
		return response;
	}

	public static void connectToSocket() {
		socket.connect(SERVER_ADDRESS);
		System.out.println("Connecting to Mighty Vocab Server");
	}

	public static void disconnectFromSocket() {
		JSONObject requestObj = new JSONObject();
		try {
			requestObj.put("request", "exit");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		socket.send(requestObj.toString().getBytes(ZMQ.CHARSET), 0);
		socket.close();
		context.close();
		System.out.println("Client - Sending exit");
	}
}
