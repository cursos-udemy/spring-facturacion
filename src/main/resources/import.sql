insert into cliente (id, nombre, apellido, email, create_at) values (1, 'Willy', 'Fernandez', 'willy@gmail.com', '2019-09-07');
insert into cliente (id, nombre, apellido, email, create_at) values (2, 'Agustina', 'Fernandez Girones', 'agus@gmail.com', '2019-09-06');
insert into cliente (id, nombre, apellido, email, create_at) values (3, 'Joaquin', 'Fernandez Girones', 'joacko@gmail.com', '2019-09-05');
insert into cliente (id, nombre, apellido, email, create_at) values (4, 'Karina', 'Girones', 'kari@gmail.com', '2019-09-04');

INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(5, 'Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(6, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(7, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(8, 'Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(9, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(10, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(11, 'Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(12, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(13,'John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(14, 'James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(15, 'Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(16, 'Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(17, 'John', 'Roe', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(18, 'Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(19, 'Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(20, 'Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(21, 'Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(22, 'Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(23, 'Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19');  
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(24, 'Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20'); 
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(25, 'Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(26, 'John', 'Smith', 'john.smith@gmail.com', '2017-08-22');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(27, 'Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(28, 'John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24');
INSERT INTO cliente (id, nombre, apellido, email, create_at) VALUES(29, 'Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25');

/* Populate tabla productos */
INSERT INTO producto (id, nombre, importe, create_at) VALUES(1, 'Panasonic Pantalla LCD', 25990, NOW());
INSERT INTO producto (id, nombre, importe, create_at) VALUES(2, 'Sony Camara digital DSC-W320B', 12340, NOW());
INSERT INTO producto (id, nombre, importe, create_at) VALUES(3, 'Apple iPod shuffle', 149990, NOW());
INSERT INTO producto (id, nombre, importe, create_at) VALUES(4, 'Sony Notebook Z110', 37990, NOW());
INSERT INTO producto (id, nombre, importe, create_at) VALUES(5, 'Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO producto (id, nombre, importe, create_at) VALUES(6, 'Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO producto (id, nombre, importe, create_at) VALUES(7, 'Mica Comoda 5 Cajones', 29990, NOW());

/* Creamos algunas facturas */
INSERT INTO factura (id, descripcion, cliente_id, fecha, numero, tipo_factura) VALUES(1, 'Factura equipos de oficina', 1, NOW(), '001-00000001', 'A');
INSERT INTO item_factura (id, cantidad, factura_id, producto_id, importe_unitario) VALUES(1, 1, 1, 1, 25990);
INSERT INTO item_factura (id, cantidad, factura_id, producto_id, importe_unitario) VALUES(2, 2, 1, 4, 37990);
INSERT INTO item_factura (id, cantidad, factura_id, producto_id, importe_unitario) VALUES(3, 1, 1, 5, 69990);
INSERT INTO item_factura (id, cantidad, factura_id, producto_id, importe_unitario) VALUES(4, 1, 1, 7, 29990);

INSERT INTO factura (id, descripcion, cliente_id, fecha, numero, tipo_factura) VALUES(2, 'Factura Bicicleta', 1, NOW(), '001-00000002', 'B');
INSERT INTO item_factura (id, cantidad, factura_id, producto_id, importe_unitario) VALUES(5, 3, 2, 6, 35980);


insert into user (username, password, enabled) values ('admin', '$2a$10$tuKj0e4UA008RvmwrfGTDe.fzpc5E42NMg6ZFIIqNaOSDorIdUby6', 1);
insert into user (username, password, enabled) values ('willy', '$2a$10$7hsYnkGdzqP9J8zFe85IfuYVrTlmVVsnuvX36ralZ01s1N1YtkmOq', 1);

insert into authority (user_id, role) values (1, 'ROLE_ADMIN');
insert into authority (user_id, role) values (1, 'ROLE_USER');
insert into authority (user_id, role) values (2, 'ROLE_USER');

