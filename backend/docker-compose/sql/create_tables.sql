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

CREATE TABLE menu (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  description TEXT,
  price NUMERIC(10,2) NOT NULL,
  category TEXT NOT NULL
);

INSERT INTO menu (name, description, price, category) VALUES ('Cheeseburger', 'Our classic cheeseburger with lettuce, tomato, and pickles.', 9.99, 'Burgers');
INSERT INTO menu (name, description, price, category) VALUES ('Caesar Salad', 'Fresh romaine lettuce with parmesan cheese, croutons, and Caesar dressing.', 7.99, 'Salads');
INSERT INTO menu (name, description, price, category) VALUES ('Margarita Pizza', 'A thin-crust pizza with tomato sauce, mozzarella cheese, and fresh basil.', 12.99, 'Pizza');
INSERT INTO menu (name, description, price, category) VALUES ('Alt Beer', 'Dusseldorf must have', 4.0, 'Drinks');
INSERT INTO menu (name, description, price, category) VALUES ('Gin Tonic', 'It just works', 5.99, 'Drinks');
INSERT INTO menu (name, description, price, category) VALUES ('Limoncello', 'please try this', 2.99, 'Drinks');
