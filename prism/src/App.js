import React, { Component } from 'react';
import logo from './assets/icon.png';
import './App.css';
import Login from './login.js';
import MainPage from './main_page.js';
import {
    BrowserRouter as Router,
    Route,
    Link
  } from 'react-router-dom';

class App extends Component {
  render() {
    return (
        <Router>
            <div>
                <Route exact path="/" component={Login} />
                <Route path="/main" component={MainPage} />
            </div>
        </Router>
  
    );
  }
}

export default App;