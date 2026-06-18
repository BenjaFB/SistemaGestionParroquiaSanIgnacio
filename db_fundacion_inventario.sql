CREATE DATABASE IF NOT EXISTS db_fundacion_inventario;
USE db_fundacion_inventario;

CREATE TABLE IF NOT EXISTS inventario (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nombre_material VARCHAR(150) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    stock_actual INT DEFAULT 0,
    unidad_medida VARCHAR(20) NOT NULL,
    descripcion TEXT
);

CREATE TABLE IF NOT EXISTS movimientos (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT NOT NULL,
    cantidad INT NOT NULL,
    tipo_movimiento ENUM('ENTRADA', 'SALIDA') NOT NULL,
    fecha_movimiento DATETIME DEFAULT CURRENT_TIMESTAMP,
    responsable_run INT NOT NULL, -- Referencia logica a Usuario (Staff/Admin)
    comentario TEXT,
    FOREIGN KEY (id_item) REFERENCES inventario(id_item) ON DELETE CASCADE
);