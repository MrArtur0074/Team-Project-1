import React from 'react';
import './SignUp.css';

const SignUp = () => {
  return (
    <div className="signup">
      <h2>Регистрация</h2>
      <form>
        <input type="text" placeholder="Имя" />
        <input type="email" placeholder="Email" />
        <input type="password" placeholder="Пароль" />
        <button type="submit">Зарегистрироваться</button>
      </form>
    </div>
  );
};

export default SignUp;