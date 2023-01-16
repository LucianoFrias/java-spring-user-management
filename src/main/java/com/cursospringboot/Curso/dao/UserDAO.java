package com.cursospringboot.Curso.dao;

import com.cursospringboot.Curso.models.Usuario;

import java.util.List;

public interface UserDAO {
    List<Usuario> getUsuarios();

    void deleteUsuario(Long id);

    void registerUsuario(Usuario usuario);

    Usuario getUserByLogin(Usuario usuario);
}
