import React from 'react'
import ReactDOM from 'react-dom/client'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

import Home from './home.jsx';
import SignIn from './sign-in.jsx';
import SignUp from './sign-up.jsx';
import Items from './items.jsx';
import Detail from './detail.jsx';
import User from './user.jsx';
import Error from './components/error.jsx';

import '../style/app.css'
import '../style/responsive.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/sign-up" element={<SignUp />} />
        <Route path="/sign-in" element={<SignIn />} />
        <Route path="/cameras" element={<Items name="cameras" />} />
        <Route path="/cameras/:id" element={<Detail name="cameras" />} />
        <Route path="/lenses" element={<Items name="lenses" />} />
        <Route path="/lenses/:id" element={<Detail name="lenses" />} />
        <Route path="/account/*" element={<User />} />
        <Route path="*" element={<Error code="404" message="Stránka nenalezena" />} />
      </Routes>
    </Router>
  </React.StrictMode>,
)
