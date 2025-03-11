import React from 'react';
import './SignIn.css';

const SignIn = () => {
  return (
    <div className="signin">
      <h2>Вход</h2>
      <form>
        <input type="email" placeholder="Email" />
        <input type="password" placeholder="Пароль" />
        <button type="submit">Войти</button>
      </form>
    </div>
  );
};

export default SignIn;