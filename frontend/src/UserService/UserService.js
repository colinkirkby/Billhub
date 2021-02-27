import axios from 'axios';
import { Redirect } from 'react-router';


const USER_API_BASE_URL = "http://localhost:8080/api/v1/registration";

class UserService{
    createUser(user){
    return axios.post(USER_API_BASE_URL, user);
    }
}

export default new UserService()