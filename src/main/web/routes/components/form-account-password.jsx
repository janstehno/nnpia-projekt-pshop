import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../axios';

function FormAccountPassword(props) {
  let navigate = useNavigate();
  const [formData, setFormData] = useState({
    password: '',
    data: ''
  });
  const [errorMessage, setErrorMessage] = useState([]);
  const [successMessage, setSuccessMessage] = useState([]);

  const validate = (e) => {
    const { id, value } = e.target;
    if (id === 'password') {
      if (value.length >= 6) {
        setErrorMessage('');
      } else {
        setErrorMessage('Heslo musí mít délku minimálně 6 znaků');
      }
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
      const response = await axios.put(`http://localhost:8080/users/update/password/${localStorage['id']}`, { ...formData, date: new Date() }, axios.defaults.headers.common['Authorization']);
      if (response.status === 200) {
        setSuccessMessage('Heslo bylo úspěšně změněno');
      }
    } catch (error) {
      setSuccessMessage('');
    }
  };

  const handleUpdate = (e) => {
    e.preventDefault()
    if (!Object.values(errorMessage).some(e => e !== '') && Object.values(formData).some(e => e !== '')) {
      submitForm(formData);
    }
  };

  return (
    <>
      <form onSubmit={handleUpdate}>
        <label htmlFor="password">Heslo</label>
        <input className={!isNotEmpty(errorMessage) || !isNotEmpty(errorMessage) ? 'valid' : 'invalid'} id="password" name="password" type="password" onChange={handleChange} />
        {errorMessage && <p className="error">{errorMessage}</p>}

        <input id="submit" value="Aktualizovat heslo" type="submit" />
      </form>
      {successMessage && <p id="success">{successMessage}</p>}
    </>
  );
}

export default FormAccountPassword