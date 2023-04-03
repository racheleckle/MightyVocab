import os
import typing
import json
import jsonschema
from jsonschema import validate


class UserAccount:
    USER_PROPERTY = "__account__"
    USERNAME = "username"
    PASSWORD = "password"

    userSchema = {
        "type": "object",
        "properties": {
            "__account__": {"type": "boolean"},
            "username": {"type": "string"},
            "password": {"type": "string"},
            "_notecards": {
                "type": "array",
                "items": {
                    "type": "object",
                    "properties": {
                        "term": {"type": "string"},
                        "definition": {"type": "string"}
                    }
                }
            }
        }
    }

    @staticmethod
    def validateJSON(jsonData):
        try:
            validate(instance=jsonData, schema=_UserAccount.userSchema)
        except jsonschema.exceptions.ValidationError as err:
            return False
        return True


class _LocalAuthManager(AuthManager):
    def __init__(self, file: str="data.json"):
        self.systemCredentials: dict[str, User] = {}
        self.dataFile = file
        self.load_data()

    def load_data(self):
        if not os.path.exists(self.dataFile):
            return
        with open(self.dataFile) as user_data:
            data = user_data.read()
            self._systemCredentials = json.loads(data, object_hook=_User.decode_user) 
            user_data.close()

    def save_data(self):
        file = open(self.dataFile, 'w')
        json.dump(self._systemCredentials, file, default=_User.encode_user, indent=4, sort_keys=True)
        file.close()
        
    def verifyUser(self, username: str, password: str) -> bool:
        if username in self._auth and self._auth[username].getPassword() == password:
            return True
        return False

    def addUser(self, username: str, password: str, account:dict) -> bool:
        if (username in self.systemAuth):
            return False
        isValid = UserAccount.validateJSON(profile)
        if (isValid):
            credentials = User(username, password, account)
            self._systemCredentials[username] = credentials
            self.save_data()
            return True
        return False

    def updateUserInfo(self, username: str) -> bool:
        if username in self._auth:
            self.save_data()
            return True
        return False

    def removeUser(self, username: str) -> bool:
        if username not in self._selfAuth:
            return False
        self._selfAuth.pop(username)
        self.save_data()
        return True

    # def getUser(self, username: str) -> typing.Optional["_User"]:
    #      if username not in self._selfAuth:
    #          raise Exception("System with this username unfound")
    #      return self._selfAuth[username].getPassword()

    def updateUserPassword(self, username: str, password: str) -> bool:
        if username not in self._systemAuth:
            return False
        self._selfAuth[username].setProfile(profile)
        self.save_data()
        return True

    def get_user(self, username: str) -> str:
        user = self._systemAuth.get(username)
        if user:
            return json.dumps(user, default=_User.encode_user)
        return None

    def login(self, username: str, password: str) -> bool:
        if self.verifyUser(username, password):
            setattr(self, _UserAccount.USER_PROPERTY, username)
            return True
        return False

    def logout(self):
        setattr(self, _UserAccount.USER_PROPERTY, None)

    def createNotecard(self, term: str, definition: str) -> bool:
        current_user = getattr(self, _UserAccount.USER_PROPERTY, None)
        if current_user:
            user = self.getUser(current_user)
            if user:
                user.add_notecard(term, definition)
                self.save_data()
                return True
        return False

    def editNotecard(self, term: str, definition: str) -> bool:
        current_user = getattr(self, _UserAccount.USER_PROPERTY, None)
        if current_user:
            user = self.getUser(current_user)
            if user:
                if user.edit_notecard(term, definition):
                    self.save_data()
                    return True
                return False


    def deleteNotecard(self, term: str) -> bool:
        current_user = getattr(self, _UserAccount.USER_PROPERTY, None)
        if current_user:
            user = self.getUser(current_user)
            if user:
                if user.delete_notecard(term):
                    self.save_data()
                    return True
                return False

