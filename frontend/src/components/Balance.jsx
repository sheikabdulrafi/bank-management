import userData from "../assets/data.json";

const Balance = () => {
  return (
    <section>
      <div className="balanceViewer">
        <h1>Balance</h1>
        <div>
          <span>Name</span>
          <span>{userData.userName}</span>
        </div>
        <div>
          <span>Account Number</span>
          <span>{userData.accountNumber}</span>
        </div>
        <div>
          <span>Current Balance</span>
          <span>{userData.balanceAmount}</span>
        </div>
      </div>
    </section>
  );
};

export default Balance;
