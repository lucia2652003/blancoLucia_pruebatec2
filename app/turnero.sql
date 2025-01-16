CREATE DATABASE turnero_db;
USE turnero_db;

-- Crear tabla de ciudadano
CREATE TABLE ciudadano (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL
);

-- Crear la tabla turnos para la relación uno a muchos
CREATE TABLE turno (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME(6) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    estado ENUM('ESPERA', 'ATENDIDO') NOT NULL,
    ciudadano_id BIGINT NOT NULL,
    FOREIGN KEY (ciudadano_id) REFERENCES ciudadano(id) ON DELETE CASCADE
);

-- Insertar registros en la tabla persona
INSERT INTO ciudadano (nombre, apellido) VALUES
('Juan', 'Perez'),
('Maria', 'Gomez'),
('Carlos', 'Lopez'),
('Ana', 'Martinez'),
('Luis', 'Garcia'),
('Elena', 'Sanchez'),
('Fernando', 'Diaz'),
('Lucia', 'Ramirez'),
('Sofia', 'Torres'),
('Miguel', 'Hernandez');