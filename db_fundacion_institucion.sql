CREATE DATABASE IF NOT EXISTS db_fundacion_institucion;
USE db_fundacion_institucion;

CREATE TABLE IF NOT EXISTS institucion (
    id_institucion INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    mision TEXT,
    vision TEXT,
    historia TEXT,
    correo_contacto VARCHAR(150),
    telefono_contacto VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS sedes (
    id_sede INT AUTO_INCREMENT PRIMARY KEY,
    nombre_sede VARCHAR(150) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    id_institucion INT NOT NULL,
    FOREIGN KEY (id_institucion) REFERENCES institucion(id_institucion) ON DELETE CASCADE
);