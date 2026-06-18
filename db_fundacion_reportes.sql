CREATE DATABASE IF NOT EXISTS db_fundacion_reportes;
USE db_fundacion_reportes;

CREATE TABLE IF NOT EXISTS reportes (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    nombre_reporte VARCHAR(150) NOT NULL,
    tipo_reporte VARCHAR(50) NOT NULL, -- EJ: DONACIONES, VOLUNTARIOS, INVENTARIO
    fecha_generacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    generado_por_run INT NOT NULL,
    datos_resumen TEXT -- Almacena un resumen JSON o descriptivo del reporte
);