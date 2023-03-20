import React, { useState } from 'react';
import axios from "axios";
import { Form, Dropdown, Popup } from 'semantic-ui-react';

const options = [
  { key: 'Cash', value: 'Cash', text: 'Cash' },
  { key: 'Card', value: 'Card', text: 'Card' },
];

const PayBill = (props) => {
  const [paymentMethod, setPaymentMethod] = useState(options[0].value);
  const [amount, setAmount] = useState(0);
  const [loading, setLoading] = useState(false);
  //const [message, setMessage] = useState('');
  const [status, setStatus] = useState('');


  const handleDropdownChange = (event, data) => {
    setPaymentMethod(data.value);
  };

  const handleFloatChange = (event) => {
    setAmount(parseFloat(event.target.value));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log('Selected option:', paymentMethod);
    console.log('Float value:', amount);

    const data = {
        tableID: props.tableID,
        paymentMethod: paymentMethod,
        amount: amount
      };

      try {
        setLoading(true);
        const response = await axios.post('http://localhost:8085/newpayment', data);
        console.log('Server response:', response.data);
        //setMessage(color:'green', message:'Payment successful!');
        setStatus({ color: 'green', message: 'Payment successful!'});
      } catch (error) {
        //setMessage("Payment failed!");
        setStatus({ color: 'red', message: "Payment failed!" });

      } finally {
        setLoading(false);
      }
  };

  return (
    <Form onSubmit={handleSubmit}>
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
      <Form.Field>
        <label>Amount:</label>
        <input type="number" step="0.01" value={amount} onChange={handleFloatChange} />
      </Form.Field>
      <Form.Button>Pay</Form.Button>

      {status && (
        <Popup
          content={status.message}
          trigger={<div style={{ color: status.color }}>{status.message}</div>}
          open
          onClose={() => setStatus('')}
          position="bottom center"
          style={{ backgroundColor: 'transparent', boxShadow: 'none' }}
          inverted
          hideOnScroll
          flowing
          wide
          basic
          size="mini"
          className={loading && 'shake'}
        />
      )}

    </Form>
  );
};

export default PayBill;