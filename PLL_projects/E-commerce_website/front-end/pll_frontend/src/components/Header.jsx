import React, { useContext } from 'react';
import { Link } from 'react-router-dom';
import './Header.css';
import { UserContext } from '../App';

const Header = () => {
const [user, setUser] = useContext(UserContext)

  return (
    <header className="header">
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
        <Link to="/basket">Корзина</Link>
        <Link to="/history">История покупок</Link>
        <div>
					{!user ? (
						<div>
							<Link to="/login">Войти</Link>
							<Link to="/registration">Регистрация</Link>
						</div>
					) : (
						<div>
							<Link to="/profile">Профиль</Link>
							<Link to="/logout">Выйти</Link>
						</div>
					)}
				</div>
      </nav>
    </header>
  );
};

export default Header;