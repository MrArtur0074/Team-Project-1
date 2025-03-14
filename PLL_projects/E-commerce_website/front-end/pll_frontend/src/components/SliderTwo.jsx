import React, { useState } from "react";
import "./SliderTwo.css"; // Подключаем стили

const categories = ["iPhone", "Mac", "iPad", "Watch", "AirPods"];

const slidesData = {
  iPhone: [
    { id: 1, text: "iPhone 15 Pro" },
    { id: 2, text: "iPhone 14" },
    { id: 3, text: "iPhone SE" },
    { id: 4, text: "iPhone 13 Mini" },
    { id: 5, text: "iPhone 12" },
  ],
  Mac: [
    { id: 6, text: "MacBook Air M2" },
    { id: 7, text: "MacBook Pro 16" },
    { id: 8, text: "iMac 24" },
    { id: 9, text: "Mac Studio" },
    { id: 10, text: "Mac Mini" },
  ],
  iPad: [
    { id: 11, text: "iPad Pro 12.9" },
    { id: 12, text: "iPad Air" },
    { id: 13, text: "iPad 10" },
    { id: 14, text: "iPad Mini" },
    { id: 15, text: "iPad 9" },
  ],
  Watch: [
    { id: 16, text: "Apple Watch Ultra" },
    { id: 17, text: "Apple Watch Series 9" },
    { id: 18, text: "Apple Watch SE" },
    { id: 19, text: "Apple Watch 8" },
    { id: 20, text: "Apple Watch Nike" },
  ],
  AirPods: [
    { id: 21, text: "AirPods Pro 2" },
    { id: 22, text: "AirPods 3" },
    { id: 23, text: "AirPods Max" },
    { id: 24, text: "AirPods 2" },
    { id: 25, text: "Beats Fit Pro" },
  ],
};

const SliderTwo = () => {
  const [activeCategory, setActiveCategory] = useState("iPhone");
  const [currentIndex, setCurrentIndex] = useState(0);

  const slides = slidesData[activeCategory];

  const nextSlide = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex + 1 > slides.length - 3 ? 0 : prevIndex + 1
    );
  };

  const prevSlide = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex - 1 < 0 ? slides.length - 3 : prevIndex - 1
    );
  };

  return (
    <div className="slider-two-container">
      {/* Кнопки выбора категории */}
      <div className="categories">
        {categories.map((category) => (
          <button
            key={category}
            className={category === activeCategory ? "active" : ""}
            onClick={() => {
              setActiveCategory(category);
              setCurrentIndex(0); // Обнуляем индекс при смене категории
            }}
          >
            {category}
          </button>
        ))}
      </div>

      {/* Слайдер */}
      <div className="slider-two-wrapper">
        <button className="slider-two-button prev" onClick={prevSlide}>
          ❮
        </button>

        <div className="slider-two-content">
          {slides.slice(currentIndex, currentIndex + 3).map((slide) => (
            <div key={slide.id} className="slider-two-item">
              {slide.text}
            </div>
          ))}
        </div>

        <button className="slider-two-button next" onClick={nextSlide}>
          ❯
        </button>
      </div>
    </div>
  );
};

export default SliderTwo;
