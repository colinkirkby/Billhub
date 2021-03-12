import React, {Component, useState} from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOpenOutlinedIcon from '@material-ui/icons/LockOpenOutlined';
import '../pages/General.css';
// import Signuppage from '../pages/Signuppage';
// import { Redirect, Route } from "react-router-dom";
// import FormControlLabel from '@material-ui/core/FormControlLabel';
// import Checkbox from '@material-ui/core/Checkbox';

import UserService from '../../UserService';

class logout extends Component{
    constructor(props){
        super(props)

        this.state={

        }
        this.handleLogout = this.handleLogout.bind(this);
        this.handleCancel = this.handleCancel.bind(this);

    }

    handleLogout(){
        sessionStorage.clear();
        this.props.logoutHandler();
        this.props.history.push('/');
    }

    handleCancel(){
        this.props.history.push('/dashboard');
    }

    render() {
    const paperStyle={padding :20,height:350,width:280, margin:"20px auto"}
    const avatarStyle={backgroundColor:'#1bbd7e'}
    const btnstyle={margin:'8px 0'}
    const typostyle={margin:'28px 0'}

    return(
        <Grid>
            <Paper elevation={10} style={paperStyle}>
                <Grid align='center'>
                    <div className = 'page-title'>
                        <h1>Sign Out</h1>
                    </div>
                     <Avatar style={avatarStyle}><LockOpenOutlinedIcon/></Avatar>
                </Grid>
                <form>                    
                    <Typography
                        style={typostyle} 
                        align = 'center'
                    > 
                        You are attempting to log out of BillHub.
                    </Typography>
                    <Button
                        className="btn btn-success" 
                        onClick={this.handleLogout} 
                        type='submit' 
                        color='primary' 
                        variant="contained" 
                        style={btnstyle} 
                        fullWidth
                    >
                        Sign Out</Button>
                    <Button
                        className="btn btn-success" 
                        onClick={this.handleCancel} 
                        type='submit' 
                        color='primary' 
                        variant="contained" 
                        style={btnstyle} 
                        fullWidth
                    >
                        Cancel</Button>
                    
                </form>


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

export default logout
