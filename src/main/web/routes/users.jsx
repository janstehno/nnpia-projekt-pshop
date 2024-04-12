import React, { useState, useEffect } from 'react';

function Users() {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchUsers = async () => {
              try {
                  const response = await fetch('http://localhost:8080/users');
                  if (!response.ok) {
                      setError(new Error(response.status));
                  } else {
                      const data = await response.json();
                      setUsers(data);
                  }
                } catch (error) {
                    console.error(error);
                    setError(error);
                }
            };
        fetchUsers();
    }, []);

    return (
      <div id="users">
        <h1>Users</h1>
          {error ? <h4>{error.message}</h4> : (
                      users.map((user) => (
                          <p key={user.id}>{user.name} {user.surname} ({user.username})</p>
                      ))
                  )}
      </div>
    );
}

export default Users