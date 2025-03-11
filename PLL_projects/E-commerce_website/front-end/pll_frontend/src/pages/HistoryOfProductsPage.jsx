import React from 'react';
import './HistoryOfProductsPage.css';

const HistoryOfProductsPage = () => {
  return (
    <div className="history-page">
      <h1>История покупок</h1>
      <div className="history-list">
        <div className="history-item">
          <p>iPhone 15 Pro Max - $999</p>
          <p>Дата покупки: 01.10.2023</p>
        </div>
      </div>
    </div>
  );
};

export default HistoryOfProductsPage;