class User:

    def init(self, username: str, password: str):
        self._username = username
        self._password = password
        self._notecards = []
    
    def getUsername(self) -> str:
        return self._username
    
    def getPassword(self) -> str:
        return self._password
    
    def add_notecard(self, term: str, definition: str):
        self._notecards.append({"term": term, "definition": definition})
    
    def edit_notecard(self, term: str, definition: str) -> bool:
        for notecard in self._notecards:
            if notecard["term"] == term:
                notecard["definition"] = definition
                return True
        return False
    
    def delete_notecard(self, term: str) -> bool:
        for notecard in self._notecards:
            if notecard["term"] == term:
                self._notecards.remove(notecard)
                return True
        return False
    
    def to_dict(self):
        return {
            _UserAccount.USERNAME: self._username,
            _UserAccount.PASSWORD: self._password,
            "_notecards": self._notecards
        }
    
    @staticmethod
    def encode_user(user):
        if isinstance(user, _User):
            return user.to_dict()
    
    @staticmethod
    def decode_user(userDict):
        if _UserAccount.validateJSON(userDict):
            username = userDict[_UserAccount.USERNAME]
            password = userDict[_UserAccount.PASSWORD]
            user = _User(username, password)
            if "_notecards" in userDict:
                for notecard in userDict["_notecards"]:
                    user.add_notecard(notecard["term"], notecard["definition"])
            return username, user


class StudyNotecard:

    def init(self):
        self.auth_manager = _LocalAuthManager()
        
    def create_account(self, username: str, password: str) -> bool:
        return self.auth_manager.addUser(username, password)
    
    def login(self, username: str, password: str) -> bool:
        return self.auth_manager.login(username, password)
    
    def logout(self):
        self.auth_manager.logout()
    
    def create_notecard(self, term: str, definition: str) -> bool:
        return self.auth_manager.createNotecard(term, definition)
    
    def edit_notecard(self, term: str, definition: str) -> bool:
        return self.auth_manager.editNotecard(term, definition)
    
    def delete_notecard(self, term: str) -> bool:
        return self.auth_manager.deleteNotecard(term)
    
    def get_notecards(self) -> typing.List[typing.Dict[str, str]]:
        current_user = getattr(self.auth_manager, _UserAccount.USER_PROPERTY, None)
        if current_user:
            user = self.auth_manager.getUser(current_user)
            if user:
                return user._notecards
        return []
    
    def get_users(self) -> typing.List[str]:
        return self.auth_manager.getUsers()
    
class _TestUserAccount(unittest.TestCase):
    def test_user_schema(self):
        valid_user = {"__account__": True, "username": "johndoe", "password": "password123", "_notecards": []}
        invalid_user = {"__account__": "not_bool", "username": 123, "password": None, "_notecards": {}}

        self.assertTrue(_UserAccount.validateJSON(valid_user))
        self.assertFalse(_UserAccount.validateJSON(invalid_user))
        
    def test_create_user_account(self):
        user_account = UserAccount('johndoe', 'password123')
        self.assertEqual(user_account.username, 'johndoe')
        self.assertEqual(user_account.password, 'password123')
    
    def test_login(self):
        user_account = UserAccount('johndoe', 'password123')
        self.assertTrue(user_account.login('johndoe', 'password123'))
        self.assertFalse(user_account.login('johndoe', 'password456'))
        self.assertFalse(user_account.login('janedoe', 'password123'))
    
    def test_change_password(self):
        user_account = UserAccount('johndoe', 'password123')
        self.assertTrue(user_account.change_password('password456'))
        self.assertEqual(user_account.password, 'password456')
        self.assertFalse(user_account.change_password('password123'))
        self.assertEqual(user_account.password, 'password456')

class _TestUser(unittest.TestCase):
    def test_create_user(self):
        user_account = UserAccount('johndoe', 'password123')
        user = User('John', 'Doe', 'johndoe@example.com', user_account)
        self.assertEqual(user.first_name, 'John')
        self.assertEqual(user.last_name, 'Doe')
        self.assertEqual(user.email, 'johndoe@example.com')
        self.assertEqual(user.user_account.username, 'johndoe')
        self.assertEqual(user.user_account.password, 'password123')
    
    def test_update_profile(self):
        user_account = UserAccount('johndoe', 'password123')
        user = User('John', 'Doe', 'johndoe@example.com', user_account)
        self.assertTrue(user.update_profile('John', 'Doe', 'johndoe@example.com', 'newpassword123'))
        self.assertEqual(user.first_name, 'John')
        self.assertEqual(user.last_name, 'Doe')
        self.assertEqual(user.email, 'johndoe@example.com')
        self.assertEqual(user.user_account.username, 'johndoe')
        self.assertEqual(user.user_account.password, 'newpassword123')
           
    def test_add_notecard(self):
        user = _User("johndoe", "password123")
        self.assertEqual(len(user._notecards), 0)

        user.add_notecard("test term", "test definition")
        self.assertEqual(len(user._notecards), 1)
        self.assertEqual(user._notecards[0]["term"], "test term")
        self.assertEqual(user._notecards[0]["definition"], "test definition")

    def test_edit_notecard(self):
        user = _User("johndoe", "password123")
        user.add_notecard("test term", "test definition")

        self.assertTrue(user.edit_notecard("test term", "new definition"))
        self.assertFalse(user.edit_notecard("nonexistent term", "new definition"))
        self.assertEqual(user._notecards[0]["definition"], "new definition")

    def test_delete_notecard(self):
        user = _User("johndoe", "password123")
        user.add_notecard("test term", "test definition")

        self.assertTrue(user.delete_notecard("test term"))
        self.assertFalse(user.delete_notecard("nonexistent term"))
        self.assertEqual(len(user._notecards), 0)
    
