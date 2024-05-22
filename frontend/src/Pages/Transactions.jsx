import "../Styles/Style.scss";
import { Link } from "react-router-dom";
import { FaRupeeSign, FaRegListAlt } from "react-icons/fa";
import { MdSavings, MdOutlineDeveloperMode } from "react-icons/md";
import { PiHandWithdrawFill } from "react-icons/pi";
import { FaMoneyBillTransfer } from "react-icons/fa6";
import { useEffect, useState } from "react";

const openHandler = (open) => {
  const optionContainer = document.querySelector(".optionContainer");
  if (optionContainer) {
    optionContainer.style.width = open ? "220px" : "45px";
  }
};

const Transactions = () => {
  const [mouse, setMouse] = useState(false);

  useEffect(() => {
    openHandler(mouse);
  }, [mouse]);

  return (
    <section className="tranSection">
      <div
        className="optionContainer"
        onMouseOver={() => setMouse(true)}
        onMouseLeave={() => setMouse(false)}
      >
        <div className="optionData">
          <FaRupeeSign className="icon"  />
          <Link>Check Balance</Link>
        </div>
        <div className="optionData">
          <FaRegListAlt className="icon"  />
          <Link>Account Statement</Link>
        </div>
        <div className="optionData">
          <MdSavings className="icon"  />
          <Link>Deposit Amount</Link>
        </div>
        <div className="optionData">
          <PiHandWithdrawFill className="icon"  />
          <Link>Withdraw Amount</Link>
        </div>
        <div className="optionData">
          <FaMoneyBillTransfer className="icon"  />
          <Link>Transfer Amount</Link>
        </div>
        <div className="optionData">
          <MdOutlineDeveloperMode className="icon"  />
          <Link>Dev Contact</Link>
        </div>
      </div>
      <div className="formContainer"></div>
    </section>
  );
};

export default Transactions;
