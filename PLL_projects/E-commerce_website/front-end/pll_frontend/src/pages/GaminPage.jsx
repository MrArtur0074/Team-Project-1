import React from 'react';
import './GaminPage.css';

const GaminPage = () => {
  return (
    <div className="gamin-page">
      <h1>Gamin</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="Gamin" />
          <p>Gaming Laptop</p>
          <p>Цена: $1499</p>
        </div>
      </div>
    </div>
  );
};

export default GaminPage;