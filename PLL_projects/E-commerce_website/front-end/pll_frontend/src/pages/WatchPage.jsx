import React from 'react';
import './WatchPage.css';

const WatchPage = () => {
  return (
    <div className="watch-page">
      <h1>Watch</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="Watch" />
          <p>Apple Watch Series 8</p>
          <p>Цена: $399</p>
        </div>
      </div>
    </div>
  );
};

export default WatchPage;