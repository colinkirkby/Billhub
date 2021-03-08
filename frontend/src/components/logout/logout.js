import React, {Component, useState} from 'react'
import { Grid,Paper, Avatar, TextField, Button, Typography,Link } from '@material-ui/core'
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
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

    }

    handleLogout(){
        sessionStorage.clear();
        this.props.logoutHandler();
        window.location.replace("/");
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
                    <h2>Sign Out</h2>
                </Grid>
                <form>                    

                    <Button className="btn btn-success" onClick={this.handleLogout} type='submit' color='primary' variant="contained" style={btnstyle} fullWidth>Sign Out</Button>
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
