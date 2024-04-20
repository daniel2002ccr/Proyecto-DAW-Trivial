 DROP DATABASE IF EXISTS trivial_BBDD;
CREATE DATABASE IF NOT EXISTS trivial_BBDD;
USE trivial_BBDD;

CREATE TABLE users (

user_id INT NOT NULL AUTO_INCREMENT,

user_name VARCHAR(255) NOT NULL,

user_passwd VARCHAR(255) NOT NULL,

user_email VARCHAR(255) UNIQUE NOT NULL,

user_image LONGBLOB,

cantidad int DEFAULT NULL,

`activo` int DEFAULT NULL,
PRIMARY KEY (user_id)

);

CREATE TABLE roles (
role_id INT NOT NULL AUTO_INCREMENT,
 user_id INTEGER,
 user_role VARCHAR(255),
 FOREIGN KEY (user_id) REFERENCES users(user_id),
PRIMARY KEY (role_id)
); 

CREATE TABLE categoria(
`ID_Categoria` int NOT NULL AUTO_INCREMENT,
`nombre` VARCHAR(255) NOT NULL,
`activo` int DEFAULT NULL,
PRIMARY KEY (ID_Categoria)
);

CREATE TABLE ranking(
 ranking_id INT NOT NULL AUTO_INCREMENT,
 user_id INT NOT NULL,
 ID_Categoria INT NOT NULL,
 puntuacion INT DEFAULT NULL,
 FOREIGN KEY (user_id) REFERENCES users(user_id),
 FOREIGN KEY (ID_Categoria) REFERENCES Categoria(ID_Categoria),
 PRIMARY KEY (ranking_id)
);


INSERT INTO users (user_name, user_passwd, user_email, user_image, cantidad, activo)
VALUES ('usuario1', '$2a$12$mg6PT2KXcBe96gcYwcSw9Okb5uxICR/EqDhUNGvvQMh4nrcDLwnW6', 'usuario1@example.com', NULL, 10, 1),
       ('usuario2', '$2a$12$mg6PT2KXcBe96gcYwcSw9Okb5uxICR/EqDhUNGvvQMh4nrcDLwnW6', 'usuario2@example.com', NULL, 15, 1),
       ('usuario3', '$2a$12$mg6PT2KXcBe96gcYwcSw9Okb5uxICR/EqDhUNGvvQMh4nrcDLwnW6', 'usuario3@example.com', NULL, 20, 1);

-- Insertar datos en la tabla roles
INSERT INTO roles (user_id, user_role)
VALUES (1, 'admin'),
       (2, 'jugador');

-- Insertar datos en la tabla Categoria
INSERT INTO categoria (nombre, activo)
VALUES ('Deportes', 1),
       ('Cine', 1),
       ('Famosos', 0); -- Inactiva

-- Insertar datos en la tabla Ranking
INSERT INTO ranking (user_id, ID_Categoria, puntuacion)
VALUES (1, 1, 100),
       (2, 1, 90),
       (3, 2, 95);
       
       select * from users;
