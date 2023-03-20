import React, { useEffect, useState } from "react";
import axios from "axios";
import { Dropdown } from "semantic-ui-react";
import PayBill from "../components/PayBill.js"

function GetTablesDropdown() {
  const [options, setOptions] = useState([]);
  const [selectedOption, setSelectedOption] = useState(null);
  const [showForm, setShowForm] = useState(false);

  const handleDropdownChange = (event, data) => {
    setSelectedOption(data.value);
    setShowForm(true);
  };

  useEffect(() => {
    axios
      .get("http://localhost:3000/tablelist")
      .then((response) => {
        setOptions(response.data.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <div>
      <Dropdown
        placeholder="Select table"
        fluid
        selection
        options={options}
        onChange={handleDropdownChange}
      />
      {showForm && <PayBill tableID={selectedOption}/>}
    </div>
  );
}
export default GetTablesDropdown;
