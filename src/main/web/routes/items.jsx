import React, { useState, useEffect } from 'react';
import axios from 'axios';

import Nav from './components/nav.jsx';
import Footer from './components/footer.jsx';
import Item from './components/item.jsx';

function Items(props) {
  const [items, setItems] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(6);
  const [totalSize, setTotalSize] = useState(0);

  useEffect(() => {
    const fetchItems = async () => {
      const res = await axios.get(`http://localhost:8080/${props.name}/pages?page=${page}&size=${size}`);
      setItems(res.data.content);
      setTotalSize(Math.ceil(res.data.totalElements / size));
    };

    fetchItems();
  }, [items, page, size, props.name]);

  const handleNext = () => {
    setPage(Math.min(totalSize - 1, page + 1));
  };

  const handlePrev = () => {
    setPage(Math.max(0, page - 1));
  };

  return (
    <div id="container">
      <Nav />
      <div id="items">
        <h1>{props.name}</h1>
        <div id="wrap">
          {items.map((item) => (
            <Item key={item.id} item={item} />
          ))}
        </div>
        <div id="pages">
          <p>{page + 1} / {totalSize}</p>
          <div id="buttons">
            <button id="previous" onClick={handlePrev}><img src="previous.svg" alt="Předchozí" /></button>
            <button id="next" onClick={handleNext}><img src="next.svg" alt="Předchozí" /></button>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Items