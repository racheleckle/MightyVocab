import os
from server import Server

'''
Launches server

@author Group 4
@version Spring 2023
'''
def run(self, authManager: AuthManager):
    if (authManager == None):
        raise Exception("Must be provided a Authorization Manager")
    if (not isinstance(authManager, AuthManager)):
        raise Exception("Must provide subtype of Authorization Manager")
    requestHandler = RequestHandler(authManager)
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("tcp://127.0.0.1:5555")
    
    while True:
        jsonRequest = socket.recv_string()
        request = json.loads(jsonRequest)
        jsonResponse: str
        print("Received request: %s" % request)
        if (request["request"] == "exit"):
            return
        else:
            response = requestHandler.handleRequest(request)
            jsonResponse = json.dumps(response)
            time.sleep(1)
            socket.send_string(jsonResponse)