import { useState } from "react";
import "./navbar.scss";
import { Link } from "react-router-dom";

function Navbar() {

  const user = true;
  
  return (
    <nav>
        {user ? (
          <>
          <div className="left">
            <a href="/" className="logo">
              <img src="/logo.png" alt="" />
              <span>LamaEstate</span>
            </a>
            <a href="/">Home</a>
            <a href="/Offres">Offres</a>
            <a href="/Myspace">My space</a>
            <a href="/Other">Other</a>
          </div>
          <div className="right">
            <div className="user">
              <span>
                <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="25" height="25" viewBox="0 0 24 24">
                <path d="M 11.984375 0.98632812 A 1.0001 1.0001 0 0 0 11 2 L 11 3.203125 C 7.0846057 3.7180136 4 6.9478647 4 11 L 4 14 C 4 16.555556 2.2929688 18.292969 2.2929688 18.292969 A 1.0001 1.0001 0 0 0 3 20 L 9 20 C 9 21.64497 10.35503 23 12 23 C 13.64497 23 15 21.64497 15 20 L 21 20 A 1.0001 1.0001 0 0 0 21.707031 18.292969 C 21.707031 18.292969 20 16.555556 20 14 L 20 11 C 20 6.9478647 16.915394 3.7180136 13 3.203125 L 13 2 A 1.0001 1.0001 0 0 0 11.984375 0.98632812 z M 11.945312 5.0117188 A 1.0001 1.0001 0 0 0 12.056641 5.0117188 C 15.354136 5.0438544 18 7.6945858 18 11 L 18 14 C 18 15.653192 18.534621 17.010786 19.083984 18 L 4.9160156 18 C 5.4653791 17.010786 6 15.653192 6 14 L 6 11 C 6 7.6939188 8.6468845 5.0427993 11.945312 5.0117188 z M 11 20 L 13 20 C 13 20.56503 12.56503 21 12 21 C 11.43497 21 11 20.56503 11 20 z"></path>
                </svg>
              </span>
              <Link to="/profile" className="profile">
                {/*<div className="notification">3</div>*/}
                <span>My Profile</span>
              </Link>
            </div>
          </div>
          </>
        ) : (
          <>
            <div className="left">
              <a href="/" className="logo">
                <img src="/logo.png" alt="" />
                <span>LamaEstate</span>
              </a>
            </div>
            <div className="right">
              <div className="user">
                <a href="/login">Sign in</a>
                <a href="/register" className="register">
                  Sign up
                </a>
              </div>
            </div>
          </>
        )}
    </nav>
  );
}

export default Navbar;
