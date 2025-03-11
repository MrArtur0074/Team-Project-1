import React from 'react';
import './Footer.css';

const Footer = () => {
  return (
    <footer className="footer">
      <p>© 2023 Все права защищены.</p>
      <nav>
        <a href="/">Политика конфиденциальности</a>
        <a href="/">Условия использования</a>
      </nav>
    </footer>
  );
};

export default Footer;