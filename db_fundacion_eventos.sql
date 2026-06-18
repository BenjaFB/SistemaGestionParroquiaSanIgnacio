CREATE DATABASE IF NOT EXISTS db_fundacion_eventos;
USE db_fundacion_eventos;

CREATE TABLE IF NOT EXISTS eventos (
    id_evento INT AUTO_INCREMENT PRIMARY KEY,
    nombre_evento VARCHAR(150) NOT NULL,
    fecha DATETIME NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    descripcion TEXT,
    estado_evento VARCHAR(50) DEFAULT 'PENDIENTE' -- PENDIENTE, CONFIRMADO, POSTERGADO, REALIZADO
);