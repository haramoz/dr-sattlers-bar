import React, { useEffect, useState } from "react";
import axios from "axios";
import headers from '../config/cors.config.js';
import { Header } from 'semantic-ui-react';


const TableNumber = () => {
  const [tableNumber, setTableNumber] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/findtable", {
        headers: headers,
      })
      .then((response) => setTableNumber(response.data))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div>
        <Header as='h2' className="h2findtable" textAlign='center' size='huge'>
            {tableNumber === "none"
            ? "Sorry, no free tables right now!"
            : `Your table number is ${tableNumber}`}
        </Header>
      
    </div>
  );
};

export default TableNumber;
