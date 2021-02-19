import './App.css';
import React, { useState } from 'react';
import Navbar from './components/navbar/Navbar';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Dashboard from './components/pages/Dashboard';
import Account from './components/pages/Account';
import Maps from './components/pages/Maps';
import NewEntry from './components/pages/NewEntry';
import EntryList from './components/pages/EntryList';

function App() {

  return (
      <Router>
        <Navbar />
        <Switch>
          <Route path = '/' exact component = {Maps} />
          <Route path = '/maps' component = {Maps} />
          <Route path = '/dashboard' component = {Dashboard} />
          <Route path = '/account' component = {Account} />
          <Route path = '/new-entry' component = {NewEntry} />
          <Route path = '/entries' component = {EntryList} />
        </Switch>
      </Router>
  );
}

export default App;
