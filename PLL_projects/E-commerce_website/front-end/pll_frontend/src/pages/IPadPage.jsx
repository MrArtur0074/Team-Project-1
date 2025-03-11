import React from 'react';
import './IPadPage.css';

const IPadPage = () => {
  return (
    <div className="ipad-page">
      <h1>iPad</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="iPad" />
          <p>iPad Pro</p>
          <p>Цена: $799</p>
        </div>
      </div>
    </div>
  );
};

export default IPadPage;