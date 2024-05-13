import React, { useState, useEffect } from 'react';

function Orders(props) {
    const {order} = props;
  return (
    <>
      <div className="order">
        <h4>#{order.id}</h4>
        <div>
          <p className="price">{(order.price).toLocaleString()} kč</p>
          <p>{order.state}</p>
        </div>
      </div>
    </>
  );
}

export default Orders;