import axios from 'axios';
import React, { useState } from 'react';

import Nav from './components/nav.jsx';

function SignUp() {
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
    email: '',
    username: '',
    password: ''
  });

  const [errorMessages, setErrorMessages] = useState({
    firstname: '',
    lastname: '',
    email: '',
    username: '',
    password: ''
  });

  const submitForm = async (formData) => {
    try {
      const response = await axios.post('http://localhost:8080/auth/register', formData);
      localStorage['firstname'] = response.data['firstname'];
      localStorage['lastname'] = response.data['lastname'];
      axios.defaults.headers.common['Authorization'] = `Bearer ${response.data['token']}`;
      window.location.href = '/';
    } catch (error) {
      if (error.response.data['message'] === "EMAIL_EXISTS") {
        setErrorMessages({ ...errorMessages, email: 'Tento email je používaný' });
      }
      if (error.response.data['message'] === "USERNAME_EXISTS") {
        setErrorMessages({ ...errorMessages, username: 'Toto uživatelské jméno je používané' });
      }
    }
  };

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({ ...formData, [id]: value });
    validate(e);
  };

  const handleSubmit = (e) => {
    e.preventDefault()
    if (!Object.values(errorMessages).some(message => message !== '') && Object.values(formData).some(message => message !== '')) {
      submitForm(formData);
    }
  };

  const validate = (e) => {
    const { id, value } = e.target;
    if (id === 'firstname') {
      if (/^[A-Z][a-z]{1,29}$/.test(value)) {
        setErrorMessages({ ...errorMessages, firstname: '' });
      } else {
        setErrorMessages({ ...errorMessages, firstname: 'Jméno musí začínat velkým písmenem a mít délku maximálně 30 znaků' });
      }
    }
    if (id === 'lastname') {
      if (/^[A-Z][a-z]{1,29}$/.test(value)) {
        setErrorMessages({ ...errorMessages, lastname: '' });
      } else {
        setErrorMessages({ ...errorMessages, lastname: 'Příjmení musí začínat velkým písmenem a mít délku maximálně 30 znaků' });
      }
    }
    if (id === 'email') {
      if (/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(value)) {
        setErrorMessages({ ...errorMessages, email: '' });
      } else {
        setErrorMessages({ ...errorMessages, email: 'Email musí být například ve formátu: jmeno@email.cz' });
      }
    }
    if (id === 'username') {
      if (/^[a-zA-Z0-9_]{1,20}$/.test(value)) {
        setErrorMessages({ ...errorMessages, username: '' });
      } else {
        setErrorMessages({ ...errorMessages, username: 'Uživatelské jméno může obsahovat pouze alfanumerické znaky, podtržítko a mít délku maximálně 20 znaků' });
      }
    }
    if (id === 'password') {
      if (value.length >= 6) {
        setErrorMessages({ ...errorMessages, password: '' });
      } else {
        setErrorMessages({ ...errorMessages, password: 'Heslo musí mít délku minimálně 6 znaků' });
      }
    }
  }

  const isNotEmpty = (value) => {
    return value.length > 0;
  };

  return (
    <div id="container">
      <Nav />
      <div id="sign">
        <h1>Registrace</h1>
        <form id="sign-form" onSubmit={handleSubmit}>
          <label htmlFor="firstname">Jméno</label>
          <input className={!isNotEmpty(errorMessages.firstname) || !isNotEmpty(errorMessages.firstname) ? 'valid' : 'invalid'} id="firstname" name="firstname" type="text" onChange={handleChange} />
          {errorMessages.firstname && <p className="error">{errorMessages.firstname}</p>}

          <label htmlFor="lastname">Příjmení</label>
          <input className={!isNotEmpty(errorMessages.lastname) || !isNotEmpty(errorMessages.lastname) ? 'valid' : 'invalid'} id="lastname" name="lastname" type="text" onChange={handleChange} />
          {errorMessages.lastname && <p className="error">{errorMessages.lastname}</p>}

          <label htmlFor="email">Email</label>
          <input className={!isNotEmpty(errorMessages.email) || !isNotEmpty(errorMessages.email) ? 'valid' : 'invalid'} id="email" name="email" type="text" onChange={handleChange} />
          {errorMessages.email && <p className="error">{errorMessages.email}</p>}

          <label htmlFor="username">Uživatelské jméno</label>
          <input className={!isNotEmpty(errorMessages.username) || !isNotEmpty(formData.username) ? 'valid' : 'invalid'} id="username" name="username" type="text" onChange={handleChange} />
          {errorMessages.username && <p className="error">{errorMessages.username}</p>}

          <label htmlFor="password">Heslo</label>
          <input className={!isNotEmpty(errorMessages.password) || !isNotEmpty(errorMessages.password) ? 'valid' : 'invalid'} id="password" name="password" type="password" onChange={handleChange} />
          {errorMessages.password && <p className="error">{errorMessages.password}</p>}

          <input id="submit" value="Zaregistrovat" type="submit" />
        </form>
      </div>
    </div>
  );
}

export default SignUp