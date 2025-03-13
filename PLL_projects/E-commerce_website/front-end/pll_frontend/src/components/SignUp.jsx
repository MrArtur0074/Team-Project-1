import React from 'react';
import './SignUp.css';
import { useNavigate, UseNavigate } from 'react-router-dom'
import { useState } from 'react'
import axios from 'axios'

const SignUp = () => {

  const [username, setUsername] = useState('')
	const [email, setEmail] = useState('')
	const [password, setPassword] = useState('')

	const navigate = useNavigate()

	function setInputUsername(event) {
		setUsername(event.target.value)
	}

	function setInputEmail(event) {
		setEmail(event.target.value)
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
			email: email,
			password: password,
		})
		.then((res) => {
			localStorage.setItem('user_id', res.data.id)

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
    <div className="signup">
      <h2>Регистрация</h2>
      <form onSubmit={sendData} method='post'>
				<label htmlFor='username'>Username: </label>
        <input onChange={setInputUsername} type="text" name='username' placeholder="Enter your name" />
				<label htmlFor='email'>Email: </label>
        <input onChange={setInputEmail} type="email" name='email' placeholder="Enter your email" />
				<label htmlFor='password'>Password: </label>
        <input onChange={setInputPassword} type="password" name='password' placeholder="Enter your password" />
        <input type="submit" value="submit"/>
      </form>
			<button onClick={backFromPage}>Back</button>
    </div>
  );
};

export default SignUp;