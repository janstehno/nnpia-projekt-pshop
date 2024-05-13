import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from './axios';

import Nav from './components/nav.jsx';
import Footer from './components/footer.jsx';
import ShoppingCartItem from './components/shopping-cart-item.jsx';

function ShoppingCart() {
  let navigate = useNavigate();

  const [items, setItems] = useState([]);
  const [billingData, setBillingData] = useState({ street: '', city: '', zipCode: '', phone: '' });
  const [shippingMethod, setShippingMethod] = useState('posta');
  const [paymentMethod, setPaymentMethod] = useState('dobirka');
  const [totalPrice, setTotalPrice] = useState(0);
  const [errorMessages, setErrorMessages] = useState({
    street: '',
    city: '',
    zipCode: '',
    phone: ''
  });

  useEffect(() => {
    const fetchItems = async () => {
      const response = await axios.get(`http://localhost:8080/carts/${localStorage['id']}`);
      setItems(response.data);
    };

    fetchItems();
  }, []);

  useEffect(() => {
    let totalPrice = 0;

    for (const item of items) {
      totalPrice += item.item.price * item.count;
    }

    totalPrice += shippingMethod === 'zasilkovna' ? 79 : 99;
    totalPrice += paymentMethod === 'dobirka' ? 40 : 0;

    totalPrice += totalPrice * 0.21;

    setTotalPrice(totalPrice);
  }, [items, shippingMethod, paymentMethod]);

  const validate = (e) => {
    const { id, value } = e.target;
    if (id === 'street' || id === 'city') {
      if (/^[A-Z][a-zA-Z0-9\s]+$/.test(value)) {
        setErrorMessages({ ...errorMessages, [id]: '' });
      } else {
        setErrorMessages({ ...errorMessages, [id]: 'Prosím, zadejte platnou adresu' });
      }
    }
    if (id === 'zipCode') {
      if (/^\d{5}$/.test(value)) {
        setErrorMessages({ ...errorMessages, [id]: '' });
      } else {
        setErrorMessages({ ...errorMessages, [id]: 'PSČ musí být pětimístné číslo' });
      }
    }
    if (id === 'phone') {
      if (/^\d{9}$/.test(value)) {
        setErrorMessages({ ...errorMessages, [id]: '' });
      } else {
        setErrorMessages({ ...errorMessages, [id]: 'Telefonní číslo musí mít 9 číslic' });
      }
    }
  };

  const handleChange = (e) => {
    const { id, value } = e.target;
    setBillingData({ ...billingData, [id]: value });
    validate(e);
  };

  const handleShippingChange = (e) => {
    setShippingMethod(e.target.value);
  };

  const handlePaymentChange = (e) => {
    setPaymentMethod(e.target.value);
  };

  const submitForm = async () => {
      try {
          const formData = {
             address: {
               street: billingData.street,
               city: billingData.city,
               zipCode: parseInt(billingData.zipCode),
               phone: parseInt(billingData.phone)
             },
             shippingMethod: shippingMethod,
             paymentMethod: paymentMethod,
             items: items.map(item => ({ id: item.item.id, type: item.itemType, count: item.count })),
             price: totalPrice
          };
      console.log(formData.items);
        await axios.post(`http://localhost:8080/orders/create/${localStorage['id']}`, formData, { headers: { Authorization: axios.defaults.headers.common['Authorization'] } });
        navigate('/');
      } catch (error) {
        console.error('Error ordering items:', error);
     }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!Object.values(errorMessages).some(e => e !== '') && Object.values(billingData).some(e => e !== '')) {
      submitForm();
    }
  };

  const handleItemCountChange = (id, newCount) => {
    const updatedItems = items.map((item) => {
      if (item.id === id) {
        return { ...item, count: newCount };
      }
      return item;
    });
    setItems(updatedItems);
  };

  const isNotEmpty = (value) => {
    return value.length > 0;
  };

  if (items.length === 0) {
    return (
      <div id="container">
        <Nav />
        <div id="shopping-cart">
          <h1 id="name">Nákupní košík</h1>
          <p>Nákupní košík je prázdný.</p>
        </div>
        <Footer />
      </div>
    );
  }

  return (
    <div id="container">
      <Nav />
      <div id="shopping-cart">
        <h1 id="name">Nákupní košík</h1>
        <div id="list">
          {items.map((item) => (
            <ShoppingCartItem
              key={item.id}
              id={item.id}
              item={item.item}
              count={item.count}
              onItemCountChange={handleItemCountChange}
            />
          ))}
        </div>
        <div id="checkout">
          <form id="shopping-cart-order" onSubmit={handleSubmit}>
            <h2>Fakturační adresa</h2>

            <label htmlFor="street">Ulice</label>
            <input className={!isNotEmpty(errorMessages.street) || !isNotEmpty(errorMessages.street) ? 'valid' : 'invalid'} id="street" name="street" type="text" onChange={handleChange} />
            {errorMessages.street && <p className="error">{errorMessages.street}</p>}

            <label htmlFor="city">Město</label>
            <input className={!isNotEmpty(errorMessages.city) || !isNotEmpty(errorMessages.city) ? 'valid' : 'invalid'} id="city" name="city" type="text" onChange={handleChange} />
            {errorMessages.city && <p className="error">{errorMessages.city}</p>}

            <label htmlFor="zipCode">PSČ</label>
            <input className={!isNotEmpty(errorMessages.zipCode) || !isNotEmpty(errorMessages.zipCode) ? 'valid' : 'invalid'} id="zipCode" name="zipCode" type="number" onChange={handleChange} />
            {errorMessages.zipCode && <p className="error">{errorMessages.zipCode}</p>}

            <label htmlFor="firstname">Telefon</label>
            <input className={!isNotEmpty(errorMessages.phone) || !isNotEmpty(errorMessages.phone) ? 'valid' : 'invalid'} id="phone" name="phone" type="number" onChange={handleChange} />
            {errorMessages.phone && <p className="error">{errorMessages.phone}</p>}

            <h2>Doprava</h2>
            <select value={shippingMethod} onChange={handleShippingChange}>
              <option value="posta">Pošta (99kč)</option>
              <option value="ppl">PPL (99kč)</option>
              <option value="dpd">DPD (99kč)</option>
              <option value="zasilkovna">Zásilkovna (79kč)</option>
            </select>

            <h2>Způsob platby</h2>
            <select value={paymentMethod} onChange={handlePaymentChange}>
              <option value="dobirka">Dobírka (40kč)</option>
              <option value="kartou">Kartou (zdarma)</option>
              <option value="prevodem">Převodem (zdarma)</option>
            </select>

            <h2>Celková cena: {totalPrice.toLocaleString()} Kč (včetně DPH)</h2>
            <input id="submit" value="Objednat" type="submit" />
          </form>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ShoppingCart