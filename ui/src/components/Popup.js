import React from 'react';

function PopupMenu(props) {
  return (
    <div className="popup-menu">
      <ul>
        <li><a href="#">Get a table</a></li>
        <li><a href="#">Get menu</a></li>
        <li><a href="#">Order food</a></li>
        <li><a href="#">Order status</a></li>
        <li><a href="#">Pay</a></li>
      </ul>
    </div>
  );
}

export default PopupMenu;