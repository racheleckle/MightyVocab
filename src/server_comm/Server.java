package server_comm;

import java.io.BufferedReader;
import java.util.ArrayList;

import org.json.JSONException;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import model_classes.User;

public class Server extends Thread {

	private String path;
	private volatile boolean exit;
	private BufferedReader standardInput;
	private BufferedReader standardError;
	
	private ArrayList<User> users;
	
	/**
	 * Default Server
	 */
	public Server() {
	}

	public Server(String path) {
		if (path == null) {
			throw new IllegalArgumentException("Path cannot be null");
		}

		if (path.isEmpty()) {
			throw new IllegalArgumentException("Path cannot be empty");
		}

		this.path = path;
	}

	public void serverConnection() {
		try (ZContext context = new ZContext()) {

			ZMQ.Socket socket = context.createSocket(ZMQ.REP);
			socket.bind("tcp://127.0.0.1:5555");

			RequestHandler handler = new RequestHandler();

			// Loop to handle requests
			while (!Thread.currentThread().isInterrupted()) {
				byte[] reply = socket.recv(0);
				String message = new String(reply, ZMQ.CHARSET);
				
				System.out.println("Message received: " + message);
				
				// Wait for request from the client
				//String reqType = socket.recvStr();
				//String reqData = socket.recvStr(ZMQ.CHARSET);

				//System.out.println("The request: " + reqType);
				
				// Handle the request
				String response = handler.handleRequest(message);
				
				if (message.equals("exit")) {
					System.out.println("Server - exiting");
					socket.close();
					context.destroy();
					return;
				}

				// Send the response back to the client
				socket.send(response);
			}
			socket.close();
		}
	}

	@Override
	public void run() {
		this.serverConnection(); 
//		Context context = ZMQ.context(1);
//		Socket socket = context.socket(ZMQ.REP);
//		socket.bind("tcp://127.0.0.1:5555");
//
//		while (!Thread.currentThread().isInterrupted()) {
//
//			byte[] reply = socket.recv(0);
//			String message = new String(reply, ZMQ.CHARSET);
//			System.out.println("Server - Recieved " + ": [" + message + "]");
//
//			if (message.equals("exit")) {
//				System.out.println("Server - exiting");
//				socket.close();
//				context.term();
//				return;
//			}
//
//			this.delay();
//
//			// Create a Hello Message
//			String request = "World";
//
//			// Send the message
//			socket.send(request.getBytes(ZMQ.CHARSET), 0);
//
//		}
//
//		socket.close();
//		context.term();

	}

	private void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException exc) {
			exc.printStackTrace();
		}
	}

	public void exit() throws JSONException {
		Client.disconnectFromSocket(null);
		this.exit = true;
	}
}
