import { useNavigate } from 'react-router-dom';

function Item(props) {
    let navigate = useNavigate();

    const item = props.item;

    return (
        <div className="item" onClick={() => navigate(`/${props.name}/${item.id}`)}>
            <img className="thumbnail" src={item.thumbnail} alt={item.name} />
            <h3>{item.name}</h3>
            <div className="price">
                <p>{item.price},- Kč</p>
                <button>Koupit</button>
            </div>
        </div>
    );
}

export default Item