import React, {useState} from 'react';
import axios from 'axios';
import { Redirect } from 'react-router';


const REGISTRATION_API = "http://localhost:8080/api/v1/registration";
const LOGIN_API = "http://localhost:8080/api/v1/login";
// const [loggedIn, setLoggedIn] = useState(false);

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
        /*
        var jsonResponse = axios.post(LOGIN_API, user);
        */
        return axios.post(LOGIN_API, user);
        /*
        if (jsonResponse == null)
        {
            console.log("ERROR: bad JSON response");
        }
        
        if (jsonResponse.message == "Invalid username or password")
        {
            console.log(jsonResponse);
            console.log("ERROR: invalid credentials");
            return false;
        }


        else
        {
            console.log(jsonResponse.token);
            sessionStorage.setItem("access_token", jsonResponse.token);
            return true;
        }
        */
    }

    // *** Retrieve user account information from the database *** //
    // getAccount(user) {
        // var jsonResponse = axios.get(ACCOUNT_API, user);
        // return jsonResponse;
    // }

    // *** Retrieve user budgeting information from the database *** //
    // getData(user) {
        // return axios.post(DATA_API, user);
    // }
}

export default new UserService()