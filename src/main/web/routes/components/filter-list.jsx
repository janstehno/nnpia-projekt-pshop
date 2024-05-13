import React from 'react';
import Filter from './filter.jsx';

const FilterList = (props) => {
  const { name, options, onChange } = props;
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