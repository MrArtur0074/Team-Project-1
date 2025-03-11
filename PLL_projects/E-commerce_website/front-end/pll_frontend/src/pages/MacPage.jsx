import React from 'react';
import './MacPage.css';

const MacPage = () => {
  return (
    <div className="mac-page">
      <h1>Mac</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="Mac" />
          <p>MacBook Pro</p>
          <p>Цена: $1999</p>
        </div>
      </div>
    </div>
  );
};

export default MacPage;