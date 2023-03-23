import React, { useEffect, useState } from "react";
import axios from "axios";
import headers from "../config/cors.config.js";
import { Header } from "semantic-ui-react";
import { Grid, Image } from "semantic-ui-react";
import bartable from "../img/medieval-dinner.jpeg";

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
      {tableNumber !== "none" ? (
        <Grid>
          <Grid.Column width={6}>
            <Image src={bartable} className="img-findtable" />
          </Grid.Column>
          <Grid.Column width={10}>
            <Header
              as="h2"
              className="h2-findtable"
              textAlign="center"
              size="medium"
            >
              `Your table number is: ${tableNumber}`
            </Header>
          </Grid.Column>
        </Grid>
      ) : (
        <Header
          as="h2"
          className="h2-findtable-none"
          textAlign="center"
          size="medium"
        >
          Sorry, no free tables right now!
        </Header>
      )}
    </div>
  );
};

export default TableNumber;
