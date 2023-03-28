package server_comm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

public class Server extends Thread {

	private String path;
	private volatile boolean exit;
	private BufferedReader standardInput;
	private BufferedReader standardError;

//	public Server() {
//	}

//	public Server(String path) {
//		if (path == null) {
//			throw new IllegalArgumentException("Path cannot be null");
//		}
//
//		if (path.isEmpty()) {
//			throw new IllegalArgumentException("Path cannot be empty");
//		}
//
//		this.path = path;
//	}

	@Override
	public void run() {
		Context context = ZMQ.context(1);
		Socket socket = context.socket(ZMQ.REP);
		socket.bind("tcp://127.0.0.1:5555");

		while (!Thread.currentThread().isInterrupted()) {

			byte[] reply = socket.recv(0);
			String message = new String(reply, ZMQ.CHARSET);
			System.out.println("Server - Recieved " + ": [" + message + "]");

			if (message.equals("exit")) {
				System.out.println("Server - exiting");
				socket.close();
				context.term();
				return;
			}

			this.delay();

			// Create a Hello Message
			String request = "World";

			// Send the message
			socket.send(request.getBytes(ZMQ.CHARSET), 0);

		}

		socket.close();
		context.term();

		try {
			ProcessBuilder builder = new ProcessBuilder("py", this.path);
			Process process = builder.start();
			Client.connectToSocket();

			this.standardInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			this.standardError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			String response = this.standardInput.readLine();
			// read output from the command
			System.out.println("----Server Responses-----");
			while (!this.exit) {
				System.out.println(response);
				response = this.standardInput.readLine();
				if (response == null) {
					break;
				}
			}

			// read any errors from the command that was attempted
			System.out.println("---Server Error----");
			while ((response = this.standardError.readLine()) != null && !this.exit) {
				System.err.println(response);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
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
