import React, { useState, useEffect } from 'react';
import axios from '../axios';

function FormAccountName(props) {
  let { user } = props;
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
    email: '',
    date: ''
  });
  const [errorMessages, setErrorMessages] = useState({
    firstname: '',
    lastname: '',
    email: '',
  });
  const [successMessage, setSuccessMessage] = useState([]);

  useEffect(() => {
    setFormData({
      firstname: user.firstname || '',
      lastname: user.lastname || '',
      email: user.email || '',
      date: ''
    });
  }, [user]);

  const validate = (e) => {
    const { id, value } = e.target;
    if (id === 'firstname') {
      if (/^[A-Z][a-z]{1,29}$/.test(value)) {
        setErrorMessages({ ...errorMessages, firstname: '' });
      } else {
        setErrorMessages({ ...errorMessages, firstname: 'Jméno musí začínat velkým písmenem a mít délku maximálně 30 znaků' });
      }
      user.firstname = value;
    }
    if (id === 'lastname') {
      if (/^[A-Z][a-z]{1,29}$/.test(value)) {
        setErrorMessages({ ...errorMessages, lastname: '' });
      } else {
        setErrorMessages({ ...errorMessages, lastname: 'Příjmení musí začínat velkým písmenem a mít délku maximálně 30 znaků' });
      }
      user.lastname = value;
    }
    if (id === 'email') {
      if (/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(value)) {
        setErrorMessages({ ...errorMessages, email: '' });
      } else {
        setErrorMessages({ ...errorMessages, email: 'Email musí být například ve formátu: jmeno@email.cz' });
      }
      user.email = value;
    }
  }

  const isNotEmpty = (value) => {
    return value.length > 0;
  };

  const handleChange = (e) => {
    const { id, value } = e.target;
    setSuccessMessage('');
    setFormData({ ...formData, [id]: value });
    validate(e);
  };

  const submitForm = async (formData) => {
    try {
      const response = await axios.put(`http://localhost:8080/users/update/${localStorage['id']}`, { ...formData, date: new Date() }, axios.defaults.headers.common['Authorization']);
      localStorage['firstname'] = user.firstname;
      localStorage['lastname'] = user.lastname;
      if (response.status === 200) {
        setSuccessMessage('Údaje byly úspěšně změněny');
      }
    } catch (error) {
      if (error.response.data['message'] === "EMAIL_EXISTS") {
        setErrorMessages({ ...errorMessages, email: 'Tento email je používaný' });
      }
      setSuccessMessage('');
    }
  };

  const handleUpdate = (e) => {
    e.preventDefault()
    if (!Object.values(errorMessages).some(e => e !== '') && Object.values(formData).some(e => e !== '')) {
      submitForm(formData);
    }
  };

  return (
    <>
      <form onSubmit={handleUpdate}>
        <label htmlFor="firstname">Jméno</label>
        <input className={!isNotEmpty(errorMessages.firstname) || !isNotEmpty(errorMessages.firstname) ? 'valid' : 'invalid'} id="firstname" name="firstname" type="text" defaultValue={formData.firstname} onChange={handleChange} />
        {errorMessages.firstname && <p className="error">{errorMessages.firstname}</p>}

        <label htmlFor="lastname">Příjmení</label>
        <input className={!isNotEmpty(errorMessages.lastname) || !isNotEmpty(errorMessages.lastname) ? 'valid' : 'invalid'} id="lastname" name="lastname" type="text" defaultValue={formData.lastname} onChange={handleChange} />
        {errorMessages.lastname && <p className="error">{errorMessages.lastname}</p>}

        <label htmlFor="email">Email</label>
        <input className={!isNotEmpty(errorMessages.email) || !isNotEmpty(errorMessages.email) ? 'valid' : 'invalid'} id="email" name="email" type="text" defaultValue={formData.email} onChange={handleChange} />
        {errorMessages.email && <p className="error">{errorMessages.email}</p>}

        <label htmlFor="username">Uživatelské jméno</label>
        <span id="username" name="username">{user.username}</span>

        <input id="submit" value="Aktualizovat údaje" type="submit" />
      </form>
      {successMessage && <p id="success">{successMessage}</p>}
    </>
  );
}

export default FormAccountName