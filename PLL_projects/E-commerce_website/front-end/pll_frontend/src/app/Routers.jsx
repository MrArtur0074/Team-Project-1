import { Route, Routes, BrowserRouter } from "react-router-dom";

import MainPage from '../pages/MainPage'
import SignUpPage from '../pages/SignUpPage'
import SignInPage from '../pages/SignInPage'
import LogOutPage from '../pages/LogOutPage'
import IPhonePage from '../pages/IPhonePage'
import IPadPage from '../pages/IPadPage'
import MacPage from '../pages/MacPage'
import WatchPage from '../pages/WatchPage'
import AirPodsPage from '../pages/AirPodsPage'
import GaminPage from '../pages/GaminPage'
import AudioPage from '../pages/AudioPage'
import AccessoriesPage from '../pages/AccessoriesPage'
import GadgetsPage from '../pages/GadgetsPage'
import HistoryOfProductsPage from '../pages/HistoryOfProductsPage'
import ProfilePage from '../pages/ProfilePage'
import BasketPage from '../pages/BasketPage'
import ErrorPage from '../pages/ErrorPage'


export default function Routers() {
	return(
		<BrowserRouter>
			<Routes>
				<Route path="/" element={<MainPage/>} />
				<Route path="/registration" element={<SignUpPage/>} />
				<Route path="/login" element={<SignInPage/>} />
				<Route path="/logout" element={<LogOutPage/>} />
				<Route path="/iphone" element={<IPhonePage/>} />
				{/* <Route path="/ipad" element={<IPadPage/>} />
				<Route path="/mac" element={<MacPage/>} />
				<Route path="/watch" element={<WatchPage/>} />
				<Route path="/airpods" element={<AirPodsPage/>} />
				<Route path="/gamin" element={<GaminPage/>} />
				<Route path="/audio" element={<AudioPage/>} />
				<Route path="/accessories" element={<AccessoriesPage/>} />
				<Route path="/gadgets" element={<GadgetsPage/>} /> */}
				<Route path="/history" element={<HistoryOfProductsPage/>} />
				<Route path="/home" element={<ProfilePage/>} />
				<Route path="/basket" element={<BasketPage/>} />
				<Route path="/*" element={<ErrorPage/>} />
			</Routes>
		</BrowserRouter>
	)
}