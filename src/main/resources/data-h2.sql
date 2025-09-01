-- H2-specific seed (optional). Use with spring.sql.init.platform=h2
INSERT INTO product (product_name, quantity_on_hand, price, status)
VALUES
  ('Acme Widget H2', 50, 1499, 'PRODUCT_ACTIVE'),
  ('Rocket Skates H2', 10, 7999, 'PRODUCT_DISCOUNTED');

