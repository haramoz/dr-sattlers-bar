import React from 'react';
import { Link } from 'react-router-dom';
import GoogleAuth from './GoogleAuth';

const Header = () => {
  return (
    <div className="ui secondary pointing menu">
      <div className="right menu">
        <Link to="/" className="item">
          Home
        </Link>

        <Link to="/metrics" className="item">
          Metrics
        </Link>

        <Link to="/notifications" className="item">
          Notifications
        </Link>
        <GoogleAuth />
      </div>
    </div>
  );
};

export default Header;