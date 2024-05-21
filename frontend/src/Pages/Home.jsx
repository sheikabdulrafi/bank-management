import Lottie from "lottie-react";
import globe from "../assets/globeanim.json";
import security from "../assets/secured.json";

const Home = () => {
  return (
    <>
      <section className="homeSection">
        <div className="content">
          <h1>
            Explore the world of finance with our global banking solutions.
          </h1>
          <button>Sign in</button>
        </div>
        <main>
          <Lottie animationData={globe} loop={true} reversed={true} />
        </main>
      </section>
      <section className="homeSection ">
        <main>
          <Lottie className="secured" animationData={security} loop={true} reversed={true} />
        </main>
        <div className="content">
          <h1>
            Guarding your credentials with utmost care, our security essentials
            are always there.
          </h1>
          <button>Create Account</button>
        </div>
      </section>
    </>
  );
};

export default Home;
