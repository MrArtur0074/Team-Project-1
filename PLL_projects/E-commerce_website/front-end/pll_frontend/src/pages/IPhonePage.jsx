import React from 'react';
import './iPhonePage.css';
import Header from '../components/Header'; // Импортируйте Header
import Footer from '../components/Footer'; // Импортируйте Footer

const iPhonePage = () => {
  const models = [
    'iPhone 16 Pro Max',
    'iPhone 16 Pro',
    'iPhone 16 Plus',
    'iPhone 16',
    'iPhone 15 Pro Max',
    'iPhone 15 Pro',
    'iPhone 15',
    'iPhone 14',
    'iPhone 13',
    'iPhone 11',
  ];
  return (
    <div className="iphone-page">
      <Header /> {/* Добавьте Header */}
      <h1>iPhone</h1>
      <p className="tagline">Великолепие в каждой детали</p>
      <div className="models-list">
        {models.map((model, index) => (
          <div key={index} className="model-item">
            {model}
          </div>
        ))}
      </div>
    </div>
  );
};
export default iPhonePage;