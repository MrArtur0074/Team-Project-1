import React from 'react';
import './LogOut.css';

const LogOut = () => {
  return (
    <div className="logout">
      <h2>Вы уверены, что хотите выйти?</h2>
      <button>Выйти</button>
    </div>
  );
};

export default LogOut;