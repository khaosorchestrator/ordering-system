INSERT INTO restaurant.restaurants(id, name, active)
VALUES ('0794d227-8b66-4bcd-9da0-4490d9138378', 'Almir', TRUE);

INSERT INTO restaurant.restaurants(id, name, active)
VALUES ('0794d227-8b66-4bcd-9da0-4490d9138379', 'Pizzada', TRUE);

INSERT INTO restaurant.products(id, name, price, available)
VALUES ('0794d227-8b66-4bcd-9da0-4490d9138380', 'Caldeirada de Carne', 250.0, FALSE);

INSERT INTO restaurant.products(id, name, price, available)
VALUES ('0794d227-8b66-4bcd-9da0-4490d9138381', 'Pizza de Frutos do Mar', 750.0, TRUE);

INSERT INTO restaurant.products(id, name, price, available)
VALUES ('0794d227-8b66-4bcd-9da0-4490d9138382', 'Coca-Cola', 50.0, FALSE);

INSERT INTO restaurant.restaurant_products(id, restaurant_id, product_id)
VALUES (uuid_generate_v4(), '0794d227-8b66-4bcd-9da0-4490d9138378', '0794d227-8b66-4bcd-9da0-4490d9138380');

INSERT INTO restaurant.restaurant_products(id, restaurant_id, product_id)
VALUES (uuid_generate_v4(), '0794d227-8b66-4bcd-9da0-4490d9138378', '0794d227-8b66-4bcd-9da0-4490d9138382');

INSERT INTO restaurant.restaurant_products(id, restaurant_id, product_id)
VALUES (uuid_generate_v4(), '0794d227-8b66-4bcd-9da0-4490d9138379', '0794d227-8b66-4bcd-9da0-4490d9138380')