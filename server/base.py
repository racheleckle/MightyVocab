import typing

'''
Manages the set of credentials for all system users
'''


class AuthManager:

    '''
    Creates a new Manager
    '''

    def __init__(self):
        raise NotImplementedError()
    
    def verifyUser(self, username: str, password: str) -> bool:
        raise NotImplementedError()
        
    '''
    Add a new user with the specified credentials to the system

    @precondition  username != null && password != null
    @postcondition

    @param username
    @param password

    @return
    '''

    def addUser(self, username: str, password: str) -> bool:
        raise NotImplementedError()

    def updateUserInfo(self, username: str) -> bool:
        raise NotImplementedError()

    '''
    Removes a user with specified

    @precondition  username != null
    @postcondition

    @param username
    
    @return
    '''

    def removeUser(self, username: str) -> bool:
        raise NotImplementedError()
    
    def getUser(self) -> bool:
        raise NotImplementedError()
    
        '''
    Update an existing user with the specified credentials to the sytem

    @precondition  username != null && password != null
    @postcondition

    @param username
    @param password

    @return
    '''

    def updateUserPassword(self, username: str, password: str) -> bool:
        raise NotImplementedError()

    '''
    Retrieves a list of names for all users

    @precondition none
    @postcondition none

    @return list of names for users
    '''

    def getUsers(self) -> typing.List[str]:
        raise NotImplementedError()
    
    def login(self) -> bool:
        raise NotImplementedError()
    
    def logout(self) -> bool:
        raise NotImplementedError()
    
    def createNotecard(self, term: str, definition: str) -> bool:
        raise NotImplementedError()
    
    def editNotecard(self, term: str, definition: str) -> bool:
        raise NotImplementedError()
    
    def deleteNotecard(self, term: str, definition: str) -> bool:
        raise NotImplementedError()
    
    def getNotecards(self) -> bool:
        raise NotImplementedError()
    