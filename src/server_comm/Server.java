package server_comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server extends Thread {

	private final String path;
	private final AtomicBoolean exitFlag = new AtomicBoolean(false);

	public Server(String path) {
		if (path == null || path.isEmpty()) {
			throw new IllegalArgumentException("Path cannot be null");
		}
		this.path = path;
	}

	public void run() {
		try {
			Process process = new ProcessBuilder("py", path).start();
			Client.connectToSocket();
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			System.out.println("---SERVER RESPONSES--:\n");
			String response;
			while ((response = stdInput.readLine()) != null || (response = stdError.readLine()) != null) {
				if (exitFlag.get()) {
					break;
				}
				if (response == null) {
					continue;
				}
				if (response.startsWith("ERROR")) {
					System.out.println("---SERVER ERROR-- (if any):\n");
					System.err.println(response);
				} else {
					System.out.println(response);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Client.disconnectFromSocket();
		}
	}

	public void exit() {
		Client.disconnectFromSocket();
		exitFlag.set(true);
	}
}
