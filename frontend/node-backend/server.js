const express = require('express');
const pg = require('pg');
const cors = require('cors');

const app = express();

// enable CORS
app.use(cors());

// create a new PostgreSQL client
const client = new pg.Client({
  connectionString: 'postgres://postgres:postgres@localhost:5438/mydb'
});

// connect to the PostgreSQL database
client.connect();

// define an API endpoint to retrieve the menu data
app.get('/menu', (req, res) => {
  // execute a SELECT query on the menu table
  client.query('SELECT * FROM menu', (err, result) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: 'Internal server error' });
    }

    // return the menu data as JSON
    res.json(result.rows);
  });
});

app.get('/tablelist', (req, res) => {
  client.query('SELECT table_id FROM tables', (err, result) => {
    if (err) {
      console.error(err);
      res.status(500).send('Error retrieving data from database');
    } else {
      const optionsFromDb = result.rows.map(item => ({
        key: item.table_id,
        text: createDropdownLabel(item.table_id),
        value: item.table_id,
      }));
      res.json({ data: optionsFromDb });
    }
  });
});

function createDropdownLabel(table_id) {
  const regex = /(\d+)$/;
  const match = regex.exec(table_id);
  if (match) {
    const digit = match[1];
    return `Table ${digit}`;
  } else {
    console.error('No digit found in input');
    return table_id;
  }
}


//TO DO
//client.end();

// start the server on port 3000
app.listen(3000, () => {
  console.log('Server started on port 3000');
});
