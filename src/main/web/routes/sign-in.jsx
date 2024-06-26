import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from './axios';

import Nav from './components/nav.jsx';

function SignUp() {
  let navigate = useNavigate();

  const [formData, setFormData] = useState({
    username: '',
    password: ''
  });

  const [errorMessages, setErrorMessages] = useState({
    username: '',
    password: ''
  });

  const submitForm = async (formData) => {
    try {
      const response = await axios.post('http://localhost:8080/auth/login', formData);
      localStorage['id'] = response.data['id'];
      localStorage['firstname'] = response.data['firstname'];
      localStorage['lastname'] = response.data['lastname'];
      localStorage.setItem('token', response.data.token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${response.data['token']}`;
      navigate('/');
    } catch (error) {
      if (error.response.data['message'] === "USERNAME_NOT_FOUND") {
        setErrorMessages({ ...errorMessages, username: 'Toto uživatelské jméno neexistuje' });
      }
      if (error.response.data['message'] === "WRONG_PASSWORD") {
        setErrorMessages({ ...errorMessages, password: 'Špatné heslo' });
      }
    }
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
    setErrorMessages({ ...errorMessages, [e.target.id]: '' });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!Object.values(errorMessages).some(e => e !== '') && Object.values(formData).some(e => e !== '')) {
      submitForm(formData);
    }
  };

  const isNotEmpty = (value) => {
    return value.length > 0;
  };

  return (
    <div id="container">
      <Nav />
      <div id="sign">
        <h1>Přihlášení</h1>
        <form onSubmit={handleSubmit}>
          <label htmlFor="username">Uživatelské jméno</label>
          <input className={!isNotEmpty(errorMessages.username) || !isNotEmpty(formData.username) ? 'valid' : 'invalid'} id="username" name="username" type="text" onChange={handleChange} />
          {errorMessages.username && <p className="error">{errorMessages.username}</p>}

          <label htmlFor="password">Heslo</label>
          <input className={!isNotEmpty(errorMessages.password) || !isNotEmpty(errorMessages.password) ? 'valid' : 'invalid'} id="password" name="password" type="password" onChange={handleChange} />
          {errorMessages.password && <p className="error">{errorMessages.password}</p>}

          <input id="submit" value="Přihlásit" type="submit" />
        </form>
      </div>
    </div>
  );
}

export default SignUp