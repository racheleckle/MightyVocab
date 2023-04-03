package model_classes;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class UserSets {

	private String type;
	private String status;

	public UserSets(String type, String status) {
		this.type = Objects.requireNonNull(type, "Request type cannot be null");
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public JSONObject toJson() {
		JSONObject json = new JSONObject();
		try {
			json.put("requestType", type);
			json.put("status", status);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	@Override
	public String toString() {
		return "Type: " + type + ", Status: " + status;
	}
}