import React from 'react';
import './ProfilePage.css';

const ProfilePage = () => {
  return (
    <div className="profile-page">
      <h1>Профиль</h1>
      <div className="profile-info">
        <p>Имя: Иван Иванов</p>
        <p>Email: ivan@example.com</p>
      </div>
    </div>
  );
};

export default ProfilePage;