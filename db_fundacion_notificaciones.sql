CREATE DATABASE IF NOT EXISTS db_fundacion_notificaciones;
USE db_fundacion_notificaciones;

CREATE TABLE IF NOT EXISTS notificaciones (
    id_notificacion INT AUTO_INCREMENT PRIMARY KEY,
    run_destinatario INT NOT NULL, -- Referencia logica a Usuario
    mensaje TEXT NOT NULL,
    tipo VARCHAR(50) NOT NULL, -- EJ: DONACION, EVENTO, ANUNCIO
    fecha_envio DATETIME DEFAULT CURRENT_TIMESTAMP,
    leido BOOLEAN DEFAULT FALSE
);