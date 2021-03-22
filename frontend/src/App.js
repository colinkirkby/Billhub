import './App.css';
import React, { Component, useState } from 'react';
import Navbar from './components/navbar/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Redirect } from 'react-router';
import Dashboard from './components/pages/Dashboard';
import Account from './components/pages/Account';
import Maps from './components/pages/Maps';
import Glossary from './components/pages/Glossary';
import ResourcePage from './components/pages/ResourcePage';
// import NewEntry from './components/pages/NewEntry';
import EntryList from './components/pages/EntryList';
import Homepage from "./components/pages/Homepage";
import Loginpage from "./components/pages/Loginpage";
import Signuppage from "./components/pages/Signuppage";
import PrivateRoute from '../src/PrivateRoute';
import Login from './components/login/login';
import Logout from './components/logout/logout';

class App extends Component {
  constructor(props){
    super(props);
    this.state={
      isLoggedIn:false
    }
    this.handleLogin=this.handleLogin.bind(this);
    this.handleLogout=this.handleLogout.bind(this);
  }

  handleLogin(){
    this.setState({isLoggedIn : true});
  }

  handleLogout(){
    this.setState({isLoggedIn : false});
  }

  componentDidMount(){
    if (sessionStorage.getItem("access_token") != null){
      this.setState({isLoggedIn:true});
    }
    else{
      this.setState({isLoggedIn:false});
    }
  }

  render(){
  return (
      <Router>
        <Navbar isLoggedIn={this.state.isLoggedIn}/>
        <Switch>
          <Route path = '/homepage' component = {Homepage} />
          <Redirect exact from = '/' to = '/homepage' />
          <Route path = '/maps' component = {Maps} />
          <Route path = '/financial-glossary' component = {Glossary} />
          <Route path = '/resources' component = {ResourcePage} />
          <Route path = '/login' render={(props)=>(<Login loginHandler={this.handleLogin} {...props}/>)} />
          <Route path = '/logout' render={(props)=>(<Logout logoutHandler={this.handleLogout} {...props}/>)} />
          <Route path = '/register' component = {Signuppage} />
          <PrivateRoute path = '/dashboard' loggedIn = { sessionStorage.getItem("access_token")} component = {Dashboard}/>
          <PrivateRoute path = '/account' loggedIn = { sessionStorage.getItem("access_token")} component = {Account} />

            {/* <Route path = '/dashboard/account' component = {Account} /> */}
            {/* <Route path = '/dashboard/new-entry' component = {NewEntry} /> */}
            <PrivateRoute path = '/entries' loggedIn = { sessionStorage.getItem("access_token")} component = {EntryList} />
        </Switch>
      </Router>
  );
}
}

export default App;
