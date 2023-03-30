package model_classes;

public enum RequestType {
	VERIFY_USER("verifyUser"), ADD_USER("addUser"), UPDATE_USERINFO("updateUser"), REMOVE_USER("removeUser"),
	GET_USER("getUser"), LOGIN("login"), LOGOUT("logout"), CREATE_NOTECARD("createNotecard"),
	EDIT_NOTECARD("editNotecard"), DELETE_NOTECARD("deleteNotecard"), GET_NOTECARDS("getNotecards");

	private String type;

	RequestType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
