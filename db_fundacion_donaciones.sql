CREATE DATABASE IF NOT EXISTS db_fundacion_donaciones;
USE db_fundacion_donaciones;

CREATE TABLE IF NOT EXISTS donaciones (
    id_donacion INT AUTO_INCREMENT PRIMARY KEY,
    run_donante INT NOT NULL, -- Relación lógica con service-usuario
    categoria VARCHAR(100) NOT NULL, -- Ej: Construcción, Alimentos
    descripcion TEXT NOT NULL,
    cantidad INT NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL, -- Ej: sacos, unidades, kg
    fecha_ingreso DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) DEFAULT 'RECIBIDA' -- RECIBIDA, PROCESADA, DISTRIBUIDA
);