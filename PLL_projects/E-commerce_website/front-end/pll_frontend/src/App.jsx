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
		.then((res) => {
			setUser(true)
			setUserInfo(res.data)
		})
		.catch((error) => {
			console.log('qwqweqwe')
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
