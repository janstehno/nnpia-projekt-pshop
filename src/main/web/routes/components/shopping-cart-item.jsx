import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../axios';

function ShoppingCartItem(props) {
    let navigate = useNavigate();

    const { id, item } = props;
    const [count, setCount] = useState(props.count);

    const submitCount = async (newCount) => {
        try{
            const response = await axios.post(`http://localhost:8080/carts/add/${localStorage['id']}`, { id: item.id, type: item.itemType, count: newCount }, axios.defaults.headers.common['Authorization']);
            setCount(newCount);
        } catch (error){
            console.error('Error editing product in shopping cart:', error);
        }
    }

    const handleChange = (e) => {
        const newCount = parseInt(e.target.value);
        if (!isNaN(newCount) && newCount >= 1 && localStorage['token']) {
            submitCount(newCount);
        }
    };

    const handleAdd = () => {
        const newCount = count + 1;
        submitCount(newCount);
    };

    const handleSubtract = () => {
        const newCount = count - 1;
        if (newCount >= 1) {
            submitCount(newCount);
        }
    };

    const submitRemove = async () => {
        try {
            await axios.post(`http://localhost:8080/carts/remove/${localStorage['id']}`, { id: id }, { headers: { Authorization: axios.defaults.headers.common['Authorization'] } });
        } catch (error) {
            console.error('Error removing product from shopping cart:', error);
        }
    }

    const handleRemove = () => {
        if(localStorage['token']){
            submitRemove();
        }
    };

    return (
        <div className="item">
            <h3 className="name">{item.name}</h3>
            <p className="price">{item.price * count},- Kč</p>
            <button className="subtract" onClick={handleSubtract}>-</button>
            <input className="count" onChange={handleChange} value={count}/>
            <button className="add" onClick={handleAdd}>+</button>
            <button className="remove" onClick={handleRemove}>✖</button>
        </div>
    );
}

export default ShoppingCartItem