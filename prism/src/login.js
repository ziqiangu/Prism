import React, { Component } from 'react';
import logo from './assets/icon.png';
import './App.css';
import { Link } from 'react-router-dom';

export default class Login extends Component {
  render() {
    return (
      <div className="App">
       <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to Prism</h1>
        </header>
        <input className="login" type="text" placeholder="Username"/> <br />
        <input className="login" type="text" placeholder="Password" />  <br />
        <Link to="/main">
          <input className="login-button" type="submit" value="Login" />
        </Link>
        
      </div>
    );
  }
}

