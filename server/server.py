import zmq # type: ignore
import json
from typing import MutableMapping, Any

'''
Handles server requests

@author Group 4
@version Spring 2023
'''

class _RequestHandler:

    '''
    Creates a new request handler with provided manager
    '''

    def __init__(self):
        
