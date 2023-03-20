import React, { useEffect, useState } from "react";
import axios from "axios";
import { Form, Dropdown, Message } from "semantic-ui-react";

const options = [
  { key: "Cash", value: "Cash", text: "Cash" },
  { key: "Card", value: "Card", text: "Card" },
];

const PayBill = (props) => {
  const [paymentMethod, setPaymentMethod] = useState(options[0].value);
  const [amount, setAmount] = useState(0);
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState(false);
  const [error, setError] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8085/bill/${props.tableID}`
        );
        setAmount(parseFloat(response.data.amount));
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, [props.tableID]);

  const handleDropdownChange = (event, data) => {
    setPaymentMethod(data.value);
  };

  const handleFloatChange = (event) => {
    setAmount(parseFloat(event.target.value));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log("Selected option:", paymentMethod);
    console.log("Float value:", amount);

    const data = {
      tableID: props.tableID,
      paymentMethod: paymentMethod,
      amount: amount,
    };

    try {
      setLoading(true);
      setError(false);
      setSuccess(false);
      const response = await axios.post(
        "http://localhost:8085/newpayment",
        data
      );
      console.log("Server response:", response.data);
      setLoading(false);
      setSuccess(true);
    } catch (error) {
      console.error(error);
      setLoading(false);
      setError(true);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Field>
        <label>Amount:</label>
        <input
          type="number"
          step="0.5"
          value={amount}
          min={amount}
          onChange={handleFloatChange}
        />
      </Form.Field>
      <Form.Field>
        <label>How would you like to pay?</label>
        <Dropdown
          placeholder="Select a payment method"
          fluid
          selection
          options={options}
          value={paymentMethod}
          onChange={handleDropdownChange}
        />
      </Form.Field>

      <Form.Button color="blue">Pay</Form.Button>

      {loading && <Message content="Processing payment..." />}
      {success && <Message positive content="Payment successful!" />}
      {error && (
        <Message negative>
          <Message.Header>Payment failed!</Message.Header>
          <p>Please try again later.</p>
        </Message>
      )}
    </Form>
  );
};

export default PayBill;
