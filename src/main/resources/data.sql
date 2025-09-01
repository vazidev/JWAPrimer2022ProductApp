-- SQL seed for product table (works with MySQL/H2)
INSERT INTO product (product_name, quantity_on_hand, price, status)
VALUES
  ('Acme Widget', 100, 1999, 'PRODUCT_ACTIVE'),
  ('Roadrunner Rocket Skates', 25, 8999, 'PRODUCT_DISCOUNTED'),
  ('Coyote Anvil', 5, 4999, 'PRODUCT_ON_HOLD');

