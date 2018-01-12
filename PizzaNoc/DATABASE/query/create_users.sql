CREATE USER 'Kucharz'@'localhost' IDENTIFIED BY 'cook';
GRANT SELECT, INSERT, UPDATE ON pizzanoc.orders TO 'Kucharz'@'localhost';
GRANT SELECT, INSERT, UPDATE ON pizzanoc.ordered_products TO 'Kucharz'@'localhost';
GRANT SELECT ON pizzanoc.products TO 'Kucharz'@'localhost';
GRANT INSERT ON pizzanoc.clients TO 'Kucharz'@'localhost';
GRANT UPDATE ON pizzanoc.ingredients TO 'Kucharz'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'DostawcaTowaru'@'localhost' IDENTIFIED BY 'towar';
GRANT INSERT, UPDATE ON pizzanoc.ingredients TO 'DostawcaTowaru'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'DostawcaPizzy'@'localhost' IDENTIFIED BY 'pizzaboy';
GRANT SELECT ON pizzanoc.orders TO 'DostawcaPizzy'@'localhost';
FLUSH PRIVILEGES;

CREATE USER 'admin'@'localhost' IDENTIFIED BY 'pizzanoc';
GRANT ALL PRIVILEGES ON pizzanoc.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;