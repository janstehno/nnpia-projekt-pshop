import React from 'react';
import Filter from './filter.jsx';

const FilterList = ({ name, options, onChange }) => {
  return (
    <div className="filter">
      <h3>{name}</h3>
      {Object.keys(options).map((option) => (
        <Filter
          key={option}
          label={option}
          checked={options[option]}
          onChange={() => onChange(option)}
        />
      ))}
    </div>
  );
};

export default FilterList;