import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Home from "./Pages/Home.jsx";
import Transactions from "./Pages/Transactions.jsx";
import Myaccount from "./Pages/Myaccount.jsx";
import Profile from "./Pages/Profile.jsx";
import Settings from "./Pages/Settings.jsx";
import Footer from "./components/Footer.jsx";

const App = () => {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/transactions" element={<Transactions />} />
        <Route path="/myaccount" element={<Myaccount />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/settings" element={<Settings />} />
      </Routes>
      <Footer />
    </Router>
  );
};

export default App;
