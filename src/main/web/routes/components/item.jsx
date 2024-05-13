import { useNavigate } from 'react-router-dom';
import axios from '../axios';

function Item(props) {
    let navigate = useNavigate();

    const item = props.item;

    const submitBuy = async () => {
        try{
            const response = await axios.post(`http://localhost:8080/carts/add/${localStorage['id']}`, { id: item.id, type: item.itemType, count: 1 }, axios.defaults.headers.common['Authorization']);
        } catch (error){
            console.error('Error adding product to shopping cart:', error);
        }
    }

    const handleBuy = () => {
        if (localStorage['token']) {
          submitBuy();
        }
    };

    return (
        <div className="item">
            <img className="thumbnail" src={item.thumbnail} alt={item.name} />
            <h3 onClick={() => navigate(`/${props.name}/${item.id}`)}>{item.name}</h3>
            <div className="price">
                <p>{(item.price).toLocaleString()},- Kč</p>
                <button onClick={handleBuy}>Koupit</button>
            </div>
        </div>
    );
}

export default Item