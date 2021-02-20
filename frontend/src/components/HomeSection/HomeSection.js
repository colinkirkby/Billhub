import React from 'react';
import '../../App.css';
import { Button } from '../button/Button';
import './HomeSection.css';

function HomeSection() {
  return (
    <div className='home-container'>
      <h1>The Future of Your Finance</h1>
      <p>What are you waiting for?</p>
      <div className='home-btns'>
        <Button
          className='btns'
          buttonStyle='btn--outline'
          buttonSize='btn--large'
        >
          GET STARTED
        </Button>
      </div>
    </div>
  );
}

export default HomeSection;