import os
import typing
import json
import jsonschema
from jsonschema import validate
''' import manager'''

'''
Stores a single set of credentials

@author Group 4
@version Spring 2023
'''

class _UserAccount:
    USER_PROPERTY = "__account__"
    USERNAME = "username"
    PASSWORD = "password"

    userSchema = {
        "type": "object",
        "properties": {
            "__account__": {"type": "boolean"}
            "username" = {"type": "string"}
            "password" = {"type": "string"}
            }
        }
    }
    def validateJSON(jsonData):
        try:
            validate(instance=jsonData, schema=User.userSchema)
        except jsonschema.exceptions.ValidationError as err:
            return false
        return true

class _User:
    USER_PROPERTY = "__user__"
    USERNAME = "username"
    PASSWORD = "password"

    '''
    Creates a new User with specified username and password

    @precondition
    @postcondition
    '''
    def __init__(self, username: str, password: str):
        self._username: str = username
        self._password: str = password

    '''
    Returns the username of the user

    @precondition
    @postcondition

    @return the username of the user
    '''
    def getUsername(self) -> str:
        return self._username

    '''
    Returns the password of the user

    @precondition
    @postcondition

    @return the password of the user
    '''
    def getPassword(self) -> str:
        return self._password

    '''
    Encodes user to serializable object

    @precondition
    @postcondition
    '''
    def encode_user(obj: object) -> object:
        if isinstance(obj, _User):
            return {
                _User.USERNAME : obj._username,
                _User.PASSWORD : obj._password
                }
        else:
            type_name = obj.__class__.__name__
            raise TypeError(f"Object of type '{type_name}' is not JSON Serializable")

    
        
