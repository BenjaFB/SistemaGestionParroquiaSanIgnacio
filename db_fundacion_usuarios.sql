-- Configuración inicial para evitar errores de importación
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";
SET FOREIGN_KEY_CHECKS = 0; -- Desactivar chequeo para permitir borrar tablas con dependencias

-- 1. Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS db_fundacion_usuarios DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE db_fundacion_usuarios;

-- 2. Eliminar tablas existentes para asegurar una importación limpia
DROP TABLE IF EXISTS voluntarios;
DROP TABLE IF EXISTS donantes;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS usuarios;

-- 3. Crear tabla base: USUARIOS
-- Coincide exactamente con el modelo Java (incluye columna 'rol')
CREATE TABLE usuarios (
  run int(11) NOT NULL,
  dv char(1) NOT NULL,
  nombre varchar(100) NOT NULL,
  appaterno varchar(100) NOT NULL,
  apmaterno varchar(100) NOT NULL,
  telefono varchar(15) DEFAULT NULL,
  correo varchar(150) NOT NULL,
  contrasena varchar(255) NOT NULL,
  rol varchar(50) NOT NULL,
  estado_cuenta boolean DEFAULT TRUE,
  PRIMARY KEY (run),
  UNIQUE KEY (correo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4. Crear tabla: VOLUNTARIOS
CREATE TABLE voluntarios (
  run int(11) NOT NULL,
  fecha_postulacion date DEFAULT NULL,
  areas_interes text DEFAULT NULL,
  disponibilidad varchar(100) DEFAULT NULL,
  PRIMARY KEY (run),
  CONSTRAINT fk_voluntario_usuario FOREIGN KEY (run) REFERENCES usuarios (run) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 5. Crear tabla: DONANTES
CREATE TABLE donantes (
  run int(11) NOT NULL,
  tipo_donante varchar(50) DEFAULT NULL,
  direccion varchar(255) DEFAULT NULL,
  PRIMARY KEY (run),
  CONSTRAINT fk_donante_usuario FOREIGN KEY (run) REFERENCES usuarios (run) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 6. Crear tabla: STAFF
CREATE TABLE staff (
  run int(11) NOT NULL,
  cargo varchar(100) DEFAULT NULL,
  unidad_departamento varchar(100) DEFAULT NULL,
  PRIMARY KEY (run),
  CONSTRAINT fk_staff_usuario FOREIGN KEY (run) REFERENCES usuarios (run) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Rehabilitar chequeos y finalizar
SET FOREIGN_KEY_CHECKS = 1;
COMMIT;