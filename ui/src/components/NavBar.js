import React, { useState } from "react";
import { NavLink } from "react-router-dom";
import "./NavBar.css";
import { ReactComponent as LogoImage } from '../img/logos/logo-white-on-transparent.svg';

function NavBar() {
  const [click, setClick] = useState(false);

  const handleClick = () => setClick(!click);
  return (
    <>
      <nav className="navbar">
        <div className="nav-container">
          <NavLink exact to="/" className="nav-logo">
            <LogoImage/>
          </NavLink>

          <ul className={click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item">
              <NavLink
                exact
                to="/"
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                exact
                to="/metrics"
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Metrics
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                exact
                to="/notifications"
                activeClassName="active"
                className="nav-links"
                onClick={handleClick}
              >
                Notifications
              </NavLink>
            </li>
            
          </ul>
          <div className="nav-icon" onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"}></i>
          </div>
        </div>
      </nav>
    </>
  );
}

export default NavBar;
