import React from 'react';
import './BasketPage.css';

const BasketPage = () => {
  const basketItems = [
    { id: 1, name: 'iPhone 15 Pro Max', price: 129990, quantity: 1 },
    { id: 2, name: 'AirPods Pro', price: 24990, quantity: 2 },
  ];

  const totalPrice = basketItems.reduce((total, item) => total + item.price * item.quantity, 0);

  return (
    <div className="basket-page">
      <h1>Корзина</h1>
      <div className="basket-items">
        {basketItems.map((item) => (
          <div key={item.id} className="basket-item">
            <h3>{item.name}</h3>
            <p>Цена: {item.price} руб.</p>
            <p>Количество: {item.quantity}</p>
            <p>Итого: {item.price * item.quantity} руб.</p>
          </div>
        ))}
      </div>
      <div className="total-price">
        <h3>Общая сумма: {totalPrice} руб.</h3>
        <button className="checkout-button">Оформить заказ</button>
      </div>
    </div>
  );
};

export default BasketPage;