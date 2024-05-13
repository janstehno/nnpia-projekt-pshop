import React, { useState, useEffect } from 'react';
import axios from './axios';

import Nav from './components/nav.jsx';
import Footer from './components/footer.jsx';
import Item from './components/item.jsx';
import FilterList from './components/filter-list.jsx';
import SortList from './components/sort-list.jsx';

function Items(props) {
  const [items, setItems] = useState({
    items: [],
    filteredItems: [],
  });
  const [paging, setPaging] = useState({
    page: 0,
    totalPages: 0,
    size: 6,
  });
  const [filter, setFilter] = useState({
    brands: {},
    types: {},
  });
  const [sortBy, setSortBy] = useState('');

  useEffect(() => {
    const fetchItems = async () => {
      const response = await axios.get(`http://localhost:8080/${props.name}`);
      setItems((prevItems) => ({ ...prevItems, items: response.data }));
    };

    fetchItems();
  }, [props.name]);

  useEffect(() => {
    const fetchFilters = () => {
      const brands = {};
      const types = {};
      items.items.forEach((item) => {
        brands[item.brand] = false;
        types[item.type] = false;
      });
      setFilter({ brands, types });
    };

    fetchFilters();
  }, [items.items]);

  useEffect(() => {
    const filtered = items.items.filter((item) => {
      return (
        (!isFilterActive('brands') || filter.brands[item.brand]) &&
        (!isFilterActive('types') || filter.types[item.type])
      );
    });

    let sortedItems = [...filtered];
    if (sortBy === 'price-asc') {
      sortedItems.sort((a, b) => a.price - b.price);
    } else if (sortBy === 'price-desc') {
      sortedItems.sort((a, b) => b.price - a.price);
    }

    setItems((prevItems) => ({ ...prevItems, filteredItems: sortedItems }));
    setPaging((prevPaging) => ({ ...prevPaging, page: 0 }));
  }, [items.items, filter, sortBy]);

  useEffect(() => {
    const newTotalPages = Math.ceil(items.filteredItems.length / paging.size);
    setPaging((prevPaging) => ({ ...prevPaging, totalPages: newTotalPages }));
  }, [items.filteredItems, paging.size]);

  const handleNext = () => {
    setPaging((prevPaging) => ({ ...prevPaging, page: Math.min(paging.totalPages - 1, paging.page + 1) }));
  };

  const handlePrev = () => {
    setPaging((prevPaging) => ({ ...prevPaging, page: Math.max(0, paging.page - 1) }));
  };

  const handleFilterChange = (category, value) => {
    const updatedFilter = { ...filter, [category]: { ...filter[category], [value]: !filter[category][value] } };
    setFilter(updatedFilter);
  };

  const isFilterActive = (category) => {
    return Object.values(filter[category]).some(Boolean);
  };

  const handleSortChange = (value) => {
    setSortBy(value);
  };

  const pagedItems = items.filteredItems.slice(paging.page * paging.size, (paging.page + 1) * paging.size);

  return (
    <div id="container">
      <Nav />
      <div id="items">
        <h1 id="name">{props.name === "cameras" ? "Fotoaparáty" : "Objektivy"}</h1>
        <div id="row">
          <div id="filters">
            <FilterList
              name="Výrobce"
              options={filter.brands}
              onChange={(option) => handleFilterChange('brands', option)}
            />
            <FilterList
              name="Typ"
              options={filter.types}
              onChange={(option) => handleFilterChange('types', option)}
            />
          </div>
          <div id="pages">
            <div id="sort">
              <SortList
                sortBy={sortBy}
                onChange={(value) => handleSortChange(value)}
              />
            </div>
            <div id="wrap">
              {pagedItems.map((item) => (
                <Item key={item.id} name={props.name} item={item} />
              ))}
            </div>
          </div>
        </div>
        <div id="controls">
          <p>{paging.page + 1} / {paging.totalPages}</p>
          <div id="buttons">
            <button id="previous" onClick={handlePrev}><img src="/previous.svg" alt="Předchozí" /></button>
            <button id="next" onClick={handleNext}><img src="/next.svg" alt="Předchozí" /></button>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Items;