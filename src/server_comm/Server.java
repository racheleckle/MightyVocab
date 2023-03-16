package server_comm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONException;

public class Server extends Thread {
	
	private String path;
	private volatile boolean exit;
	private BufferedReader standardInput;
	private BufferedReader standardError;
	
	public Server(String path) {
		if (path == null) {
			throw new IllegalArgumentException("Path cannot be null");
		}
		
		if (path.isEmpty()) {
			throw new IllegalArgumentException("Path cannot be empty");
		}
		
		this.path = path;
	}
	
	@Override
	public void run() {
		try {
			Process process = Runtime.getRuntime().exec("py " + "\"" + this.path + "\"");
			//Client.connectToSocket();
			
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
	
	public void exit() throws JSONException {
		Client.disconnectFromSocket(null);
		this.exit = true;
	}
	
}
