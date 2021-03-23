import axios from 'axios';

const REGISTRATION_API = "http://localhost:8080/api/v1/registration";
const LOGIN_API = "http://localhost:8080/api/v1/login";
const ACCOUNT_API = "http://localhost:8080/api/v1/account";
const ENTRIES_API = "http://localhost:8080/api/v1/entries";
const DELETE_ENTRY_API = "http://localhost:8080/api/v1/remove-entry/{entryID}";

class UserService{
    
    // *** Save a new user into the database *** //
    createUser(user){
        return axios.post(REGISTRATION_API, user);
    }

    // *** Check if email and password entered on login are stored in the database *** //
    checkCredential(user){
        return axios.post(LOGIN_API, user);
    }

    // *** Retrieve user account information from the database *** //
    getAccount(userEmail) {
        return axios.get(ACCOUNT_API, {
            params: {
                ID: userEmail
            }
        });
    }

    // *** Retrieve the existing budget entries for a user from the database *** //
    getEntries(userEmail) {
        return axios.get(ENTRIES_API, {
            params: {
                ID: userEmail
            }
        });
    }
}

export default new UserService()