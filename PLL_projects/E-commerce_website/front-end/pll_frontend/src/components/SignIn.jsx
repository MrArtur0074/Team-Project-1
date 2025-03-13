import React from 'react';
import './SignIn.css';
import { useState, useContext} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { UserContext } from '../App';


const SignIn = () => {
  const [username, setUsername] = useState('')
	const [password, setPassword] = useState('')
	const navigate = useNavigate()
	const [user, setUser] = useContext(UserContext)

	function setInputUsername(event) {
		setUsername(event.target.value)
	}

	function setInputPassword(event) {
		setPassword(event.target.value)
	}

	function backFromPage() {
		navigate('/')
	}

	async function sendData(event) {
		event.preventDefault()

		const responce = axios.post('', {
			username: username,
			password: password,
		})
		.then((res) => {
			localStorage.setItem('access_token', res.data.access)
			localStorage.setItem('refresh_token', res.data.refresh)
			localStorage.setItem('user_id', res.data.user_id)

			setUser(true)

			navigate('/')
		})
		.catch((error) => {
			if (error.response) {
				console.error('Authentication failed. Server responded with:', error.response.data)
			} else if (error.request) {
				console.error('No response received from the server. Is the server running?')
			} else {
				console.error('Error setting up the request:', error.message)
			}
		})
	}

  return (
    <div className="signin">
      <h2>Вход</h2>
      <form onSubmit={sendData} method='post'>
			  <label htmlFor='username'>Username: </label>
        <input onChange={setInputUsername} name='username' onChange={setInputUsername} type="username" placeholder="enter your username" />
				<label htmlFor='password'>Password: </label>
        <input onChange={setInputPassword} name='password' onChange={setInputPassword} type="password" placeholder="enter your password" />
        <input type="submit" value="submit"/>
      </form>
			<button onClick={backFromPage}>Back</button>
    </div>
  );
};

export default SignIn;