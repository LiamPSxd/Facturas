DROP DATABASE IF EXISTS facturas;
CREATE DATABASE facturas;

USE facturas;

CREATE USER IF NOT EXISTS 'facturas'@'localhost' IDENTIFIED BY 'facturas';
GRANT ALL PRIVILEGES ON facturas.* TO 'root'@'localhost';
GRANT ALL PRIVILEGES ON facturas.* TO 'facturas'@'localhost';
FLUSH PRIVILEGES;

SHOW GRANTS FOR root@'localhost';
SHOW GRANTS FOR facturas@'localhost';

SELECT * FROM factura;
SELECT * FROM hibernate_sequence;