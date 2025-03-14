import React from 'react';
import Header from '../components/Header';
import Slider from '../components/Slider';
import SliderTwo from '../components/SliderTwo';
import Navigator from '../components/Navigator';
import './MainPage.css';
import Footer from '../components/Footer';

const MainPage = () => {
  return (
    <div className="main-page">
      <Header />
      <Navigator />
      <h1>Популярные товары</h1>
      {/* Здесь можно добавить список популярных товаров */}
      <Slider />
      <h1>линейка продуктов</h1>
      <SliderTwo />
      <Footer />
    </div>
  );
};

export default MainPage;