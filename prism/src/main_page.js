import React, { Component } from 'react';
import logo from './assets/icon.png';
import './App.css';
import StoryElement from './StoryElement';

export default class MainPage extends Component {
  render() {
    var elements = [];
    for(var i = 0; i < 10; i++) {
        elements.push(<StoryElement />);
    }

    return (
      <div className="App mainpage" >
          <div className="navbar">
          <img src={logo}  className="App-logo" alt="logo" />
        </div>
        <div style={{marginTop: 200}}>
          <p className="bigfont" style={{'margin-top': 20}} > View Stories </p>
         {elements}
         </div>
      </div>
    );
  }
}