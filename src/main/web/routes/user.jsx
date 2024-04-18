import React, { useState, useEffect } from 'react';
import axios from './axios';

import Nav from './components/nav.jsx';
import Footer from './components/footer.jsx';
import Error from './components/error.jsx';

import FormAccountName from './components/form-account-name.jsx';
import FormAccountPassword from './components/form-account-password.jsx';

function Items() {
  const [user, setUser] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await axios.post(`http://localhost:8080/users/${localStorage['id']}`, axios.defaults.headers.common['Authorization']);
        const data = await response.data;
        setUser(data);
      } catch (error) {
        const status = error.response.status;
        if (status === 404) {
          setError({ status: status, message: 'Účet nebyl nalezen' });
        } else if (status === 403) {
          setError({ status: status, message: 'Nemáte dostatečná oprávnění' });
        } else {
          setError({ status: 500, message: 'Nastala chyba' });
        }
      }
    };

    fetchUser();
  }, []);

  return (
    <div id="container">
      <Nav />
      <div id="detail-account">
        {error ? <Error code={error.status} message={error.message} /> : (
          <>
            <h1>Účet</h1>
            <div id="split">
              <div id="sections">
                <a href="/account"><h3>Základní údaje</h3></a>
                <a href="/account/password"><h3>Zabezpečení</h3></a>
              </div>
              <div id="form">
                {location.href.includes("password") ? <FormAccountPassword /> : <FormAccountName user={user} />}
              </div>
            </div>
          </>
        )}
      </div>
      <Footer />
    </div>
  );
}

export default Items