import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Getmenu() {
    const [menuItems, setMenuItems] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:3000/menu')
          .then(response => setMenuItems(response.data))
          .catch(error => console.error(error));
      }, []);

    return (
        <div>
            <h1>Menu Items</h1>
            <ul>
                {menuItems.map(item => (
                    <li key={item.id}>
                        <div>{item.name}</div>
                        <div>{item.price}</div>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default Getmenu;
