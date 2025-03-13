import { useEffect, useState, createContext } from 'react'
import './App.css'
import Routers from './app/Routers'
import axios from 'axios'

export const UserContext = createContext()
export const UserInfoContext = createContext()

function App() {
  const [user, setUser] = useState(false)
	const [userInfo, setUserInfo] = useState({

	})

	useEffect(() => {
		if (localStorage.getItem('access_token')) {
			checkUser(localStorage.getItem('access_token'))
		}
	}, [])

	useEffect(() => {
		if (localStorage.getItem('access_token')) {
			checkUser(localStorage.getItem('access_token'))
		}
	}, [user])

	async function checkUser(access) {
		const access_token = localStorage.getItem('access_token')
		axios.defaults.headers.common = {'Authorization': `Bearer ${access_token}`}
		const responce = axios.get('')
		.then((res) => {
			setUser(true)
			setUserInfo(res.data)
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
		<UserContext.Provider value={[user, setUser]} >
			<UserInfoContext.Provider value={[userInfo, setUserInfo]} >
				<Routers/>
			</UserInfoContext.Provider>
		</UserContext.Provider>		
  )
}

export default App
