import React from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import "./iPhonePage.css";

const iPhones = [
  "iPhone 16 Pro Max",
  "iPhone 16 Pro",
  "iPhone 16 Plus",
  "iPhone 16",
  "iPhone 15 Pro Max",
  "iPhone 15 Pro",
  "iPhone 15",
  "iPhone 14",
  "iPhone 13",
  "iPhone 11",
];

const iPhonePage = () => {
  return (
    <div className="iphone-container">
      <Header />
      <h1>iPhone</h1>
      <p className="subtitle">Великолепие в каждой детали</p>
      <div className="iphone-list">
        {iPhones.map((model, index) => (
          <div key={index} className="iphone-item">
            {model}
          </div>
        ))}
        
      </div><Footer/>
    </div>
  );
};

export default iPhonePage;
