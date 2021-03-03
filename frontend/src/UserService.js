import axios from 'axios';
import { Redirect } from 'react-router';


const REGISTRATION_API = "http://localhost:8080/api/v1/registration";
const LOGIN_API = "http://localhost:8080/api/v1/login";

// *** Input correct URLs once they're ready *** //
// const ACCOUNT_API = "http://localhost:8080/api/v1";
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
    // getAccount(user) {
        // return axios.post(ACCOUNT_API, user);
    // }

    // *** Retrieve user budgeting information from the database *** //
    // getData(user) {
        // return axios.post(DATA_API, user);
    // }
}

export default new UserService()