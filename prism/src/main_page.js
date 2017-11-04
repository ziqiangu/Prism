import React, { Component } from 'react';
import logo from './assets/icon.png';
import './App.css';
import StoryElement from './StoryElement';
import {Link} from 'react-router-dom';

export default class MainPage extends Component {
  render() {
    var elements = [];
    for(var i = 0; i < 10; i++) {
        elements.push(<StoryElement />);
    }

    return (
      <div className="App mainpage" >
         <p className="title"> PRISM </p>
          <p className="bigfont" > View Stories </p>
         {elements}
         
      </div>
    );
  }
}