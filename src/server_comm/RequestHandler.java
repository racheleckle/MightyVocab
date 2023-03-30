package server_comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zeromq.ZContext;

public class RequestHandler {

	public HashMap<String, String> getUserInfo(String username, String password) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "GET_USER");
		request.put("username", username);
		request.put("password", password);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof String) {
				String jsonResponse = (String) responseObj;
				JSONObject json = new JSONObject(jsonResponse);
				String name = json.getString("name");
				String passwordResponse = json.getString("password");
				HashMap<String, String> userInfo = new HashMap<>();
				userInfo.put("name", name);
				userInfo.put("password", passwordResponse);
				return userInfo;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException | JSONException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return null;
	}

	public boolean addUser(String username, String password) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "ADD_USER");
		request.put("username", username);
		request.put("password", password);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof Boolean) {
				boolean response = (Boolean) responseObj;
				return response;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return false;
	}

	public boolean updateUserInfo(String username, String password) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "UPDATE_USER");
		request.put("username", username);
		request.put("password", password);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof Boolean) {
				return (Boolean) responseObj;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return false;
	}

	public boolean removeUser(String username, String password) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "REMOVE_USER");
		request.put("username", username);
		request.put("password", password);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof Boolean) {
				return (Boolean) responseObj;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return false;
	}

	public boolean verifyUser(String username, String password) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "VERIFY_USER");
		request.put("username", username);
		request.put("password", password);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof Boolean) {
				return (Boolean) responseObj;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return false;
	}

	public HashMap<String, String> login(String username, String password) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "LOGIN");
		request.put("username", username);
		request.put("password", password);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof String) {
				String jsonResponse = (String) responseObj;
				JSONObject json = new JSONObject(jsonResponse);
				String name = json.getString("name");
				String passwordResponse = json.getString("password");
				HashMap<String, String> userInfo = new HashMap<>();
				userInfo.put("name", name);
				userInfo.put("password", passwordResponse);
				return userInfo;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException | JSONException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return null;
	}

	public boolean logout(String username, String password) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "LOGOUT");
		request.put("username", username);
		request.put("password", password);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof String) {
				String jsonResponse = (String) responseObj;
				JSONObject json = new JSONObject(jsonResponse);
				boolean success = json.getBoolean("success");
				return success;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException | JSONException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return false;
	}

	public void createNoteCard() {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "CREATE_NOTECARD");

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof String) {
				String jsonResponse = (String) responseObj;
				JSONObject json = new JSONObject(jsonResponse);
				String message = json.getString("message");
				System.out.println(message);
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException | JSONException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
	}

	public void editNoteCard(String id, String term, String definition) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "EDIT_NOTECARD");
		request.put("id", id);
		request.put("term", term);
		request.put("definition", definition);

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof String) {
				String jsonResponse = (String) responseObj;
				JSONObject json = new JSONObject(jsonResponse);
				String message = json.getString("message");
				System.out.println(message);
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException | JSONException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
	}

	public boolean deleteNoteCard(int notecardID) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "DELETE_NOTECARD");
		request.put("notecardID", Integer.toString(notecardID));

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof Boolean) {
				return (Boolean) responseObj;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return false;
	}

	public ArrayList<HashMap<String, String>> getNoteCards(int userID) {
		HashMap<String, String> request = new HashMap<>();
		request.put("type", "GET_NOTECARDS");
		request.put("userID", Integer.toString(userID));

		ZContext context = new ZContext();
		String serverAddress = "localhost";
		int serverPort = 8080;
		try (Socket socket = new Socket(serverAddress, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject(request);
			out.flush();
			Object responseObj = in.readObject();
			if (responseObj instanceof String) {
				String jsonResponse = (String) responseObj;
				JSONArray jsonArray = new JSONArray(jsonResponse);
				ArrayList<HashMap<String, String>> noteCards = new ArrayList<>();
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject json = jsonArray.getJSONObject(i);
					int id = json.getInt("id");
					String term = json.getString("term");
					String definition = json.getString("definition");
					HashMap<String, String> noteCard = new HashMap<>();
					noteCard.put("noteCardID", Integer.toString(id));
					noteCard.put("term", term);
					noteCard.put("definition", definition);
					noteCards.add(noteCard);
				}
				return noteCards;
			} else {
				throw new IOException("Invalid response type");
			}
		} catch (IOException | ClassNotFoundException | JSONException e) {
			e.printStackTrace();
		} finally {
			context.close();
		}
		return null;
	}
}
