import React from 'react';
import './IPhonePage.css';

const IPhonePage = () => {
  return (
    <div className="iphone-page">
      <h1>iPhone</h1>
      <div className="product-list">
        {/* Здесь можно отображать список товаров */}
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="iPhone" />
          <p>iPhone 15 Pro Max</p>
          <p>Цена: $999</p>
        </div>
      </div>
    </div>
  );
};

export default IPhonePage;