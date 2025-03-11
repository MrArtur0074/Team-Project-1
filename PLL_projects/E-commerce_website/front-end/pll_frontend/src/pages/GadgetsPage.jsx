import React from 'react';
import './GadgetsPage.css';

const GadgetsPage = () => {
  return (
    <div className="gadgets-page">
      <h1>Гаджеты</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="Gadgets" />
          <p>Умные часы</p>
          <p>Цена: $99</p>
        </div>
      </div>
    </div>
  );
};

export default GadgetsPage;