import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from './axios';

import Nav from './components/nav.jsx';
import Footer from './components/footer.jsx';
import Error from './components/error.jsx';
import Parameter from './components/parameter.jsx';
import CameraDetail from './components/detail-camera.jsx';
import LensDetail from './components/detail-lens.jsx';

function Detail(props) {
  const { id } = useParams();
  const [item, setItem] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchItem = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/${props.name}/${id}`);
        const data = await response.data;
        setItem(data);
      } catch (error) {
        const status = error.response.status;
        if (status === 403) {
          setError({ status: status, message: 'Nemáte dostatečná oprávnění' });
        } else if (status === 404) {
          setError({ status: status, message: 'Produkt nebyl nalezen' });
        } else {
          setError({ status: 500, message: 'Nastala chyba' });
        }
      }
    };

    fetchItem();
  }, []);

  const submitBuy = async (id) => {
      try{
          const response = await axios.post(`http://localhost:8080/carts/add/${localStorage['id']}`, { id: item.id, type: item.itemType, count: 1 }, axios.defaults.headers.common['Authorization']);
      } catch (error){
          console.error('Error adding product to shopping cart:', error);
      }
  }

  const handleBuy = (id) => {
      if (localStorage['token']) {
        submitBuy(id);
      }
  };

  return (
    <div id="container">
      <Nav />
      <div id="detail-product">
        {error ? <Error code={error.status} message={error.message} /> : (
          <>
            <h1 id="name">{item.name}</h1>
            <div id="detail-top">
              <div id="image">
                <img src={`../${item.thumbnail}`} alt={item.name} />
              </div>
              <div id="info">
                <p id="text">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut iaculis sed ex sed mattis. Suspendisse finibus sapien ut libero viverra blandit. Ut hendrerit tellus elit, at efficitur leo tincidunt et. Curabitur at vestibulum dolor. Phasellus scelerisque volutpat sapien ut laoreet...
                </p>
                <p id="storage">Skladem {item.inStorage < 0 ? 0 : item.inStorage} ks</p>
                <div id="price">
                  <p>{item.price},- Kč</p>
                  <button onClick={() => handleBuy(item.id)}>Koupit</button>
                </div>
              </div>
            </div>
            {item.fps ? <CameraDetail item={item} /> : <LensDetail item={item} />}
          </>
        )}
      </div>
      <Footer />
    </div>
  );
}

export default Detail;