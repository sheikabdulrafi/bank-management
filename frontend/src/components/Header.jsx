import { Link } from "react-router-dom";

const Header = () => {
  return (
    <nav id="top">
      <h1>HSF Bank</h1>
      <main>
        <span className="item">
          <Link to={"/"}>Home</Link>
        </span>
        <span className="item">
          <Link to={"/transactions"}>Transactions</Link>
        </span>
        <span className="item">
          <Link to={"/myaccount"}>My Account</Link>
        </span>
        <span className="item">
          <Link to={"/profile"}>Profile</Link>
        </span>
        <span className="item">
          <Link to={"/settings"}>Settings</Link>
        </span>
      </main>
    </nav>
  );
};

export default Header;
