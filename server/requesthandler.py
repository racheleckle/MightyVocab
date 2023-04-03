import os
from server import Server
import zmq


'''
Launches server

@author Group 4
@version Spring 2023
'''
class RequestHandler:

    SERVER_ADDRESS = "tcp://*:5555"
    # authManager: AuthManager
    
    def __init__(self, credentialsManager: CredentialsManager):
        if (authManager == None):
            raise Exception("Must be provided a Authorization Manager")
        if (not isinstance(authManager, AuthManager)):
            raise Exception("Must provide subtype of Authorization Manager")
        self.authManager = authManager

    def handle_request(self, request):
        response = {}
        
        if "requestType" not in request:
            response = {"status": "error", "message": "Unknown request type."}
        elif request["requestType"] == "VERIFY_USER":
            data = json.loads(request["request"])
            response = self.verifyuser(data["username"], data["password"])
        elif request["requestType"] == "ADD_USER":
            data = json.loads(request["request"])
            response = handle_add_user(data["username"], data["password"])
        elif request["requestType"] == "UPDATE_USERINFO":
            data = json.loads(request["request"])
            response = handle_update_userinfo(data["username"])
        elif request["requestType"] == "REMOVE_USER":
            data = json.loads(request["request"])
            response = handle_remove_user(data["username"])
        elif request["requestType"] == "GET_USER":
            response = handle_get_user()
        elif request["requestType"] == "LOGIN":
            response = handle_login()
        elif request["requestType"] == "LOGOUT":
            response = handle_logout()
        elif request["requestType"] == "CREATE_NOTECARD":
            data = json.loads(request["request"])
            response = handle_create_notecard(data["term"], data["definition"])
        elif request["requestType"] == "EDIT_NOTECARD":
            data = json.loads(request["request"])
            response = handle_edit_notecard(data["term"], data["definition"])
        elif request["requestType"] == "DELETE_NOTECARD":
            data = json.loads(request["request"])
            response = handle_delete_notecard(data["term"], data["definition"])
        elif request["requestType"] == "GET_NOTECARDS":
            response = self.get_notecards()
        else:
            error_message = "Unknown Request Type ({})".format(request["requestType"])
            response = {"status": "error", "message": error_message}        
        return response
    
    
    def handle_verify_user(self, username, password):
        if password == password:
            user = self.authManager.verifyUser(username)
            response = {"status": "success", "user": user}
        else:
            response = {"status": "error", "user": "Invalid username or password."}
        return reponse
    
    
    def handle_add_user(self, username, password):
        if self.authManager.addUser(username, password):
            response = {"status": "success", "message": "successfully added user"}
        else:
            response = {"status": "failed", "message": "failed to add user"}
        return response
    
    
    def handle_update_userinfo(self, username):
        if self.authManager.updateUserInfo(username):
            response = {"status": "success", "message": "successfully updated user"}
        else:
            response = {"status": "failed", "message": "failed to update user"}
        return response
    
    
    def handle_remove_user(self, username):
        if self.authManager.removeUser(username):
            response = {"status": "success", "message": "successfully removed user"}
        else:
            response = {"status": "failed", "message": "failed to remove user"}
        return response
    
    
    def handle_get_user(self):
        if self.authManager.getUser():
            response = {"status": "success", "message": "successfully grabbed user"}
        else:
            response = {"status": "failed", "message": "failed to get user"}
        return response
    
    
    def handle_login(self):
        if self.authManager.login():
            response = {"status": "success", "message": "successfully logged in"}
        else:
            response = {"status": "failed", "message": "failed to login"}
        return response
    
    
    def handle_logout(self):
        if self.authManager.logout():
            response = {"status": "success", "message": "successfully logged out"}
        else:
            response = {"status": "failed", "message": "failed to logout"}
        return response
    
    
    def handle_create_notecard(self, term, definition):
        if self.authManager.createNotecard(term, definition):
            response = {"status": "success", "message": "successfully created notecard"}
        else:
            response = {"status": "failed", "message": "failed to create notecard"}
        return response
    
    
    def handle_edit_notecard(self, term, definition):
        if self.authManager.editNotecard(term, definition):
            response = {"status": "success", "message": "successfully edited notecard"}
        else:
            response = {"status": "failed", "message": "failed to edit notecard"}
        return response
    
    
    def handle_delete_notecard(self, term, definition):
        if self.authManager.deleteNotecard(term, definition):
            response = {"status": "success", "message": "successfully deleted notecard"}
        else:
            response = {"status": "failed", "message": "failed to delete user"}
        return response
    
    
    def handle_get_notecards(self):
        if self.authManager.getNotecards():
            response = {"status": "success", "message": "successfully grabbed notecards"}
        else:
            response = {"status": "failed", "message": "failed to get notecards"}
        return response
    
    
    def _init_(self, authManager):
        if not isinstance(authManager, AuthManager):
            raise Exception("Must provide subtype of AuthManager")
        self.authManager = authManager
