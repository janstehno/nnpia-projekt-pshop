import React from 'react';

function SortList(props) {
  const { sortBy, onChange } = props;

  return (
    <div>
      <button onClick={() => onChange('price-asc')} className={sortBy === 'price-asc' ? "active" : "inactive"}>Od nejlevnějšího</button>
      <button onClick={() => onChange('price-desc')} className={sortBy === 'price-desc' ? "active" : "inactive"}>Od nejdražšího</button>
    </div>
  );
}

export default SortList;