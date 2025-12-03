-- DROP DATABASE IF EXISTS gestion_ventas;
-- CREATE DATABASE gestion_ventas;
-- USE gestion_ventas;

DROP TABLE IF EXISTS detalle_ventas;
DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS clientes;


CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80)  NOT NULL,
  apellido VARCHAR(80)  NOT NULL,
  dni CHAR(8) NOT NULL,
  email VARCHAR(120) NULL,
  telefono VARCHAR(40)  NULL,
  activo boolean NOT NULL DEFAULT true,
  CONSTRAINT uq_clientes_dni UNIQUE (dni)
);

CREATE TABLE productos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  precio_compra DECIMAL(12,2) NOT NULL,
  precio_venta DECIMAL(12,2) NOT NULL,
  stock INT NOT NULL
);

CREATE TABLE ventas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_cliente INT NOT NULL,
  fecha DATETIME NOT NULL,
  estado ENUM('PENDIENTE','PAGADA','ANULADA') NOT NULL DEFAULT 'PENDIENTE',
  total DECIMAL(12,2) NOT NULL DEFAULT 0.00
);

ALTER TABLE ventas ADD CONSTRAINT fk_ventas_cliente
FOREIGN KEY (id_cliente) REFERENCES clientes(id);

CREATE TABLE detalle_ventas (
  id_venta         INT NOT NULL,
  id_producto      INT NOT NULL,
  precio_unitario  DECIMAL(12,2) NOT NULL,
  cantidad         INT NOT NULL,
  subtotal         DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (id_venta, id_producto)
);

ALTER TABLE detalle_ventas ADD CONSTRAINT fk_detalle_venta
FOREIGN KEY (id_venta) REFERENCES ventas(id);
ALTER TABLE detalle_ventas ADD CONSTRAINT fk_detalle_producto
FOREIGN KEY (id_producto) REFERENCES productos(id);
