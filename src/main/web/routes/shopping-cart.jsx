import React, { useState, useEffect } from 'react';
import axios from './axios';

import Nav from './components/nav.jsx';
import Footer from './components/footer.jsx';
import ShoppingCartItem from './components/shopping-cart-item.jsx';

function ShoppingCart() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    const fetchItems = async () => {
      const response = await axios.get(`http://localhost:8080/carts/${localStorage['id']}`);
      setItems(response.data);
    };

    fetchItems();
  }, []);

  return (
    <div id="container">
      <Nav />
      <div id="shopping-cart">
        <h1 id="name">Nákupní košík</h1>
        <div id="list">
            {items.map((item) => (
                <ShoppingCartItem key={item.id} id={item.id} item={item.item} count={item.count} />
            ))}
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ShoppingCart