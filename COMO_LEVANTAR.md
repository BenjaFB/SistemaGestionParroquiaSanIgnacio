# Parroquia San Ignacio — Proyecto UNIFICADO (monolito)

Ya no hay 7 microservicios ni frontend separado. **Todo es UNA sola aplicación Spring Boot**:
- El backend completo (usuarios, donaciones, inventario, eventos, institución, notificaciones, reportes)
- El frontend servido desde la misma app (carpeta `src/main/resources/static`)
- Una sola base de datos: `db_parroquia` (se crea sola, no necesitas correr scripts SQL)
- Sin CORS, sin puertos múltiples, sin Live Server

## Cómo levantar (UN solo comando)
1. Abre XAMPP y enciende **MySQL** (solo MySQL, Apache no hace falta).
2. En la carpeta del proyecto:
   ```
   mvnw spring-boot:run
   ```
   (en Windows: `mvnw.cmd spring-boot:run`, o ábrelo en IntelliJ/VSCode y corre `ParroquiaApplication`)
3. Abre el navegador en: **http://localhost:8080**

Eso es todo. Hibernate crea la base `db_parroquia` y todas las tablas automáticamente.

## Si MySQL no te funciona
En `src/main/resources/application.properties` hay un "PLAN B" comentado: descomenta las líneas de H2 y comenta las de MySQL. La app corre con base de datos en memoria (los datos se pierden al cerrar, pero sirve para demo/pruebas).

## Usuarios de prueba (se crean SOLOS al arrancar)
Ya no necesitas Postman ni curl. El `DataSeeder` crea automáticamente:

| Rol | Correo | Contraseña | Entra en |
|---|---|---|---|
| Admin | admin@parroquia.cl | admin123 | /pages/login-admin.html |
| Supervisor | supervisor@parroquia.cl | super123 | /pages/login-supervisor.html |

## OJO con las URLs
El frontend ya NO está en `/front-end/...`. Las rutas correctas son:
- http://localhost:8080  (o /index.html)
- http://localhost:8080/pages/login-admin.html
- etc.

## Endpoints (todos en el puerto 8080)
| Módulo | Ruta |
|---|---|
| Usuarios / Login | `/api/usuarios`, `/api/usuarios/login` |
| Donaciones | `/api/donaciones` |
| Inventario | `/api/inventario` |
| Eventos | `/api/eventos` |
| Institución | `/api/institucion` |
| Notificaciones | `/api/notificaciones` |
| Reportes | `/api/reportes` |

## Estructura
```
src/main/java/com/parroquia/app/
├── ParroquiaApplication.java   ← main
├── config/SecurityConfig.java  ← BCrypt + rutas permitidas
├── exception/GlobalExceptionHandler.java
├── usuarios/    (model, dto, repository, service, controller)
├── donaciones/  ...
├── eventos/     ...
├── institucion/ ...
├── inventario/  ...
├── notificaciones/ ...
└── reportes/    ...
src/main/resources/static/      ← el frontend (index.html, pages/, js/)
```

## Notas
- Probado: la app compila, arranca, sirve el frontend, registra usuarios, hace login (BCrypt), rechaza claves malas y guarda donaciones. ✅
- El voluntario que se inscribe por la web queda con contraseña inicial = su RUT sin puntos (ej: `12345678-9`).
- El Chat Bot (Node/Baileys) sigue siendo un proyecto aparte — no se puede fusionar con Java.
