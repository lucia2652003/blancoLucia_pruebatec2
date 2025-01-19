-- Creación de Base de datos
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

-- Insertar registros en la tabla turno
INSERT INTO turno (fecha, descripcion, estado, ciudadano_id) VALUES
('2025-05-12','Nutrición','ESPERA', 1),
('2025-01-25', "Dentista",'ATENDIDO', 1),
('2025-02-14',"Logopeda",'ESPERA', 2),
('2025-05-26',"Nutrición",'ATENDIDO', 2),
('2025-01-30',"Logopeda",'ESPERA', 3),
('2025-10-10',"Nutrición",'ATENDIDO', 3),
('2025-03-28',"Logopeda",'ESPERA', 4),
('2025-11-12',"Logopeda",'ESPERA', 5),
('2025-04-25',"Nutrición",'ATENDIDO', 6),
('2025-03-20','Oculista','ESPERA', 7),
('2025-10-01',"Médica",'ATENDIDO', 7),
('2025-08-08',"Médica",'ESPERA', 8),
('2025-03-15',"Dentista",'ATENDIDO', 8),
('2025-06-21',"Nutrición",'ESPERA', 9),
('2025-06-24',"Oculista",'ATENDIDO', 9),
('2025-11-26',"Médica",'ESPERA', 10),
('2025-12-12',"Logopeda",'ATENDIDO', 10);