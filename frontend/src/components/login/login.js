import React, {Component, useState} from 'react'
import { Redirect, Route } from "react-router-dom";
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import Signuppage from '../pages/Signuppage';
import axios from 'axios';
/*
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
*/
import LoginUserService from '../../UserService/LoginUserService';

class loginUserComponent extends Component{
    constructor(props){
        super(props)

        this.state={
            email: '',
            password: ''
        }
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.saveUser = this.saveUser.bind(this);
    }

    saveUser = (e) =>{
        e.preventDefault();
        let user={email: this.state.email, password:this.state.password};
        console.log('user => ' + JSON.stringify(user));
        LoginUserService.checkCredential(user)
            .then((response) => {
                localStorage.setItem("access_token", "loggedIn");
                window.location.replace("/dashboard");
            //TODO, the page redirect
        });
    }

    changeEmailHandler=(event) =>{
        this.setState({email: event.target.value});
    }
    changePasswordHandler=(event) =>{
        this.setState({password: event.target.value});
    }

    render() {
    const paperStyle={padding :20,height:'70vh',width:280, margin:"20px auto"}
    const avatarStyle={backgroundColor:'#1bbd7e'}
    const btnstyle={margin:'8px 0'}

    return(
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                     <Avatar style={avatarStyle}><LockOutlinedIcon/></Avatar>
                    <h2>Sign In</h2>
                </Grid>
                <form>                    
                    <div className ="form-group">
                        <label> Email: </label>
                        <input placeholder="Email Address" name="email" className="form-control"
                            value={this.state.email} onChange={this.changeEmailHandler}/>
                    </div>
                    
                    <div className ="form-group">
                        <label> Password: </label>
                        <input placeholder="Password" name="password" className="form-control"
                            value={this.state.password} onChange={this.changePasswordHandler}/>
                    </div>

                    <button className="btn btn-success" onClick={this.saveUser}>Sign In</button>
                </form>

                {/* <Typography > */}
                     {/* <Link href="#" > */}
                        {/* Forgot password ? */}
                    {/* </Link> */}
                {/* </Typography> */}
                {/* <Typography > Don't have an account ? */}
                    {/* <Link to = '/register' className = 'button-link'> */}
                        {/* <Button>Register Here</Button> */}
                    {/* </Link> */}
                {/* </Typography> */}
                {/* <Link to = '/register'>Sign Up</Link> */}
            </Paper>
        </Grid>
    )
}
}

export default loginUserComponent