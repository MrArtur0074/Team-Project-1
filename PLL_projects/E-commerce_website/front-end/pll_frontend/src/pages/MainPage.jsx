import React from 'react';
import Slider from '../components/Slider';
import Navigator from '../components/Navigator';
import './MainPage.css';

const MainPage = () => {
  return (
    <div className="main-page">
      <Slider />
      <Navigator />
      <h1>Популярные товары</h1>
      {/* Здесь можно добавить список популярных товаров */}
    </div>
  );
};

export default MainPage;