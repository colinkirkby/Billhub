import './App.css';
import React, { useState } from 'react';
import Navbar from './components/navbar/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Redirect } from 'react-router';
import Dashboard from './components/pages/Dashboard';
import Account from './components/pages/Account';
import Maps from './components/pages/Maps';
import NewEntry from './components/pages/NewEntry';
import EntryList from './components/pages/EntryList';
import Homepage from "./components/pages/Homepage";
import Loginpage from "./components/pages/Loginpage";
import Signuppage from "./components/pages/Signuppage";
import PrivateRoute from '../src/PrivateRoute';

function App() {
  return (
      <Router>
        <Navbar />
        <Switch>
          <Route path = '/homepage' component = {Homepage} />
          <Redirect exact from = '/' to = '/homepage' />
          <Route path = '/maps' component = {Maps} />
          <Route path = '/login' component = {Loginpage} />
          <Route path = '/register' component = {Signuppage} />
          <PrivateRoute path = '/dashboard' loggedIn = { sessionStorage.getItem("access_token")} component = {Dashboard}/>
            <Route path = '/dashboard/account' component = {Account} />
            <Route path = '/dashboard/new-entry' component = {NewEntry} />
            <Route path = '/dashboard/entries' component = {EntryList} />
        </Switch>
      </Router>
  );
}

export default App;
