package com.parroquia.app.usuarios.service;

import com.parroquia.app.usuarios.model.*;
import com.parroquia.app.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired 
    private PasswordEncoder encoder;

    @Transactional
    public void registrar(Usuario userRequest) {
        if(usuarioRepository.existsById(userRequest.getRun())) {
            throw new RuntimeException("El RUN ya existe en el sistema.");
        }
        // Encriptar contraseña antes de guardar
        userRequest.setContrasena(encoder.encode(userRequest.getContrasena()));
        usuarioRepository.save(userRequest);
    }


    public Usuario login(String correo, String contrasena) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Correo o contrase\u00f1a incorrectos."));

        if (!encoder.matches(contrasena, usuario.getContrasena())) {
            throw new RuntimeException("Correo o contrase\u00f1a incorrectos.");
        }
        if (Boolean.FALSE.equals(usuario.getEstadoCuenta())) {
            throw new RuntimeException("La cuenta se encuentra deshabilitada.");
        }
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtener(Integer run) {
        return usuarioRepository.findById(run)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con RUN: " + run));
    }

    @Transactional
    public void actualizar(Integer run, Usuario updated) {
        Usuario existing = obtener(run);
        existing.setNombre(updated.getNombre());
        existing.setAppaterno(updated.getAppaterno());
        existing.setApmaterno(updated.getApmaterno());
        existing.setTelefono(updated.getTelefono());
        existing.setEstadoCuenta(updated.getEstadoCuenta());
        
        // Si el usuario es de un tipo específico, se pueden actualizar sus campos
        if (existing instanceof Donante && updated instanceof Donante) {
            ((Donante) existing).setDireccion(((Donante) updated).getDireccion());
        }
        
        usuarioRepository.save(existing);
    }

    @Transactional
    public void eliminar(Integer run) {
        if(!usuarioRepository.existsById(run)) {
            throw new RuntimeException("No se puede eliminar: RUN no existe.");
        }
        usuarioRepository.deleteById(run);
    }
}