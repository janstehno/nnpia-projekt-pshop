import React, { useState, useEffect } from 'react';

function Orders(props) {
    const { order } = props;

    const calculatePrice = () => {
        let price = order.shippingPrice + order.paymentPrice;
        order.items.forEach((item) => {
            price += item.price * item.count;
        });
        return price;
    };

    const [price, setPrice] = useState(calculatePrice());

    useEffect(() => {
        setPrice(calculatePrice());
    }, [order]);

    return (
        <>
            <div className="order">
                <h4>#{order.id}</h4>
                <div>
                    <p className="price">{price.toLocaleString()} kč</p>
                    <p>{order.state}</p>
                </div>
            </div>
        </>
    );
}

export default Orders;