function Parameter(props) {
    return (
        <div className="parameter">
            <p className="name">{props.name}</p>
            <p className="value">{props.value}</p>
        </div>
    );
}

export default Parameter