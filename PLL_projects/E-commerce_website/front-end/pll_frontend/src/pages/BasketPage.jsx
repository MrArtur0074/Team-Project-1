import React from 'react';
import './BasketPage.css';

const BasketPage = () => {
  return (
    <div className="basket-page">
      <h1>Корзина</h1>
      <div className="basket-items">
        {/* Здесь можно отображать товары в корзине */}
        <div className="basket-item">
          <img src="https://via.placeholder.com/100" alt="Товар" />
          <p>iPhone 15 Pro Max</p>
          <p>Цена: $999</p>
          <button>Удалить</button>
        </div>
      </div>
      <button className="checkout">Оформить заказ</button>
    </div>
  );
};

export default BasketPage;