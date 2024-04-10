import React, { useState, useEffect } from 'react';
import '../style/app.css'

function App() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
              try {
                const response = await fetch('http://localhost:8080/users');
                if (!response.ok) {
                  throw new Error('Failed to fetch users');
                }
                const data = await response.json();
                setUsers(data);
              } catch (error) {
                console.error('Error fetching users:', error.message);
              }
            };
        fetchUsers();
    }, []);

    return (
      <div>
        <h1>Users</h1>
          {users.map((user) => (
            <p key={user.id}>{user.name} {user.surname} ({user.username})</p>
          ))}
      </div>
    );
}

export default App
