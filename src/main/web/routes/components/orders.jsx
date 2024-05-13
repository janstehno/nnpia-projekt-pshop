import React, { useState, useEffect } from 'react';
import axios from '../axios';

import Order from './order.jsx';

function Orders() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    const fetchOrders = async () => {
      const response = await axios.get(`http://localhost:8080/orders/${localStorage['id']}`);
      console.log(response);
      setOrders(response.data);
    };

    fetchOrders();
  }, []);

  if (orders.length === 0) {
    return (
        <>
          <div id="orders">
            <p>Namáte žádné objednávky.</p>
          </div>
        </>
    );
  }

  return (
    <>
      <div id="orders">
        {orders.map((order) => (
          <Order key={order.id} order={order} />
        ))}
      </div>
    </>
  );
}

export default Orders;