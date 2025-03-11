import React from 'react';
import './AccessoriesPage.css';

const AccessoriesPage = () => {
  return (
    <div className="accessories-page">
      <h1>Аксессуары</h1>
      <div className="product-list">
        <div className="product-item">
          <img src="https://via.placeholder.com/200" alt="Accessories" />
          <p>Чехол для iPhone</p>
          <p>Цена: $29</p>
        </div>
      </div>
    </div>
  );
};

export default AccessoriesPage;