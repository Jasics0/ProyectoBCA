CREATE DATABASE BCA_TELECOMUNICACIONES;
USE BCA_TELECOMUNICACIONES;
CREATE TABLE usuarios (username VARCHAR(20) PRIMARY KEY, contraseña VARCHAR(100));
CREATE TABLE empleados (id_empleado VARCHAR(10), username VARCHAR(20),nombre VARCHAR(20), rol VARCHAR(10), direccion VARCHAR(15),estado_empleado BOOLEAN,telefono VARCHAR(10), PRIMARY KEY(id_empleado), FOREIGN KEY (username) REFERENCES usuarios(username));
CREATE TABLE planes (cod_producto VARCHAR(10) PRIMARY KEY, nombre VARCHAR(10), descripcion VARCHAR(150), valor float8);
CREATE TABLE clientes (id_cliente VARCHAR(10) PRIMARY KEY,nombre VARCHAR(20) NOT NULL,direccion VARCHAR(15), estado_cliente BOOLEAN,plan VARCHAR(10),telefono VARCHAR(10), FOREIGN KEY (plan) REFERENCES planes(cod_producto));
CREATE TABLE facturas(codigo_factura VARCHAR(10), id_cliente VARCHAR(10), id_empleado VARCHAR(10), fecha_inicial DATE,fecha_final DATE, valor DOUBLE, estado BOOLEAN,PRIMARY KEY(codigo_factura),FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado));
CREATE TABLE pagos (codigo_pago VARCHAR(10) PRIMARY KEY, codigo_factura VARCHAR(10), medio_pago VARCHAR(12),FOREIGN KEY(codigo_factura) REFERENCES facturas(codigo_factura));
CREATE TABLE tickets (codigo_ticket VARCHAR(10) PRIMARY KEY,id_empleado VARCHAR(10), id_cliente VARCHAR(10), servicio VARCHAR(100), fecha_inicial DATE,fecha_final DATE, prioridad VARCHAR (10), estado_ticket BOOLEAN,FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente),FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado));