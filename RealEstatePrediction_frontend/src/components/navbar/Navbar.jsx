import React, { useEffect, useState } from "react";
import "./navbar.scss";
import { Link, useNavigate } from "react-router-dom";
import { getAuthUser, setAuthHeader,setAuthUser } from "../../helpers/apiService"; 

function Navbar() {
  const navigate = useNavigate();
  const user = getAuthUser(); 

  const handleLogout = () => {
    localStorage.removeItem("auth_token");
    localStorage.removeItem("user");
    setAuthHeader(null);
    setAuthUser(null);
    navigate("/login");
  };

  return (
    <nav>
      {user ? ( 
        <>
          <div className="left">
            <a href="/" className="logo">
              <img src="/logo.png" alt="" />
              <span>LamaEstate</span>
            </a>
            <a href="/HomePage">Home</a>
            <a href="/Offres">Offres</a>
            <a href="/Myspace">My space</a>
            <a href="/Other">Other</a>
          </div>
          <div className="right">
            <div className="user">
              <span>
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  x="0px"
                  y="0px"
                  width="25"
                  height="25"
                  viewBox="0 0 24 24"
                >
                  <path d="..."></path>
                </svg>
              </span>
              <Link to="/profile" className="profile">
                <span>My Profile</span>
              </Link>
              <Link onClick={handleLogout} className="profile">
                <span>Logout</span>
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
            <a href="/login" className="register">Sign in</a>
            <a href="/register" className="register">
              Sign up
            </a>
          </div>
        </>
      )}
    </nav>
  );
}

export default Navbar;
