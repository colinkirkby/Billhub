import React, {Component, useState} from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
// import Signuppage from '../pages/Signuppage';
// import { Redirect, Route } from "react-router-dom";
// import FormControlLabel from '@material-ui/core/FormControlLabel';
// import Checkbox from '@material-ui/core/Checkbox';

import UserService from '../../UserService';

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
        if (this.state.email == '')
        {
            window.alert("Email cannot be empty");
            return;
        }

        if (this.state.password == '')
        {
            window.alert("Password cannot be empty");
            return;
        }

        let user={email: this.state.email, password:this.state.password};


        console.log('user => ' + JSON.stringify(user));
        UserService.checkCredential(user)
            .then(res => {
                if (res.data.message=="Success"){
                    console.log(res.data.message);
                    const {token} = res.data.token;
                    localStorage.setItem("access_token", token);
                    window.location.replace("/dashboard");
                }
                else{
                    window.alert("Invalid username or password. Please try again!");
                }
        });
    }

    changeEmailHandler=(event) =>{
        this.setState({email: event.target.value});
    }
    changePasswordHandler=(event) =>{
        this.setState({password: event.target.value});
    }

    render() {
    const paperStyle={padding :20,height:'40vh',width:280, margin:"20px auto"}
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
                        <TextField label="Email" placeholder="Email Address" name="email" className="form-control"
                            value={this.state.email} onChange={this.changeEmailHandler} fullWidth required/>
                    </div>
                    
                    <div className ="form-group">
                        <TextField label="Password" placeholder="Password" name="password" className="form-control"
                            value={this.state.password} onChange={this.changePasswordHandler} fullWidth required/>
                    </div>

                    <Button className="btn btn-success" onClick={this.saveUser} type='submit' color='primary' variant="contained" style={btnstyle} fullWidth>Sign In</Button>
                </form>

                <Typography> Don't have an account? Check out the "Register" tab!</Typography>

                {/* <Typography > */}
                     {/* <Link href="#" > */}
                        {/* Forgot password ? */}
                    {/* </Link> */}
                {/* </Typography> */}
            </Paper>
        </Grid>
    )
}
}

export default loginUserComponent
