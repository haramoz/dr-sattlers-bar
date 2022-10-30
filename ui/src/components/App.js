import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import Header from './Header';
import HomePage from '../pages/HomePage';
import Metrics from '../pages/Metrics';
import Notifications from '../pages/Notifications';


const App = () => {
  return (
    <div className="ui container">
      <BrowserRouter>
        <div>
          <Header />
          <Route path="/" exact component={HomePage} />
          <Route path="/metrics" exact component={Metrics} />
          <Route path="/notifications" exact component={Notifications} />
        </div>
      </BrowserRouter>
    </div>
  );
};

export default App;