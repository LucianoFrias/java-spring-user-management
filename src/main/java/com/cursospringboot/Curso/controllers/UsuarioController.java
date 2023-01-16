package com.cursospringboot.Curso.controllers;

import com.cursospringboot.Curso.dao.UserDAO;
import com.cursospringboot.Curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id)
    {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setName("Luciano");
        usuario.setSurname("Frias");
        usuario.setPassword("1234");
        usuario.setEmail("lucianofrias1@hotmail.com");
        usuario.setPhoneNumber("02235758635");
        return usuario;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<Usuario> getAllUsers(@RequestHeader(value = "Authorization") String token)
    {
        byte[] decodedBytes = Base64.getDecoder().decode(token);
        String tokenCheck = new String(decodedBytes);

        if (token.isBlank())
        {
            return new ArrayList<>();
        }

        return userDAO.getUsuarios();
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody Usuario usuario)
    {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hashedPassword = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hashedPassword);

        userDAO.registerUsuario(usuario);
    }

    @RequestMapping(value = "api/user/4123")
    public Usuario editUsuario()
    {
        Usuario usuario = new Usuario();
        usuario.setName("Luciano");
        usuario.setSurname("Frias");
        usuario.setPassword("1234");
        usuario.setEmail("lucianofrias1@hotmail.com");
        usuario.setPhoneNumber("02235758635");
        return usuario;
    }
    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@PathVariable Long id)
    {
        userDAO.deleteUsuario(id);
    }

}
