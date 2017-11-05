import React, { Component } from 'react';
import logo from './assets/icon.png';
import './App.css';
import { Link } from 'react-router-dom';

export default class StoryElement extends Component {
  render() {
    return (
      <div className="element-container">
          <p className="bigfont" > Title or Premise of the Story Goes Here </p>
          <hr />
          <p className="bigfont"> Number of Words Remaining </p>
      </div>
    );
  }
}