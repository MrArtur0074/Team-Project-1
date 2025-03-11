import React from 'react';
import './AudioPage.css';

const AudioPage = () => {
  return (
    <div className="audio-page">
      <h1>Audio</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="Audio" />
          <p>Наушники</p>
          <p>Цена: $199</p>
        </div>
      </div>
    </div>
  );
};

export default AudioPage;