import React, { useEffect, useState } from "react";
import axios from "axios";
import headers from "../config/cors.config.js";
import { Header } from "semantic-ui-react";

const TableNumber = () => {
  const [tableNumber, setTableNumber] = useState("");

  /*
  -First populate all the table ids in a dropdown
  -Then user selects a tableId (where currently sitting)
  -Based on the tableid the total bill is populated
  -User proceeds to select payment method
  -Add tip functionality (Future scope)
  -Make Payment 
  -Integrate Stripe.mock or paypal sandbox for processing the payment (Future scope) 
  */
  useEffect(() => {
    const tableNumber = 'T1';
    axios
      .get(`http://localhost:8085/${tableNumber}`, {
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
