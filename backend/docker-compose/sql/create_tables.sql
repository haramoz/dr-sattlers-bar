CREATE TABLE orders (
  order_id varchar(250) NOT NULL,
  table_id varchar(250) NOT NULL,
  status varchar(250) NOT NULL,
  waiter_id varchar(250),
  PRIMARY KEY (order_id)
);

CREATE TABLE waiters (
  waiter_id varchar(250) NOT NULL,
  waiter_name varchar(100) NOT NULL,
  PRIMARY KEY (waiter_id)
);

CREATE TABLE tables (
  table_id varchar(250) NOT NULL,
  status varchar(10) DEFAULT 'available',
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

INSERT INTO waiters (waiter_id, waiter_name)
VALUES ('W001', 'Annika'),
       ('W007', 'James Bond'),
       ('W009','Rihanna');

INSERT INTO tables (table_id, status)
VALUES ('T1', 'available'),
       ('T2', 'available'),
       ('T3', 'available'),
       ('T4', 'available'),
       ('T5', 'available'),
       ('T6', 'available'),
       ('T7', 'available'),
       ('T8', 'available'),
       ('T9', 'available');

INSERT INTO menu (name, description, price, category) VALUES ('Cheeseburger', 'Our classic cheeseburger with lettuce, tomato, and pickles.', 9.99, 'Burgers');
INSERT INTO menu (name, description, price, category) VALUES ('Caesar Salad', 'Fresh romaine lettuce with parmesan cheese, croutons, and Caesar dressing.', 7.99, 'Salads');
INSERT INTO menu (name, description, price, category) VALUES ('Margarita Pizza', 'A thin-crust pizza with tomato sauce, mozzarella cheese, and fresh basil.', 12.99, 'Pizza');
INSERT INTO menu (name, description, price, category) VALUES ('Alt Beer', 'Dusseldorf must have', 4.0, 'Drinks');
INSERT INTO menu (name, description, price, category) VALUES ('Gin Tonic', 'It just works', 5.99, 'Drinks');
INSERT INTO menu (name, description, price, category) VALUES ('Limoncello', 'please try this', 2.99, 'Drinks');
