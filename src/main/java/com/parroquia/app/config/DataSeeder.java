package com.parroquia.app.config;

import com.parroquia.app.usuarios.model.Staff;
import com.parroquia.app.usuarios.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Crea usuarios iniciales al arrancar la aplicación (solo si no existen).
 * Admin:      admin@parroquia.cl / admin123
 * Supervisor: supervisor@parroquia.cl / super123
 */
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initUsuarios(UsuarioRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByCorreo("admin@parroquia.cl").isEmpty()) {
                Staff admin = new Staff();
                admin.setRun(11111111);
                admin.setDv("1");
                admin.setNombre("Admin");
                admin.setAppaterno("Sistema");
                admin.setApmaterno("Parroquia");
                admin.setTelefono("+56 9 1111 1111");
                admin.setCorreo("admin@parroquia.cl");
                admin.setContrasena(encoder.encode("admin123"));
                admin.setRol("ROLE_ADMIN");
                admin.setCargo("Administrador");
                admin.setUnidadDepartamento("Central");
                repo.save(admin);
                System.out.println(">>> Usuario ADMIN creado: admin@parroquia.cl / admin123");
            }

            if (repo.findByCorreo("supervisor@parroquia.cl").isEmpty()) {
                Staff supervisor = new Staff();
                supervisor.setRun(22222222);
                supervisor.setDv("2");
                supervisor.setNombre("Supervisor");
                supervisor.setAppaterno("Sistema");
                supervisor.setApmaterno("Parroquia");
                supervisor.setTelefono("+56 9 2222 2222");
                supervisor.setCorreo("supervisor@parroquia.cl");
                supervisor.setContrasena(encoder.encode("super123"));
                supervisor.setRol("ROLE_SUPERVISOR");
                supervisor.setCargo("Supervisor de Terreno");
                supervisor.setUnidadDepartamento("Operaciones");
                repo.save(supervisor);
                System.out.println(">>> Usuario SUPERVISOR creado: supervisor@parroquia.cl / super123");
            }
        };
    }
}
