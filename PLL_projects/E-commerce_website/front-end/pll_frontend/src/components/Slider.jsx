import { useState } from "react";
import "./slider.css";

const slides = [
  "Самый продвинутый ноутбук Mac уже в продаже.",
  "Мощь и элегантность в одном устройстве.",
  "Лучший выбор для профессионалов на macOS.",
  "Быстродействие и надежность для любых задач.",
  "Технологии будущего уже сегодня.",
];

const Slider = () => {
  const [current, setCurrent] = useState(0);

  const nextSlide = () => {
    setCurrent((prev) => (prev + 1) % slides.length);
  };

  const prevSlide = () => {
    setCurrent((prev) => (prev - 1 + slides.length) % slides.length);
  };

  return (
    <div className="slider">
      <button className="prev" onClick={prevSlide}>&#10094;</button>
      <div className="slide-content">
        <p>{slides[current]}</p>
      </div>
      <button className="next" onClick={nextSlide}>&#10095;</button>
    </div>
  );
};

export default Slider;
