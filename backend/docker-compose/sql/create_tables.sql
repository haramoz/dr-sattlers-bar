CREATE TABLE orders (
  order_id varchar(250) NOT NULL,
  table_id varchar(250) NOT NULL,
  status varchar(250) NOT NULL,
  waiter_id varchar(250),
  PRIMARY KEY (order_id)
);

CREATE TABLE waiters (
  waiter_id varchar(250) NOT NULL,
  PRIMARY KEY (waiter_id)
);

CREATE TABLE tables (
  table_id varchar(250) NOT NULL,
  PRIMARY KEY (table_id)
);

CREATE TABLE bills (
  bill_id varchar(250) NOT NULL,
  payment_method varchar(250) NOT NULL,
  amount DECIMAL(20,3) NOT NULL,
  waiter_id varchar(250),
  table_id varchar(250) NOT NULL,
  items varchar(250),
  PRIMARY KEY (bill_id)
);
