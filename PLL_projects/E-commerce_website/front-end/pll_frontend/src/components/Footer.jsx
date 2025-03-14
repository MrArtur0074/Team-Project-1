import React from "react";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        {/* Верхняя часть */}
        <div className="footer-top">
          <h2>
            Asia Store – официальный авторизованный партнёр Apple в Кыргызстане
            со статусом Apple Authorised Reseller.
          </h2>
          <div className="footer-buttons">
            <button>О нас</button>
            <button>Гарантия и сервис</button>
          </div>
        </div>

        {/* Средняя часть */}
        <div className="footer-middle">
          <p>
            В нашем магазине можно не только купить iPhone, компьютер Mac или
            планшет iPad, а также другую технику Apple и аксессуары для неё.
            Asia Store – это место, в котором вам помогут с выбором техники,
            научат ей пользоваться, дадут советы по эксплуатации ваших гаджетов
            и предложат качественные аксессуары.
          </p>
          <p>
            Наш магазин является авторизованным реселлером Apple и Garmin в
            Кыргызстане, а также официальным дистрибьютором JBL & Harman Kardon
            и умных устройств от Yandex.
          </p>
        </div>

        {/* Нижняя часть */}
        <div className="footer-bottom">
          {/* Контакты */}
          <div className="footer-section">
            <h4>Связь с нами</h4>
            <p>Beeline Mega</p>
            <a href="tel:+996502111100">+996 502 111100</a>
            <div className="footer-icons">
              <a href="https://wa.me/996502111100">
                <p>s</p>
              </a>
              <a href="https://instagram.com">
                <p>s</p>
              </a>
            </div>
          </div>

          {/* Товары */}
          <div className="footer-section">
            <h4>Товары</h4>
            <ul>
              <li>iPhone</li>
              <li>Mac</li>
              <li>iPad</li>
              <li>Watch</li>
              <li>AirPods</li>
              <li>Аксессуары</li>
              <li>Аудио</li>
            </ul>
          </div>

          {/* Бренды */}
          <div className="footer-section">
            <h4>Бренды</h4>
            <ul>
              <li>Garmin</li>
              <li>Яндекс</li>
              <li>JBL</li>
              <li>Marshall</li>
            </ul>
          </div>

          {/* Информация */}
          <div className="footer-section">
            <h4>Информация</h4>
            <ul>
              <li>О магазине</li>
              <li>Товары в кредит</li>
              <li>Trade-in</li>
              <li>Гарантия</li>
              <li>Доставка и оплата</li>
            </ul>
          </div>

          {/* Предложения */}
          <div className="footer-section">
            <h4>Предложения</h4>
            <ul>
              <li>Для корпоративных клиентов</li>
              <li>Для образования</li>
              <li>Сертификаты</li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
