import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Home from './home.jsx';
import SignIn from './sign-in.jsx';
import SignUp from './sign-up.jsx';
import Users from './users.jsx';

import '../style/app.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/" Component={Home} />
        <Route path="/sign-up" Component={SignUp} />
        <Route path="/sign-in" Component={SignIn} />
        <Route path="/users" Component={Users} />
      </Routes>
    </Router>
  </React.StrictMode>,
)
