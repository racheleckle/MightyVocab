import os

from server import Server

'''
Launches server

@author Group 4
@version Spring 2023
'''

import zmq

SERVER_ADDRESS = "tcp://*:5555"


def handle_request(request):
    request_type = request.get("type")
    request_body = request.get("request")
    response = {"status": "error", "message": "Unknown request type."}

    if request_type == "VERIFY_USER":
        response = handle_verify_user(request_body)
    elif request_type == "ADD_USER":
        response = handle_add_user(request_body)
    elif request_type == "UPDATE_USERINFO":
        response = handle_update_userinfo(request_body)
    elif request_type == "REMOVE_USER":
        response = handle_remove_user(request_body)
    elif request_type == "GET_USER":
        response = handle_get_user(request_body)
    elif request_type == "LOGIN":
        response = handle_login(request_body)
    elif request_type == "LOGOUT":
        response = handle_logout(request_body)
    elif request_type == "CREATE_NOTECARD":
        response = handle_create_notecard(request_body)
    elif request_type == "EDIT_NOTECARD":
        response = handle_edit_notecard(request_body)
    elif request_type == "DELETE_NOTECARD":
        response = handle_delete_notecard(request_body)
    elif request_type == "GET_NOTECARDS":
        response = handle_get_notecards(request_body)
    return response


def handle_verify_user(request_body):
    username, password = get_username_password(request_body)
    if verify_user(username, password):
        return {"status": "success", "message": "User verified."}
    else:
        return {"status": "error", "message": "Invalid username or password."}


def handle_add_user(request_body):
    username, password, email = get_username_password_email(request_body)
    if add_user(username, password, email):
        return {"status": "success", "message": "User added."}
    else:
        return {"status": "error", "message": "Failed to add user."}


def handle_update_userinfo(request_body):
    username, email = get_username_email(request_body)
    if update_userinfo(username, email):
        return {"status": "success", "message": "User info updated."}
    else:
        return {"status": "error", "message": "Failed to update user info."}


def handle_remove_user(request_body):
    username = get_username(request_body)
    if remove_user(username):
        return {"status": "success", "message": "User removed."}
    else:
        return {"status": "error", "message": "Failed to remove user."}


def handle_get_user(request_body):
    username = get_username(request_body)
    user = get_user(username)
    if user is not None:
        return {"status": "success", "message": "User found.", "user": user}
    else:
        return {"status": "error", "message": "User not found."}


def handle_login(request_body):
    username, password = get_username_password(request_body)
    if login(username, password):
        return {"status": "success", "message": "User logged in."}
    else:
        return {"status": "error", "message": "Invalid username or password."}


def handle_logout(request_body):
    username = get_username(request_body)
    if logout(username):
        return {"status": "success", "message": "User logged out."}
    else:
        return {"status": "error", "message": "Failed to log out user."}


def handle_create_notecard(request_body):
    username, title, content = get_username_title_content(request_body)
    if create_notecard(username, title, content):
        return {"status": "success", " message": "Notecard created."}
    else:
        return {"status": "error", "message": "Failed to create notecard."}


def handle_edit_notecard(request_body):
    username, notecard_id, title, content = get_username_notecard_id_title_content(request_body)
    if edit_notecard(username, notecard_id, title, content):
        return {"status": "success", "message": "Notecard edited."}
    else:
        return {"status": "error", "message": "Failed to edit notecard."}


def handle_delete_notecard(request_body):
    username, notecard_id = get_username_notecard_id(request_body)
    if delete_notecard(username, notecard_id):
        return {"status": "success", "message": "Notecard deleted."}
    else:
        return {"status": "error", "message": "Failed to delete notecard."}


def handle_get_notecards(request_body):
    username = get_username(request_body)
    notecards = get_notecards(username)
    if notecards is not None:
        return {"status": "success", "message": "Notecards found.", "notecards": notecards}
    else:
        return {"status": "error", "message": "No notecards found."}
    
    
def main():
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind(SERVER_ADDRESS)

    try:
        while True:
            message = socket.recv_string()
            request = json.loads(message)
            response = handle_request(request)
            socket.send_string(response)
    except KeyboardInterrupt:
        pass

    socket.close()
    context.term()


if __name__ == "__main__":
    main()
