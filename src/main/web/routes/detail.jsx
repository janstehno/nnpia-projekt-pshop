import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

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
        if (response.status === 404) {
            setError({ status: response.status, message: 'Produkt nebyl nalezen' });
        } else {
            const data = await response.data;
            setItem(data);
        }
      } catch (error) {
          setError({ status: 500, message: 'Nastala chyba' });
      }
    };

    fetchItem();
  }, []);

  return (
    <div id="container">
        <Nav />
            <div id="detail">
                {error ? <Error code={error.status} message={error.message} /> : (
                <>
                   <h1>{item.name}</h1>
                   <div id="detail-top">
                        <div id="image">
                            <img src={`../${item.thumbnail}`} alt={item.name} />
                        </div>
                        <div id="info">
                            <p id="text">
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut iaculis sed ex sed mattis. Suspendisse finibus sapien ut libero viverra blandit. Ut hendrerit tellus elit, at efficitur leo tincidunt et. Curabitur at vestibulum dolor. Phasellus scelerisque volutpat sapien ut laoreet...
                            </p>
                            <p id="storage">Skladem {item.inStorage} ks</p>
                            <div id="price">
                                <p>{item.price},- Kč</p>
                                <button>Koupit</button>
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