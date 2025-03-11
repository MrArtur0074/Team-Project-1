import React from 'react';
import './AirPodsPage.css';

const AirPodsPage = () => {
  return (
    <div className="airpods-page">
      <h1>AirPods</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="AirPods" />
          <p>AirPods Pro</p>
          <p>Цена: $249</p>
        </div>
      </div>
    </div>
  );
};

export default AirPodsPage;