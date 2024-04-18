import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../axios';

function Nav() {
    let navigate = useNavigate();

    const [loggedIn, setLoggedIn] = useState(false);
    const [username, setUsername] = useState({ firstname: '', lastname: '' });

    const checkLoggedIn = async () => {
        try {
            const id = localStorage['id'];
            const firstname = localStorage['firstname'];
            const lastname = localStorage['lastname'];
            if (id != null && firstname != null && lastname != null) {
                setLoggedIn(true);
                setUsername({ firstname: firstname, lastname: lastname });
            } else {
                setLoggedIn(false);
                setUsername({ firstname: '', lastname: '' });
            }
        } catch (error) {
            setLoggedIn(false);
            setUsername({ firstname: '', lastname: '' });
        }
    };

    useEffect(() => {
        checkLoggedIn();
    }, []);

    const handleLogout = async () => {
        try {
            delete axios.defaults.headers.common['Authorization'];
            localStorage.clear();
            setLoggedIn(false);
            navigate('/');
        } catch (error) {
            console.error('Error logging out:', error);
        }
    };

    const handleAccount = async () => {
        location.href = "/account";
    };

    const handleSignUp = async () => {
        location.href = "/sign-up";
    };

    const handleSignIn = async () => {
        location.href = "/sign-in";
    };

    return (
        <nav>
            <div id="navigation">
                <a id="title" href="/"><h3>Pshop</h3></a>
                <div id="right">
                    <div id="auth">
                        {loggedIn ? (
                            <>
                                <p id="username">{username.firstname} {username.lastname}</p>
                                <button id="account" onClick={handleAccount}>Účet</button>
                                <button id="logout" onClick={handleLogout}>Odhlásit</button>
                            </>
                        ) : (
                            <>
                                <button id="sign-up" onClick={handleSignUp}>Registrace</button>
                                <button id="sign-in" onClick={handleSignIn}>Přihlášení</button>
                            </>
                        )}
                    </div>
                    <div id="links">
                        <a href="/cameras">Fotoaparáty</a>
                        <a href="/lenses">Objektivy</a>
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default Nav