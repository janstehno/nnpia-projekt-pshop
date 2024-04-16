function Item(props) {
    const item = props.item;

    return (
        <div className="item" onClick={() => location.href += `/${item.id}`}>
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