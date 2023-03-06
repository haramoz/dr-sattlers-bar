import React from 'react';
import { BrowserRouter as Router,  Route, Routes } from "react-router-dom";
import NavBar from "./NavBar";
import HomePage from '../pages/HomePage';
import Metrics from '../pages/Metrics';
import Notifications from '../pages/Notifications';
import backgroundImage from '../img/bar.jpg';

const App = () => {
  return (
    <>
      <Router>
        <NavBar />

        <div className="pages">
          <Routes>
            <Route path="/" element={<HomePage/>} />
            <Route path="/metrics" element={<Metrics/>} />
            <Route path="/notifications" element={<Notifications/>} />
          </Routes>
        </div>
      </Router>
    </>
  );
};

export default App;