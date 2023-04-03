from typing import List


class AuthManager:

    def __init__(self):
        pass
    
    def verify_user(self, username: str, password: str) -> bool:
        """
        Verifies whether the user with the given username and password exists in the system.

        :param username: The username of the user to verify.
        :param password: The password of the user to verify.
        :return: True if the user exists and the password is correct, False otherwise.
        """
        raise NotImplementedError()
        
    def add_user(self, username: str, password: str) -> bool:
        """
        Adds a new user with the given username and password to the system.

        :param username: The username of the new user.
        :param password: The password of the new user.
        :return: True if the user was successfully added, False otherwise.
        """
        raise NotImplementedError()

    def update_user_info(self, username: str) -> bool:
        """
        Updates the information for the user with the given username in the system.

        :param username: The username of the user to update.
        :return: True if the user information was successfully updated, False otherwise.
        """
        raise NotImplementedError()

    def remove_user(self, username: str) -> bool:
        """
        Removes the user with the given username from the system.

        :param username: The username of the user to remove.
        :return: True if the user was successfully removed, False otherwise.
        """
        raise NotImplementedError()
    
    def get_user(self) -> bool:
        """
        Gets the currently logged in user.

        :return: True if there is a logged in user, False otherwise.
        """
        raise NotImplementedError()
    
    def update_user_password(self, username: str, password: str) -> bool:
        """
        Updates the password for the user with the given username in the system.

        :param username: The username of the user to update the password for.
        :param password: The new password for the user.
        :return: True if the password was successfully updated, False otherwise.
        """
        raise NotImplementedError()

    def get_users(self) -> List[str]:
        """
        Retrieves a list of usernames for all users in the system.

        :return: A list of usernames for all users in the system.
        """
        raise NotImplementedError()
    
    def login(self) -> bool:
        """
        Logs the user in to the system.

        :return: True if the login was successful, False otherwise.
        """
        raise NotImplementedError()
    
    def logout(self) -> bool:
        """
        Logs the user out of the system.

        :return: True if the logout was successful, False otherwise.
        """
        raise NotImplementedError()
    
    def create_notecard(self, term: str, definition: str) -> bool:
        """
        Creates a new notecard with the given term and definition.

        :param term: The term for the notecard.
        :param definition: The definition for the notecard.
        :return: True if the notecard was successfully created, False otherwise.
        """
        raise NotImplementedError()
    
    def edit_notecard(self, term: str, definition: str) -> bool:
        """
        Edits the notecard with the given term to have the given definition.

        :param term: The term of the notecard to edit.
        :param definition: The new definition for the notecard.
        :return: True if the notecard was successfully edited, False otherwise.
        """
        raise NotImplementedError()
    
    def deleteNotecard(self, term: str, definition: str) -> bool:
        raise NotImplementedError()
    
    def getNotecards(self) -> bool:
        raise NotImplementedError()
