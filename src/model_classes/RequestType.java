package model_classes;

public enum RequestType {
	VERIFY_USER("verifyUser"), ADD_USER("addUser"), UPDATE_USER("updateUser"), REMOVE_USER("removeUser"),
	COMPLETE("complete"), NONE("none");

	private String type;

	RequestType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
