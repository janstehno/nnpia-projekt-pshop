import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../axios';

function Nav() {
    let navigate = useNavigate();
    const [cartItems, setCartItems] = useState([]);
    const [loggedIn, setLoggedIn] = useState(false);
    const [username, setUsername] = useState({ id: '', firstname: '', lastname: '' });

    const checkLoggedIn = async () => {
        try {
            const id = localStorage['id'];
            const firstname = localStorage['firstname'];
            const lastname = localStorage['lastname'];
            if (id != null && firstname != null && lastname != null) {
                setLoggedIn(true);
                setUsername({ id: id, firstname: firstname, lastname: lastname });
            } else {
                setLoggedIn(false);
                setUsername({ id: '', firstname: '', lastname: '' });
            }
        } catch (error) {
            setLoggedIn(false);
            setUsername({ id: '', firstname: '', lastname: '' });
        }
    };

    useEffect(() => {
        checkLoggedIn();

        const fetchCart = async () => {
            try{
                const response = await axios.get(`http://localhost:8080/carts/${localStorage['id']}`, axios.defaults.headers.common['Authorization']);
                const data = await response.data;
                setCartItems(data);
            } catch (error) {
                console.error('Error fetching shopping cart:', error);
            }
        };

        if(axios.defaults.headers.common['Authorization']) {
            fetchCart();
        }
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

    const handleCart = async () => {
        location.href = "/shopping-cart";
    };

    const handleSignUp = async () => {
        location.href = "/sign-up";
    };

    const handleSignIn = async () => {
        location.href = "/sign-in";
    };

    return (
        <div id="bar">
            {loggedIn ? (
                <div id="logged-in">
                    <div id="cart" onClick={handleCart}>
                        <img src="/bag.svg" alt="Košík" />
                        <p>{cartItems.length}</p>
                    </div>
                    <div id="auth">
                        <div id="account">
                            <p id="username">{username.firstname} {username.lastname}</p>
                            <button id="account" onClick={handleAccount}>Účet</button>
                        </div>
                        <button id="logout" onClick={handleLogout}>Odhlásit</button>
                    </div>
                </div>
            ) : (
                <div id="logged-out">
                    <button id="sign-up" onClick={handleSignUp}>Registrace</button>
                    <button id="sign-in" onClick={handleSignIn}>Přihlášení</button>
                </div>
            )}
        </div>
    );
}

export default Nav