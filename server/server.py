import os
import json
import time
import zmq
from server import Server
from auth import AuthManager
from request_handler import RequestHandler

'''
Launches server

@author Group 4
@version Spring 2023
'''


class ServerLauncher:

    def run(self, authManager: AuthManager):
        if authManager is None:
            raise Exception("Must be provided an Authorization Manager")
        if not isinstance(authManager, AuthManager):
            raise Exception("Must provide subtype of Authorization Manager")
        requestHandler = RequestHandler(authManager)
        context = zmq.Context()
        socket = context.socket(zmq.REP)
        socket.bind("tcp://127.0.0.1:5555")
        
        while True:
            jsonRequest = socket.recv_string()
            request = json.loads(jsonRequest)
            jsonResponse = ""
            print("Received request: %s" % request)
            if request["request"] == "exit":
                return
            else:
                response = requestHandler.handleRequest(request)
                jsonResponse = json.dumps(response)
                time.sleep(1)
                socket.send_string(jsonResponse)


if __name__ == '__main__':
    authManager = AuthManager()
    serverLauncher = ServerLauncher()
    serverLauncher.run(authManager)