class _TestLocalAuthManager(unittest.TestCase):
    def setUp(self):
        self.mock_file = MagicMock()
        self.mock_file.__enter__.return_value = self.mock_file
        self.mock_file.__exit__.return_value = False

    def test_load_data(self):
        with patch("auth_manager.open", return_value=self.mock_file) as mock_open:
            self.mock_file.read.return_value = '{"johndoe": {"username": "johndoe", "password": "password123", "_notecards": []}}'
            auth_manager = _LocalAuthManager()

            mock_open.assert_called_once_with("data.json")
            self.assertEqual(len(auth_manager._auth), 1)
            self.assertIsInstance(auth_manager._auth["johndoe"], _User)

    def test_save_data(self):
        with patch("auth_manager.open", return_value=self.mock_file) as mock_open:
            auth_manager = _LocalAuthManager()
            auth_manager._auth["johndoe"] = _User("johndoe", "newpassword")

            auth_manager.save_data()

            mock_open.assert_called_once_with("data.json", "w")
            self.mock_file.write.assert_called_once_with('{"johndoe": {"password": "newpassword", "username": "johndoe", "_notecards": []}}')

    def test_verify_user(self):
        auth_manager = _LocalAuthManager()
        auth_manager._auth["johndoe"] = _User("johndoe", "password123")

        self.assertTrue(auth_manager.verifyUser("johndoe", "password123"))
        self.assertFalse(auth_manager.verifyUser("johndoe", ""))
    
class _TestRequestHandler(unittest.TestCase):
    def test_create_request(self):
        user_account = UserAccount('johndoe', 'password123')
        user = User('John', 'Doe', 'johndoe@example.com', user_account)
        request_handler = RequestHandler()
        request_id = request_manager.create_request(user, 'I need help with my computer')
        self.assertEqual(len(request_manager.requests), 1)
        self.assertEqual(request_manager.requests[0].id, request_id)
        self.assertEqual(request_manager.requests[0].user, user)
        self.assertEqual(request_manager.requests[0].description, 'I need help with my computer')
    
    def test_get_requests(self):
        user_account1 = UserAccount('johndoe', 'password123')
        user1 = User('John', 'Doe', 'johndoe@example.com', user_account1)
        user_account2 = UserAccount('janedoe', 'password456')
        user2 = User('Jane', 'Doe', 'janedoe@example.com', user_account2)
        request_manager = RequestManager()
        request_manager.create_request(user1, 'I need help with my computer')
        request_manager.create_request(user2, 'I need help with my phone')
        requests = request_manager.get_requests()
        self.assertEqual(len(requests), 2)
        self.assertEqual(requests[0].user, user1)
        self.assertEqual(requests[1].user, user2)
        self.assertEqual(requests[0].description, 'I need help with my computer')
        self.assertEqual(requests[1].description, 'I need help with my phone')
        
    def test_update_request_status(self):
        user_account = UserAccount('johndoe', 'password123')
        user = User('John', 'Doe', 'johndoe@example.com', user_account)
        request_manager = RequestManager()
        request_id = request_manager.create_request(user, 'I need help with my computer')
        self.assertTrue(request_manager.update_request_status(request_id, 'In progress'))
        self.assertEqual(request_manager.requests[0].status, 'In progress')
        self.assertFalse(request_manager.update_request_status(2, 'In progress'))
        self.assertEqual(request_manager.requests[0].status, 'In progress')
        self.assertTrue(request_manager.update_request_status(request_id, 'Completed'))
        self.assertEqual(request_manager.requests[0].status, 'Completed')