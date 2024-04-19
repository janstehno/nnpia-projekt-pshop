import React from 'react';

const Filter = ({ label, checked, onChange }) => {
  return (
    <label>
      <input
        type="checkbox"
        checked={checked}
        onChange={onChange}
      />
      <span className="box"></span>
      {label}
    </label>
  );
};

export default Filter;