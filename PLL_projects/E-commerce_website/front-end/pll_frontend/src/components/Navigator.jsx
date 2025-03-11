import React from 'react';
import { Link } from 'react-router-dom';
import './Navigator.css';

const Navigator = () => {
  return (
    <div className="navigator">
      <Link to="/iphone">iPhone</Link>
      <Link to="/ipad">iPad</Link>
      <Link to="/mac">Mac</Link>
      <Link to="/watch">Watch</Link>
      <Link to="/airpods">AirPods</Link>
      <Link to="/gamin">Gamin</Link>
      <Link to="/audio">Audio</Link>
      <Link to="/accessories">Аксессуары</Link>
      <Link to="/gadgets">Гаджеты</Link>
    </div>
  );
};

export default Navigator;