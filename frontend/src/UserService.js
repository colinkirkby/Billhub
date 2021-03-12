import axios from 'axios';

const REGISTRATION_API = "http://localhost:8080/api/v1/registration";
const LOGIN_API = "http://localhost:8080/api/v1/login";
const ACCOUNT_API = "http://localhost:8080/api/v1/account";
const CHANGE_EMAIL_API = "http://localhost:8080/api/v1/change-email";

// *** Input correct URLs once they're ready *** //
// const DATA_API = "http://localhost:8080/api/v1";

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
}

export default new UserService()