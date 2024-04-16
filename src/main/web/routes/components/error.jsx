function Error(props) {
  return (
    <div id="error">
      <h1>{props.code}</h1>
      <p>{props.message}</p>
    </div>
  );
}

export default Error;