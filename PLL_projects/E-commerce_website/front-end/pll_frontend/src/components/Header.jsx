import React from 'react';
import { Link } from 'react-router-dom';
import './Header.css';

const Header = () => {
  return (
    <header className="header">
      <div className="logo">
        <Link to="/">Asia Store</Link>
      </div>
      <nav className="nav">
        <Link to="/iphone">iPhone</Link>
        <Link to="/ipad">iPad</Link>
        <Link to="/mac">Mac</Link>
        <Link to="/watch">Watch</Link>
        <Link to="/airpods">AirPods</Link>
        <Link to="/gamin">Gamin</Link>
        <Link to="/audio">Audio</Link>
        <Link to="/accessories">Аксессуары</Link>
        <Link to="/gadgets">Гаджеты</Link>
      </nav>
      <div className="auth">
        <Link to="/login">Войти</Link>
        <Link to="/registration">Регистрация</Link>
      </div>
    </header>
  );
};

export default Header;