package model_classes;

public enum RequestType {
	VERIFY_PASSWORD("verifyUser"), ADD_USER("addUser"), UPDATE_USERINFO("updateUser"), REMOVE_USER("removeUser"),
	UPDATE_USER("updateUser"), GET_USER("getUser"), LOGIN("login"), LOGOUT("logout"), CREATE_NOTECARD("createNotecard"),
	EDIT_NOTECARD("editNotecard"), DELETE_NOTECARD("deleteNotecard"), GET_NOTECARDS("getNotecards");

	private final String type;

	private RequestType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}