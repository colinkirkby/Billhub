import React, {Component} from 'react'
import { Grid, Paper, Avatar, Typography, TextField, Button } from '@material-ui/core'
import AddCircleOutlineOutlinedIcon from '@material-ui/icons/AddCircleOutlineOutlined';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import Checkbox from '@material-ui/core/Checkbox';
import { render } from '@testing-library/react';
import UserService from '../../UserService/UserService';
import { Redirect } from 'react-router';


class registerUserComponent extends Component{
    constructor(props){
        super(props)

        this.state={
            firstName: '',
            lastName: '',
            email: '',
            password: ''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.saveUser = this.saveUser.bind(this);
    }

    logSomething = () => {
        console.log("SAVE USER CLICK")
    }

    saveUser = (e) =>{
        e.preventDefault();
        let user={firstName: this.state.firstName, lastName: this.state.lastName, email: this.state.email, password:this.state.password};
        console.log('user => ' + JSON.stringify(user));
        UserService.createUser(user).then(response =>{
            //TODO, the page redirect
        });

    }
    

    changeFirstNameHandler=(event) =>{
        this.setState({firstName: event.target.value});
    }
    changeLastNameHandler=(event) =>{
        this.setState({lastName: event.target.value});
    }
    changeEmailHandler=(event) =>{
        this.setState({email: event.target.value});
    }
    changePasswordHandler=(event) =>{
        this.setState({password: event.target.value});
    }

    render() {
    const paperStyle = { padding: '30px 20px', width: 300, margin: "20px auto" }
    const headerStyle = { margin: 0 }
    const avatarStyle = { backgroundColor: '#1bbd7e' }
    const marginTop = { marginTop: 5 }



    return (
        <Grid>
            <Paper elevation={20} style={paperStyle}>
                <Grid align='center'>
                    <Avatar style={avatarStyle}>
                        <AddCircleOutlineOutlinedIcon />
                    </Avatar>
                    <h2 style={headerStyle}>Sign Up</h2>
                    <Typography variant='caption' gutterBottom>Please fill this form to create an account !</Typography>
                </Grid>
                <form>
                    <div className ="form-group">
                        <label> First Name: </label>
                        <input placeholder="First Name" name="firstName" className="form-control"
                            value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                    </div>
                    
                    <div className ="form-group">
                        <label> Last Name: </label>
                        <input placeholder="Last Name" name="lastName" className="form-control"
                            value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                    </div>
                    
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

                    <button className="btn btn-success" onClick={this.saveUser}>Register</button>
                    {/* <button className="btn btn-success" onClick={this.logSomething}>Register</button> */}
                </form>
            </Paper>
        </Grid>
    )
}
}
export default registerUserComponent;