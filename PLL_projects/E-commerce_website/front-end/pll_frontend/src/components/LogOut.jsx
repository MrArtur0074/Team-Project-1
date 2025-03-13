import React from 'react';
import './LogOut.css';
import { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../App'

const LogOut = () => {
	const [user, setUser] = useContext(UserContext)
	const navigate = useNavigate()

	function logout() {
		setUser(false)

		localStorage.removeItem('access_token')
		localStorage.removeItem('refresh_token')

		navigate('/')
	}

	function backFromPage() {
		navigate('/')
	}

  return (
    <div className="logout">
      <form>
			  <h2>Logout</h2>
				<label htmlFor=''>Вы уверены, что хотите выйти?</label>
        <button onClick={logout}>Выйти</button>
				<button onClick={backFromPage}>Остаться</button>
			</form>
    </div>
  );
};

export default LogOut;