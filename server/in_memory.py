import os
import typing
import json
import jsonschema
from jsonschema import validate


class UserAccount:
    USER_PROPERTY = "account"
    USERNAME = "username"
    PASSWORD = "password"

    user_schema = {
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
    def validate_json(json_data):
        try:
            validate(instance=json_data, schema=UserAccount.user_schema)
        except jsonschema.exceptions.ValidationError as err:
            return False
        return True


class _LocalAuthManager(AuthManager):

    def init(self, file: str="data.json"):
        # self.system_credentials: dict[str, User] = {}
        self.data_file = file
        self.load_data()
    
    def load_data(self):
        if not os.path.exists(self.data_file):
            return
        with open(self.data_file) as user_data:
            data = user_data.read()
            self.system_credentials = json.loads(data, object_hook=User.decode_user)
            user_data.close()
    
    def save_data(self):
        with open(self.data_file, 'w') as file:
            json.dump(self.system_credentials, file, default=User.encode_user, indent=4, sort_keys=True)
        
    def verify_user(self, username: str, password: str) -> bool:
        if username in self.system_credentials and self.system_credentials[username].get_password() == password:
            return True
        return False
    
    def add_user(self, username: str, password: str, account: dict) -> bool:
        if username in self.system_credentials:
            return False
        is_valid = UserAccount.validate_json(account)
        if is_valid:
            credentials = User(username, password, account)
            self.system_credentials[username] = credentials
            self.save_data()
            return True
        return False
    
    def update_user_info(self, username: str) -> bool:
        if username in self.system_credentials:
            self.save_data()
            return True
        return False
    
    def remove_user(self, username: str) -> bool:
        if username not in self.system_credentials:
            return False
        self.system_credentials.pop(username)
        self.save_data()
        return True
    
    def update_user_password(self, username: str, password: str) -> bool:
        if username not in self.system_credentials:
            return False
        self.system_credentials[username].set_password(password)
        self.save_data()
        return True
    
    def get_user(self, username: str) -> typing.Optional[User]:
        if username not in self.system_credentials:
            raise Exception("System with this username unfound")
        return self.system_credentials[username]
    
    def login(self, username: str, password: str) -> bool:
        if self.verify_user(username, password):
            setattr(self, UserAccount.USER_PROPERTY, username)
            return True
        return False
    
    def logout(self):
        setattr(self, UserAccount.USER_PROPERTY, None)
    
    def create_notecard(self, term: str, definition: str) -> bool:
        current_user = getattr(self, UserAccount.USER_PROPERTY, None)
        if current_user:
            user = self.get_user(current_user)
            if user:
                user.add_notecard(term, definition)
                self.save_data()
                return True
    
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

    def __init__(self, username: str, password: str, account: dict):
        self._username = username
        self._password = password
        self._account = account
        self._notecards = []

    def getUsername(self) -> str:
        return self._username
    
    def getPassword(self) -> str:
        return self._password
    
    def getAccount(self) -> dict:
        return self._account
    
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
    
    def __init__(self):
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
        user = self.auth_manager.getUser(getattr(self.auth_manager, _UserAccount.USER_PROPERTY, None))
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

    def setUp(self):
        self.user_account = UserAccount('johndoe', 'password123')
        self.user = User('John', 'Doe', 'johndoe@example.com', self.user_account)
        
    def test_create_user(self):
        self.assertEqual(self.user.first_name, 'John')
        self.assertEqual(self.user.last_name, 'Doe')
        self.assertEqual(self.user.email, 'johndoe@example.com')
        self.assertEqual(self.user.user_account.username, 'johndoe')
        self.assertEqual(self.user.user_account.password, 'password123')
    
    def test_update_profile(self):
        self.assertTrue(self.user.update_profile('John', 'Doe', 'johndoe@example.com', 'newpassword123'))
        self.assertEqual(self.user.first_name, 'John')
        self.assertEqual(self.user.last_name, 'Doe')
        self.assertEqual(self.user.email, 'johndoe@example.com')
        self.assertEqual(self.user.user_account.username, 'johndoe')
        self.assertEqual(self.user.user_account.password, 'newpassword123')
           
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

    def setUp(self):
        self.user1_account = UserAccount('johndoe', 'password123')
        self.user1 = User('John', 'Doe', self.user1_account)
        self.user2_account = UserAccount('janedoe', 'password456')
        self.user2 = User('Jane', 'Doe', self.user2_account)
        self.request_manager = RequestManager()

    def test_create_request(self):
        request_id = self.request_manager.create_request(self.user1, 'I need help with my computer')
        self.assertEqual(len(self.request_manager.requests), 1)
        self.assertEqual(self.request_manager.requests[0].id, request_id)
        self.assertEqual(self.request_manager.requests[0].user, self.user1)
        self.assertEqual(self.request_manager.requests[0].description, 'I need help with my computer')

    def test_get_requests(self):
        self.request_manager.create_request(self.user1, 'I need help with my computer')
        self.request_manager.create_request(self.user2, 'I need help with my phone')
        requests = self.request_manager.get_requests()
        self.assertEqual(len(requests), 2)
        self.assertEqual(requests[0].user, self.user1)
        self.assertEqual(requests[1].user, self.user2)
        self.assertEqual(requests[0].description, 'I need help with my computer')
        self.assertEqual(requests[1].description, 'I need help with my phone')

    def test_update_request_status(self):
        request_id = self.request_manager.create_request(self.user1, 'I need help with my computer')
        self.assertTrue(self.request_manager.update_request_status(request_id, 'In progress'))
        self.assertEqual(self.request_manager.requests[0].status, 'In progress')
        self.assertFalse(self.request_manager.update_request_status(2, 'In progress'))
        self.assertEqual(self.request_manager.requests[0].status, 'In progress')
        self.assertTrue(self.request_manager.update_request_status(request_id, 'Completed'))
        self.assertEqual(self.request_manager.requests[0].status, 'Completed')
