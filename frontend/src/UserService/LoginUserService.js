import axios from 'axios';

const USER_API_BASE_URL = "http://localhost:8080/api/v1/login";

class LoginUserService{
    checkCredential(user){
        return axios.post(USER_API_BASE_URL, user);
    }

    // localStorage.setItem("access_token", "loggedIn");

}

export default new LoginUserService()